package com.sco.mental.assessment.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="assessment_answer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentAnswer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="assessment_id")
    private Assessment assessment;



    private Integer questionNumber;


    private Integer answer;


}
