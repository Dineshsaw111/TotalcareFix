package com.totalcarefix.Repos;

import com.totalcarefix.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
}
