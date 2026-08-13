package com.sco.mental.service;

import com.sco.mental.dto.MoodCheckinRequest;
import com.sco.mental.dto.MoodCheckinResponse;
import com.sco.mental.entity.MoodCheckIn;
import com.sco.mental.entity.Role;
import com.sco.mental.entity.User;
import com.sco.mental.repository.MoodCheckinRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoodCheckinService {

    private final MoodCheckinRepository moodCheckinRepository;

    public MoodCheckinService(MoodCheckinRepository moodCheckinRepository) {
        this.moodCheckinRepository = moodCheckinRepository;
    }

    public List<MoodCheckinResponse> getAllForUser(User user) {
    	
    	
    	 List<MoodCheckIn> moodCheckins;

    	    if (user.getRole() == Role.ADMIN) {
    	        moodCheckins = moodCheckinRepository.findAllByOrderByCheckinDateAsc();
    	    } else {
    	        moodCheckins = moodCheckinRepository.findByUserOrderByCheckinDateAsc(user);
    	    }

    	    return moodCheckins.stream()
    	            .map(this::toResponse)
    	            .toList();
    	    

    }

    /**
     * Upsert: if the user already checked in on this date, update the score
     * instead of creating a duplicate (matches the unique constraint on
     * user_id + checkin_date).
     */
    public MoodCheckinResponse saveOrUpdate(User user, MoodCheckinRequest req) {
    	MoodCheckIn checkin = moodCheckinRepository.findByUserAndCheckinDate(user, req.date())
                .orElseGet(MoodCheckIn::new);

        checkin.setUser(user);
        checkin.setCheckinDate(req.date());
        checkin.setMoodScore(req.score());
        checkin.setSleepHours(req.sleepHours());
        checkin.setEnergyLevel(req.energyLevel());
        checkin.setNotes(req.notes());

        MoodCheckIn saved = moodCheckinRepository.save(checkin);
        return toResponse(saved);
    }

    private MoodCheckinResponse toResponse(MoodCheckIn c) {
        return new MoodCheckinResponse(c.getId(), c.getCheckinDate(), c.getMoodScore(),c.getSleepHours(),c.getEnergyLevel(),c.getNotes());
    }
}
