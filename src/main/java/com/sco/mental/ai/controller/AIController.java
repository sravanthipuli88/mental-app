package com.sco.mental.ai.controller;


import com.sco.mental.ai.dto.AIRequest;

/***
 * 
 * Assessment Engine
       |
       | (score + severity)
       |
       v
AI Wellness Service
       |
       v
Ollama LLM
(Qwen 2.5 / Llama 3.1)
 */


import com.sco.mental.ai.service.AIWellnessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
public class AIController {

private final AIWellnessService service;

/**
 * POST /api/v1/ai/assessment-explanation
 * {
  "userId":101,

  "phq9Score":15,
  "phq9Severity":"Moderately Severe Depression",

  "gad7Score":12,
  "gad7Severity":"Moderate Anxiety",

  "pss10Score":25,
  "pss10Severity":"Moderate Stress",

  "who5Score":40,
  "who5Severity":"Low Well-being"
}

 * @return
 */

	@PostMapping("/user/{userId}/summary")
	public String explain(@PathVariable Long userId){
		System.out.println("inside AI Generated explanation -1 >>> explain");
		return service.generateExplanation(userId);
	}

}