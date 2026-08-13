package com.sco.mental.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sco.mental.dto.SavedPlanRequest;
import com.sco.mental.dto.SavedPlanResponse;
import com.sco.mental.entity.SavedPlan;
import com.sco.mental.entity.User;
import com.sco.mental.repository.SavedPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedPlanService {

    private final SavedPlanRepository savedPlanRepository;
    private final ObjectMapper objectMapper;

    public SavedPlanService(SavedPlanRepository savedPlanRepository, ObjectMapper objectMapper) {
        this.savedPlanRepository = savedPlanRepository;
        this.objectMapper = objectMapper;
    }

    public List<SavedPlanResponse> getAllForUser(User user) {
        return savedPlanRepository.findByUserOrderBySavedAtDesc(user)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    /**
     * Mirrors the frontend rule: a plan is only ever persisted if the user
     * confirmed satisfaction ("yes"). Rejected plans ("no") are acknowledged
     * but never written to the database — this is enforced here, not just
     * trusted from the client, since the client is not a security boundary.
     *
     * For long-term plans, saving a new satisfied plan replaces any existing
     * long-term plan for that user, so only one is ever active at a time.
     */
    public SavedPlanResponse saveIfSatisfied(User user, SavedPlanRequest req) {
        if (!"yes".equals(req.satisfaction())) {
            throw new IllegalArgumentException(
                    "Plans are only saved when satisfaction is 'yes'. This plan was not persisted.");
        }

        if ("long".equals(req.length())) {
            List<SavedPlan> existingLongTerm = savedPlanRepository.findByUserAndLength(user, "long");
            savedPlanRepository.deleteAll(existingLongTerm);
        }

        SavedPlan plan = new SavedPlan();
        plan.setUser(user);
        plan.setArea(req.area());
        plan.setAreaOther(req.areaOther());
        plan.setLength(req.length());
        plan.setSatisfaction(req.satisfaction());
        plan.setContentJson(toJson(req.content()));

        SavedPlan saved = savedPlanRepository.save(plan);
        return toResponse(saved);
    }

    public void delete(User user, Long planId) {
        SavedPlan plan = savedPlanRepository.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("Plan not found."));

        if (!plan.getUser().getId().equals(user.getId())) {
            // Don't leak existence of another user's plan; same error either way.
            throw new IllegalArgumentException("Plan not found.");
        }

        savedPlanRepository.delete(plan);
    }

    private String toJson(Object content) {
        try {
            return objectMapper.writeValueAsString(content);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Plan content could not be saved.", e);
        }
    }

    private Object fromJson(String json) {
        try {
            return objectMapper.readValue(json, Object.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    private SavedPlanResponse toResponse(SavedPlan p) {
        return new SavedPlanResponse(
                p.getId(),
                p.getArea(),
                p.getAreaOther(),
                p.getLength(),
                p.getSatisfaction(),
                fromJson(p.getContentJson()),
                p.getSavedAt()
        );
    }
}
