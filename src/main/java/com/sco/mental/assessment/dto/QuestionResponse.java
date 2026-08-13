package com.sco.mental.assessment.dto;

public record QuestionResponse(


        Long id,


        Integer questionNumber,


        String question,


        Boolean reverseScore


) {}
