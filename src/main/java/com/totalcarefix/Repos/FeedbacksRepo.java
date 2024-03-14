package com.totalcarefix.Repos;

import com.totalcarefix.Entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbacksRepo extends JpaRepository<Feedback, Integer> {
}