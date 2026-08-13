package com.sco.mental.assessment.scoring;

import com.sco.mental.assessment.entity.AssessmentType;

import java.util.Map;


public interface AssessmentScoringService {


    AssessmentType supportedType();


    AssessmentScore calculate(
            Map<Integer,Integer> answers
    );

}