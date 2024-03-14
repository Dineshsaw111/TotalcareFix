package com.totalcarefix.Services;

import com.totalcarefix.DTO.RequestFeedbacks;
import com.totalcarefix.Entities.Feedback;
import com.totalcarefix.Repos.FeedbacksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class FeedbacksService {
    @Autowired
    private FeedbacksRepo feedbacksRepo;

    public String addFeedback(RequestFeedbacks requestFeedbacks) {
        try {
            Feedback feedback = Feedback.builder()
                    .user_id(requestFeedbacks.getUser_id())
                    .tech_id(requestFeedbacks.getTech_id())
                    .message(requestFeedbacks.getMessage())
                    .rating(requestFeedbacks.getRating())
                    .creation_time(Timestamp.from(Instant.now()))
                    .build();

            feedbacksRepo.save(feedback);
            return "Thanks for your feedback";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Feedback fails to store! Please try again";
        }
    }
}

