package com.sco.mental.assessment.repository;

import com.sco.mental.assessment.entity.AssessmentQuestion;
import com.sco.mental.assessment.entity.AssessmentType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AssessmentQuestionRepository
        extends JpaRepository<AssessmentQuestion, Long> {


    List<AssessmentQuestion> 
    findByTypeOrderByQuestionNumber(
            AssessmentType type
    );

}
