package com.sco.mental.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "journal_entry")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JournalEntry {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Column(nullable = false)
    private String title;


    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;


    /**
     * Optional AI sentiment analysis
     */
    private String sentiment;


    /**
     * Example:
     * HAPPY
     * SAD
     * ANXIOUS
     * CALM
     */
    private String emotion;


    private LocalDate entryDate;


    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

}
