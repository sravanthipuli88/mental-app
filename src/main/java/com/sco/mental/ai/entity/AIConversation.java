package com.sco.mental.ai.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name="ai_conversation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AIConversation {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


private Long userId;


private String assessmentType;


@Column(columnDefinition="TEXT")
private String userPrompt;


@Column(columnDefinition="TEXT")
private String aiResponse;


private LocalDateTime createdAt;



@PrePersist
public void create(){

createdAt = LocalDateTime.now();

}

}
