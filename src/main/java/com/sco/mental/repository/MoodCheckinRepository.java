package com.sco.mental.repository;

import com.sco.mental.entity.MoodCheckIn;
import com.sco.mental.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MoodCheckinRepository extends JpaRepository<MoodCheckIn, Long> {
    List<MoodCheckIn> findByUserOrderByCheckinDateAsc(User user);
    Optional<MoodCheckIn> findByUserAndCheckinDate(User user, LocalDate checkinDate);
    List<MoodCheckIn> findAllByOrderByCheckinDateAsc();
}
