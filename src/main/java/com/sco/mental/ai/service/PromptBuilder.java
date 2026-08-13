package com.sco.mental.ai.service;

import org.springframework.stereotype.Component;
import com.sco.mental.ai.dto.AIRequest;
@Component
public class PromptBuilder {

	public String build(
			AIRequest request) {


	return """
	You are a mental wellness assistant.

	Review the following assessment summary.

	PHQ-9:
	Score: %d
	Severity: %s


	GAD-7:
	Score: %d
	Severity: %s


	PSS-10:
	Score: %d
	Severity: %s


	WHO-5:
	Score: %d
	Severity: %s


	Provide:

	1. Simple explanation of results
	2. Relationship between mood, anxiety and stress
	3. Healthy coping recommendations
	4. Lifestyle suggestions
	5. When professional support may be useful


	Important safety rules:

	- Do not diagnose.
	- Do not prescribe medication.
	- Do not replace a healthcare professional.
	- If there are signs of self-harm risk, encourage immediate professional support.


	"""
	.formatted(

	request.phq9Score(),
	request.phq9Severity(),

	request.gad7Score(),
	request.gad7Severity(),

	request.pss10Score(),
	request.pss10Severity(),

	request.who5Score(),
	request.who5Severity()

	);


	}
}
