package com.sco.mental.assessment.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="assessment_question")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentQuestion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Enumerated(EnumType.STRING)
    private AssessmentType type;



    @Column(columnDefinition = "TEXT")
    private String question;



    private Integer questionNumber;



    private Boolean reverseScore;


}
