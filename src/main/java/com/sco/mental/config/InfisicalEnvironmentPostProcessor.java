package com.sco.mental.config;

import com.infisical.sdk.InfisicalSdk;
import com.infisical.sdk.config.SdkConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class InfisicalEnvironmentPostProcessor
        implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(
            ConfigurableEnvironment environment,
            SpringApplication application) {

     try {
    	 String clientId = environment.getProperty(
                 "INFISICAL_CLIENT_ID");
         
         String clientSecret = environment.getProperty(
                 "INFISICAL_CLIENT_SECRET");
         
         String projectId = environment.getProperty(
                 "INFISICAL_PROJECT_ID");

         String env = environment.getProperty(
                 "infisical.environment", "dev");

        String path = environment.getProperty(
                "infisical.secret-path", "/");

        String siteUrl = environment.getProperty(
                "infisical.site-url", "https://app.infisical.com");

        InfisicalSdk sdk = new InfisicalSdk(
                new SdkConfig.Builder()
                        .withSiteUrl(siteUrl)
                        .build());

        sdk.Auth().UniversalAuthLogin(clientId, clientSecret);

        // Fetch values without modifying JVM-wide system properties.
        var secrets = sdk.Secrets().ListSecrets(
                projectId, env, path,
                false, false, false, false);

        Map<String, Object> properties = new HashMap<>();

        for (var secret : secrets) {
        	 
            properties.put(secret.getSecretKey(),
                           secret.getSecretValue());
        }
             if (!properties.containsKey("AI_LLM_URL")) {
    throw new IllegalStateException(
        "AI_LLM_URL was not loaded from Infisical"
    );
}

        environment.getPropertySources().addFirst(
                new MapPropertySource(
                        "infisicalSecrets", properties));
     }catch(Exception e) {
        System.out.println(" reading secrets is issue");
     }
    }
}
