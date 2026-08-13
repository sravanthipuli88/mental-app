package com.sco.mental.assessment.repository;

import com.sco.mental.assessment.entity.AssessmentSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssessmentSummaryRepository
        extends JpaRepository<AssessmentSummary, Long> {


    List<AssessmentSummary> 
    findByUserIdOrderByCreatedAtDesc(Long userId);


    AssessmentSummary 
    findFirstByUserIdOrderByCreatedAtDesc(Long userId);


}
