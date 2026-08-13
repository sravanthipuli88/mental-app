package com.sco.mental.assessment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.sco.mental.entity.User;


@Entity
@Table(name="assessment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Assessment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;


    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private AssessmentType type;


    private Integer score;


    private String severity;


    private String interpretation;


    private LocalDateTime createdAt;



    @PrePersist
    public void prePersist(){

        createdAt = LocalDateTime.now();

    }


}