package com.sco.mental.assessment.repository;

import com.sco.mental.assessment.entity.Assessment;
import com.sco.mental.assessment.entity.AssessmentType;
import com.sco.mental.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import com.sco.mental.assessment.dto.AssessmentUserDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


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
	            AssessmentType type
	    );

	    
  
	    
	    @Query("""
	    	    SELECT new com.sco.mental.assessment.dto.AssessmentUserDTO(
	    	    		 a.id,
	    	    	        a.createdAt,
	    	    	        a.interpretation,
	    	    	        a.score,
	    	    	        a.severity,
	    	    	        a.type,
	    	    	        u.id, 
	    	        u.username,
	    	        a.aiId
	        	    )
	    	    FROM Assessment a
	    	    JOIN a.user u
	    	    ORDER BY a.createdAt DESC
	    	    """)

	    List<AssessmentUserDTO> findAssessmentsWithUsername();	 


	    @Modifying
	    @Transactional
	    @Query(value = """
	        UPDATE assessment
	        SET ai_id = :aiId
	        WHERE user_id = :userId
	          AND type = :type
	          AND id = :assessmentId
	        """, nativeQuery = true)
	    int updateAssessmentAIId(
	            @Param("userId") Long userId,
	            @Param("type") String type,
	            @Param("assessmentId") Long assessmentId,
	            @Param("aiId") Long aiId
	    );


}
