package com.sco.mental.assessment.service;


import com.sco.mental.ai.dto.AIRequest;
import com.sco.mental.assessment.dto.*;
import com.sco.mental.assessment.entity.*;
import com.sco.mental.assessment.repository.*;
import com.sco.mental.assessment.scoring.*;
import com.sco.mental.entity.Role;
import com.sco.mental.entity.User;

import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Map;



@Service
@RequiredArgsConstructor
public class AssessmentService {



private final AssessmentRepository assessmentRepository;


private final AssessmentQuestionRepository questionRepository;



private final List<AssessmentScoringService> scoringServices;




public List<QuestionResponse> getQuestions(
        AssessmentType type){


return questionRepository
        .findByTypeOrderByQuestionNumber(type)

        .stream()

        .map(q ->
            new QuestionResponse(
                q.getId(),
                q.getQuestionNumber(),
                q.getQuestion(),
                q.getReverseScore()
            )
        )
        .toList();

}





public AssessmentResponse submit( User user, AssessmentRequest request){
	AssessmentScoringService scorer = scoringServices.stream().filter(s -> s.supportedType() == request.type())
        .findFirst().orElseThrow();
	AssessmentScore result = scorer.calculate( request.answers());
	Assessment assessment = Assessment.builder().user(user).type(request.type()).score(result.score()).severity(result.severity())
        .interpretation(result.interpretation()).build();
	Assessment saved = assessmentRepository.save(assessment);
	
	return new AssessmentResponse( saved.getId(),saved.getType(),saved.getScore(),saved.getSeverity(), saved.getInterpretation(),saved.getCreatedAt());
}

public List<AssessmentResponse> getAllAssessments(User user) {

    List<Assessment> assessments;

    if (user.getRole() == Role.ADMIN) {
        assessments = assessmentRepository.findAllByOrderByCreatedAtDesc();
    } else {
        assessments = assessmentRepository.findByUserOrderByCreatedAtDesc(user);
    }

    return assessments.stream()
            .map(this::toResponse)
            .toList();
}


// It is used in the AI controller

public AIRequest getAssessmentRequestDetails(Long userId) {

    Assessment phq9 = getLatestAssessment(userId, AssessmentType.PHQ9);
    Assessment gad7 = getLatestAssessment(userId, AssessmentType.GAD7);
    Assessment pss10 = getLatestAssessment(userId, AssessmentType.PSS10);
    Assessment who5 = getLatestAssessment(userId, AssessmentType.WHO5);

    return new AIRequest(

            userId,

            // PHQ-9
            getScore(phq9),
            getSeverity(phq9),

            // GAD-7
            getScore(gad7),
            getSeverity(gad7),

            // PSS-10
            getScore(pss10),
            getSeverity(pss10),

            // WHO-5
            getScore(who5),
            getSeverity(who5)
    );
}


private Assessment getLatestAssessment(
        Long userId,
        AssessmentType type) {

    return assessmentRepository
            .findFirstByUserIdAndTypeOrderByCreatedAtDesc(
                    userId,
                    type
            )
            .orElse(null);
}

private Integer getScore(Assessment assessment) {

    return assessment != null
            ? assessment.getScore()
            : null;
}

private String getSeverity(Assessment assessment) {

    return assessment != null
            ? assessment.getSeverity()
            : null;
}


private AssessmentResponse toResponse(Assessment assessment) {

    return new AssessmentResponse(
            assessment.getId(),
            assessment.getType(),
            assessment.getScore(),
            assessment.getSeverity(),
            assessment.getInterpretation(),
            assessment.getCreatedAt()
    );
}


}