package com.sco.mental.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "user_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSettings {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "user_id",
        nullable = false,
        unique = true
    )
    private User user;


    private Boolean emailNotification = true;


    private Boolean reminderEnabled = true;


    /**
     * DAILY, WEEKLY
     */
    private String reminderFrequency;


    /**
     * DARK, LIGHT
     */
    private String theme;


    /**
     * AI suggestions enabled
     */
    private Boolean aiInsightsEnabled = true;


    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();


    private Instant updatedAt;

}
