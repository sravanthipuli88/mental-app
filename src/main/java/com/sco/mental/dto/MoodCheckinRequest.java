package com.sco.mental.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MoodCheckinRequest(
        @NotNull LocalDate date,
        @NotNull @Min(1) @Max(10) Integer score,
        @Min(1) @Max(24) Integer sleepHours,
        @Min(1) @Max(5) Integer energyLevel,
        String notes
        
) {}
