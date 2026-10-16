package com.sco.mental.ai.dto;

import lombok.Builder;

@Builder
public record AIRequest(

		 Long userId,
	        
	        Long phq9Id,

	        Integer phq9Score,

	        String phq9Severity,

	        Long gad7Id,
	        Integer gad7Score,

	        String gad7Severity,

	        Long pss10Id,
	        Integer pss10Score,

	        String pss10Severity,

	        Long who5Id,
	        Integer who5Score,

	        String who5Severity

) {}
