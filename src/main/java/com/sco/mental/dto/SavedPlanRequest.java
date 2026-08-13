package com.sco.mental.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SavedPlanRequest(
        @NotBlank String area,
        String areaOther,
        @NotBlank @Pattern(regexp = "quick|long") String length,
        @NotBlank @Pattern(regexp = "yes|no") String satisfaction,
        @NotNull Object content // flat array for "quick", {title, weeks} object for "long"
) {}
