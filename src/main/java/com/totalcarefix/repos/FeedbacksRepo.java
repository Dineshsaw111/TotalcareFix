package com.totalcarefix.repos;

import com.totalcarefix.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbacksRepo extends JpaRepository<Feedback, Integer> {
   List<Feedback> findAllByTechId(int techId);
   Feedback findByBookingId(int bookingId);
}