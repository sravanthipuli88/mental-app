package com.sco.mental.controller;

import com.sco.mental.dto.MoodCheckinRequest;
import com.sco.mental.dto.MoodCheckinResponse;
import com.sco.mental.security.CurrentUserProvider;
import com.sco.mental.service.MoodCheckinService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * 
 * --------------------------------
 How are you feeling today?

 😄 Great       9
 🙂 Good        7
 😐 Neutral     5
 😔 Low         3
 😢 Very Low    1


 Sleep Hours:
 [ 7 hours ]

 Energy Level:
 ⭐⭐⭐⭐☆

 Notes:
 [ I feel stressed because exams ]

        SAVE
--------------------------------
 * request body
 * {
 "moodScore":7,
 "sleepHours":7,
 "energyLevel":4,
 "note":
 "Feeling stressed because of exams"
}
 * 
 * 
 * 
 * 
 */



@RestController
@RequestMapping("/api/mood-checkins")
public class MoodCheckinController {

    private final MoodCheckinService moodCheckinService;
    private final CurrentUserProvider currentUserProvider;

    public MoodCheckinController(MoodCheckinService moodCheckinService, CurrentUserProvider currentUserProvider) {
        this.moodCheckinService = moodCheckinService;
        this.currentUserProvider = currentUserProvider;
    }

   // @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public List<MoodCheckinResponse> getAll() {
    	System.out.println("inside mockcheckin controller");
        return moodCheckinService.getAllForUser(currentUserProvider.getCurrentUser());
    }

    @PostMapping
    public MoodCheckinResponse saveOrUpdate(@Valid @RequestBody MoodCheckinRequest req) {
        return moodCheckinService.saveOrUpdate(currentUserProvider.getCurrentUser(), req);
    }
}
