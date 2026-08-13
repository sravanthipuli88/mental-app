package com.sco.mental.assessment.dto;

import com.sco.mental.assessment.entity.AssessmentType;

import java.time.LocalDateTime;


public record AssessmentResponse(


        Long assessmentId,


        AssessmentType type,


        Integer score,


        String severity,


        String interpretation,


        LocalDateTime completedAt


) {}
