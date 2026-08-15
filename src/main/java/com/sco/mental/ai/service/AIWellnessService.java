package com.sco.mental.ai.service;


import com.sco.mental.ai.dto.AIRequest;
import com.sco.mental.ai.entity.AIConversation;
import com.sco.mental.ai.repository.AIConversationRepository;
import com.sco.mental.assessment.entity.AssessmentSummary;
import com.sco.mental.assessment.repository.AssessmentSummaryRepository;
import com.sco.mental.assessment.service.AssessmentService;

import lombok.RequiredArgsConstructor;


import org.springframework.ai.chat.client.ChatClient;

import org.springframework.stereotype.Service;
import org.springframework.ai.openai.OpenAiChatOptions;



@Service
@RequiredArgsConstructor
public class AIWellnessService {


private final ChatClient chatClient;


private final PromptBuilder promptBuilder;


private final SafetyGuardService safetyGuard;


private final AssessmentSummaryRepository repository;

private final AssessmentService assessmentService;



public String generateExplanation(Long userId){
	String response = null;
	try {
System.out.println("inside AI Generated explanation -1");
//Getting inputs from db and forming request
 AIRequest request = assessmentService.getAssessmentRequestDetails(userId);

	
String prompt =promptBuilder.build(request);
System.out.println("inside AI Generated explanation -2");
response =chatClient.prompt().system(safetyGuard.systemPrompt()).user(prompt)
.options(OpenAiChatOptions.builder()
        .model("llama-3.3-70b-versatile")
        .temperature(0.7)
        .build()).call().content();
System.out.println("inside AI Generated explanation -3");

AssessmentSummary summary =AssessmentSummary.builder().userId(request.userId()).phq9Score(request.phq9Score()).gad7Score(request.gad7Score()).pss10Score(request.pss10Score()).who5Score(request.who5Score()).aiSummary(response).build();

//AIConversation history =AIConversation.builder().userId(userId).assessmentType(type).userPrompt(prompt).aiResponse(response).build();
System.out.println("inside AI Generated explanation -4");
repository.save(summary);
	}catch(Exception e) {
		e.printStackTrace();
	}

return response;

}


}
