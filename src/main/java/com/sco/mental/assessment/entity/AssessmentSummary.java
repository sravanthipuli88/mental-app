package com.sco.mental.assessment.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import com.sco.mental.entity.User;


@Entity
@Table(name = "assessment_summary")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentSummary{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "user_id", nullable = false)
    private Long userId;


    @Column(name = "phq9_score")
    private Integer phq9Score;


    @Column(name = "gad7_score")
    private Integer gad7Score;


    @Column(name = "pss10_score")
    private Integer pss10Score;


    @Column(name = "who5_score")
    private Integer who5Score;


    @Column(name = "ai_summary", columnDefinition = "TEXT")
    private String aiSummary;


    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @PrePersist
    public void prePersist() {

        createdAt = LocalDateTime.now();

    }

}