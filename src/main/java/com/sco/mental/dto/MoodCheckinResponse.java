package com.sco.mental.dto;

import java.time.LocalDate;

public record MoodCheckinResponse(
        Long id,
        LocalDate date,
        Integer score,
        Integer sleepHours,
        Integer energyLevel,
        String notes
) {}
