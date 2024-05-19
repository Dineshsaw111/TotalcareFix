package com.totalcarefix.Repos;

import com.totalcarefix.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Integer> {

    List<Booking> findAllBySkillId(int skillId);
    List<Booking> findAllByTechId(int techId);
}
