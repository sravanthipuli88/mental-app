package com.sco.mental.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "assessment_result")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    /**
     * PHQ9, GAD7, PSS10, WHO5
     */
    @Column(nullable = false)
    private String assessmentType;


    @Column(nullable = false)
    private Integer score;


    /**
     * Example:
     * Minimal
     * Mild
     * Moderate
     * Severe
     */
    private String severityLevel;


    @Column(length = 2000)
    private String recommendation;


    @Column(nullable = false, updatable = false)
    private Instant completedAt = Instant.now();

}