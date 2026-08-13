package com.sco.mental.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(
    name = "mood_checkin",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "checkin_date"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoodCheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Mood score 1-10
     */
    @Column(nullable = false)
    private Integer moodScore;
    
    /**
     * Mood score 1-10
     */
    @Column(nullable = true)
    private Integer sleepHours;

    
    /**
     * Mood score 1-10
     */
    @Column(nullable = true)
    private Integer energyLevel;
    
    /**
     * Optional user notes
     */
    @Column(length = 1000)
    private String notes;

    @Column(nullable = false)
    private LocalDate checkinDate;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}