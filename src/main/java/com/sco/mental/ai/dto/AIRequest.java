package com.sco.mental.ai.dto;

import lombok.Builder;

@Builder
public record AIRequest(

        Long userId,

        Integer phq9Score,

        String phq9Severity,


        Integer gad7Score,

        String gad7Severity,


        Integer pss10Score,

        String pss10Severity,


        Integer who5Score,

        String who5Severity

) {}
