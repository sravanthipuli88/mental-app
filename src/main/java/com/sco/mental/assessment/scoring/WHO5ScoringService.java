package com.sco.mental.assessment.scoring;


import com.sco.mental.assessment.entity.AssessmentType;
import org.springframework.stereotype.Service;


import java.util.Map;



@Service
public class WHO5ScoringService
        implements AssessmentScoringService {



@Override
public AssessmentType supportedType(){

    return AssessmentType.WHO5;

}



@Override
public AssessmentScore calculate(
        Map<Integer,Integer> answers){


    int rawScore =
            answers.values()
                    .stream()
                    .mapToInt(Integer::intValue)
                    .sum();


    int percentage =
            rawScore * 4;



    String severity =
            percentage < 50
            ?
            "Low Well-being"
            :
            "Good Well-being";



    return new AssessmentScore(

            percentage,

            severity,

            "WHO-5 wellbeing assessment completed"

    );


}

}