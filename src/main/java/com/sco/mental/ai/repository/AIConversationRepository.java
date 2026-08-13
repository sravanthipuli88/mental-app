package com.sco.mental.ai.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sco.mental.ai.entity.AIConversation;

import java.util.List;


public interface AIConversationRepository
extends JpaRepository<AIConversation,Long>{


List<AIConversation> 
findByUserIdOrderByCreatedAtDesc(Long userId);


}
