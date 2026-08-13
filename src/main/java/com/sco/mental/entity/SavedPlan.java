package com.sco.mental.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

/**
 * A saved plan (quick fix or long-term). The actual plan content (title + steps,
 * or a flat list of suggestions) is stored as a JSON string in `contentJson` so the
 * schema doesn't need to change as plan shapes evolve — the frontend owns the shape.
 */
@Entity
@Table(name = "saved_plan")
@Getter
@Setter
@NoArgsConstructor
public class SavedPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String area; // relationships | depression | stress | trauma | anxiety | other

    private String areaOther; // free-text description when area == "other"

    @Column(nullable = false)
    private String length; // "quick" | "long"

    @Column(nullable = false)
    private String satisfaction; // "yes" | "no"

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contentJson;

    @Column(nullable = false, updatable = false)
    private Instant savedAt = Instant.now();
}
