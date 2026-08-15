package com.sco.mental.assessment.repository;

import com.sco.mental.assessment.entity.Assessment;
import com.sco.mental.assessment.entity.AssessmentType;
import com.sco.mental.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface AssessmentRepository 
        extends JpaRepository<Assessment, Long> {


    List<Assessment> findByUserOrderByCreatedAtDesc(User user);


    List<Assessment> findByUserAndTypeOrderByCreatedAtDesc(
            User user,
            AssessmentType type
    );
    
    List<Assessment> findAllByOrderByCreatedAtDesc();
    
    Optional<Assessment> findFirstByUserIdAndTypeOrderByCreatedAtDesc(
            Long userId,
            String type
    );

}
