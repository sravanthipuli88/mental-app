package com.sco.mental.assessment.scoring;


import com.sco.mental.assessment.entity.AssessmentType;
import org.springframework.stereotype.Service;


import java.util.Map;
import java.util.Set;



@Service
public class PSS10ScoringService
        implements AssessmentScoringService {



private static final Set<Integer> REVERSE =
        Set.of(4,5,7,8);



@Override
public AssessmentType supportedType(){

    return AssessmentType.PSS10;

}



@Override
public AssessmentScore calculate(
        Map<Integer,Integer> answers){



    int score=0;



    for(var item:answers.entrySet()){


        int value=item.getValue();



        if(REVERSE.contains(item.getKey())){

            value=4-value;

        }


        score+=value;

    }



    String severity;


    if(score<=13)

        severity="Low Stress";


    else if(score<=26)

        severity="Moderate Stress";


    else

        severity="High Stress";



    return new AssessmentScore(

            score,

            severity,

            "PSS-10 stress screening completed"

    );

}

}
