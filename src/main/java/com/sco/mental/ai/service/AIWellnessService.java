package com.sco.mental.ai.service;


import com.sco.mental.ai.dto.AIRequest;
import com.sco.mental.ai.entity.AIConversation;
import com.sco.mental.ai.repository.AIConversationRepository;
import com.sco.mental.assessment.entity.AssessmentSummary;
import com.sco.mental.assessment.repository.AssessmentRepository;
import com.sco.mental.assessment.repository.AssessmentSummaryRepository;
import com.sco.mental.assessment.service.AssessmentService;

import lombok.RequiredArgsConstructor;


import org.springframework.ai.chat.client.ChatClient;

import org.springframework.stereotype.Service;
import org.springframework.ai.openai.OpenAiChatOptions;
import java.util.List;



@Service
@RequiredArgsConstructor
public class AIWellnessService {


private final ChatClient chatClient;


private final PromptBuilder promptBuilder;


private final SafetyGuardService safetyGuard;


private final AssessmentSummaryRepository repository;

private final AssessmentService assessmentService;
private final AssessmentRepository assessmentRepository;



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
        .model("openai/gpt-oss-120b")
        .temperature(0.7)
        .build()).call().content();
System.out.println("inside AI Generated explanation -3");

AssessmentSummary summary =AssessmentSummary.builder().userId(request.userId()).phq9Score(request.phq9Score()).gad7Score(request.gad7Score()).pss10Score(request.pss10Score()).who5Score(request.who5Score()).aiSummary(response).build();

AssessmentSummary savedSummary = repository.save(summary);
Long aiId = savedSummary.getId();

assessmentRepository.updateAssessmentAIId(userId,"PHQ9",request.phq9Id(),aiId);
assessmentRepository.updateAssessmentAIId(userId,"GAD7",request.gad7Id(),aiId);
assessmentRepository.updateAssessmentAIId(userId,"PSS10",request.pss10Id(),aiId);
assessmentRepository.updateAssessmentAIId(userId,"WHO5",request.who5Id(),aiId);

	}catch(Exception e) {
		e.printStackTrace();
	}

return response;

}


public AssessmentSummary getLatestAssessment(Long userId) {
    return repository.findFirstByUserIdOrderByCreatedAtDesc(userId);
}


public List<AssessmentSummary> getAllAssessments() {
    return repository.findAllByOrderByCreatedAtDesc();
}


public List<AssessmentSummary> getAssessmentHistory(Long userId) {
    return repository.findByUserIdOrderByCreatedAtDesc(userId);
}

public AssessmentSummary getLatestAssessmentForAllUsers() {
    return repository.findFirstByOrderByCreatedAtDesc();
}



}
