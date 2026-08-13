package com.sco.mental.dto;

public record AuthResponse(
        String token,
        Long userId,
        String username,
        String firstName,
        String lastName,
        String gender,
        String ageBucket
) {}
