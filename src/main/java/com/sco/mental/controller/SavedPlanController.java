package com.sco.mental.controller;

import com.sco.mental.dto.SavedPlanRequest;
import com.sco.mental.dto.SavedPlanResponse;
import com.sco.mental.security.CurrentUserProvider;
import com.sco.mental.service.SavedPlanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saved-plans")
public class SavedPlanController {

    private final SavedPlanService savedPlanService;
    private final CurrentUserProvider currentUserProvider;

    public SavedPlanController(SavedPlanService savedPlanService, CurrentUserProvider currentUserProvider) {
        this.savedPlanService = savedPlanService;
        this.currentUserProvider = currentUserProvider;
    }

    @GetMapping
    public List<SavedPlanResponse> getAll() {
        return savedPlanService.getAllForUser(currentUserProvider.getCurrentUser());
    }

    @PostMapping
    public SavedPlanResponse save(@Valid @RequestBody SavedPlanRequest req) {
        return savedPlanService.saveIfSatisfied(currentUserProvider.getCurrentUser(), req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        savedPlanService.delete(currentUserProvider.getCurrentUser(), id);
    }
}
