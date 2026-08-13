package com.sco.mental.assessment.scoring;

import com.sco.mental.assessment.entity.AssessmentType;
import org.springframework.stereotype.Service;


import java.util.Map;



@Service
public class GAD7ScoringService
        implements AssessmentScoringService {



@Override
public AssessmentType supportedType() {

    return AssessmentType.GAD7;

}



@Override
public AssessmentScore calculate(
        Map<Integer,Integer> answers) {


    int score =
            answers.values()
                    .stream()
                    .mapToInt(Integer::intValue)
                    .sum();


    String severity;


    if(score <=4)

        severity="Minimal Anxiety";


    else if(score <=9)

        severity="Mild Anxiety";


    else if(score <=14)

        severity="Moderate Anxiety";


    else

        severity="Severe Anxiety";



    return new AssessmentScore(

            score,

            severity,

            "GAD-7 anxiety screening completed"

    );


}

}