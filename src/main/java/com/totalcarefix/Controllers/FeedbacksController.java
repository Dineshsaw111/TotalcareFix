package com.totalcarefix.Controllers;

import com.totalcarefix.DTO.RequestFeedbacks;
import com.totalcarefix.Services.FeedbacksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
public class FeedbacksController {
    @Autowired
    FeedbacksService feedbacksService;

    @PostMapping("/feedbacksave")
    public ResponseEntity<String> feedbacksave(@RequestBody RequestFeedbacks requestFeedbacks)
    {
        try {
            return new ResponseEntity<>(feedbacksService.addFeedback(requestFeedbacks), HttpStatus.OK);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>("Unable to add feedback", HttpStatus.NOT_MODIFIED);
        }
    }
}
