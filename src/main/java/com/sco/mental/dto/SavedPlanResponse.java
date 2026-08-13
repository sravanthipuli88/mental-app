package com.sco.mental.dto;

import java.time.Instant;

public record SavedPlanResponse(
        Long id,
        String area,
        String areaOther,
        String length,
        String satisfaction,
        Object content,
        Instant savedAt
) {}
