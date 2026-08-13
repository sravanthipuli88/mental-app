package com.sco.mental.assessment.scoring;

public record AssessmentScore(

        Integer score,

        String severity,

        String interpretation

) {}