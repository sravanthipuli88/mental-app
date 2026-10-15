package com.sco.mental.assessment.dto;

import com.sco.mental.assessment.entity.AssessmentType;

import java.time.LocalDateTime;


public record AssessmentUserDTO(

        Long id,
        LocalDateTime createdAt,
        String interpretation,
        Integer score,
        String severity,
        AssessmentType type,
        Long userId,
        String username

) {}
