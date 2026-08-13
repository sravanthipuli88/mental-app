package com.sco.mental.assessment.scoring;

import com.sco.mental.assessment.entity.AssessmentType;
import org.springframework.stereotype.Service;


import java.util.Map;


@Service
public class PHQ9ScoringService implements AssessmentScoringService {


@Override
public AssessmentType supportedType() {

    return AssessmentType.PHQ9;

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
        severity="Minimal Depression";

    else if(score <=9)
        severity="Mild Depression";

    else if(score <=14)
        severity="Moderate Depression";

    else if(score <=19)
        severity="Moderately Severe Depression";

    else
        severity="Severe Depression";



    return new AssessmentScore(
            score,
            severity,
            "PHQ-9 depression screening completed"
    );

}

}
