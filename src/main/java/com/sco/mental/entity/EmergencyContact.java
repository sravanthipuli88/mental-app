package com.sco.mental.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "emergency_contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmergencyContact {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Column(nullable = false)
    private String contactName;


    private String relationship;


    @Column(nullable = false)
    private String phoneNumber;


    private String email;


    private Boolean primaryContact = false;


    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

}
