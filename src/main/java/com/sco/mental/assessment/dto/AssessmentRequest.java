package com.sco.mental.assessment.dto;

import com.sco.mental.assessment.entity.AssessmentType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Map;


public record AssessmentRequest(


        @NotNull
        AssessmentType type,


        @NotEmpty
        Map<Integer,Integer> answers


) {}
