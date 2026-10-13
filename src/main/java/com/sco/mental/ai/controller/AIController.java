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
import com.sco.mental.assessment.entity.AssessmentSummary;
import java.util.List;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/v1/ai/summary")
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

	@PostMapping("/user/{userId}")
	public String explain(@PathVariable Long userId){
		System.out.println("inside AI Generated explanation -1 >>> explain");
		return service.generateExplanation(userId);
	}
	
	
	// All users' assessment records
    @GetMapping
    public ResponseEntity<List<AssessmentSummary>> getAllAssessments() {
        return ResponseEntity.ok(service.getAllAssessments());
    }

    // Assessment history for one user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AssessmentSummary>> getByUserId(
            @PathVariable Long userId) {
        return ResponseEntity.ok(service.getAssessmentHistory(userId));
    }

    // Latest assessment for one user
    @GetMapping("/user/{userId}/latest")
    public ResponseEntity<AssessmentSummary> getLatestByUserId(
            @PathVariable Long userId) {

        AssessmentSummary result = service.getLatestAssessment(userId);

        return result != null
                ? ResponseEntity.ok(result)
                : ResponseEntity.notFound().build();
    }

    // Latest assessment across all users
    @GetMapping("/latest")
    public ResponseEntity<AssessmentSummary> getLatestForAllUsers() {
        AssessmentSummary result =
                service.getLatestAssessmentForAllUsers();

        return result != null
                ? ResponseEntity.ok(result)
                : ResponseEntity.notFound().build();
    }
}