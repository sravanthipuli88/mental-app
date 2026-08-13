package com.sco.mental.repository;

import com.sco.mental.entity.SavedPlan;
import com.sco.mental.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedPlanRepository extends JpaRepository<SavedPlan, Long> {
    List<SavedPlan> findByUserOrderBySavedAtDesc(User user);

    // Used to enforce "only one active long-term plan" at the data layer too,
    // not just in frontend logic.
    List<SavedPlan> findByUserAndLength(User user, String length);
}
