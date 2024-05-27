package com.totalcarefix.Controllers;

import com.totalcarefix.DTO.*;
import com.totalcarefix.Entities.Booking;
import com.totalcarefix.Entities.Feedback;
import com.totalcarefix.Entities.Users;
import com.totalcarefix.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/userbooking")
    public ResponseEntity<UserBookingResponse> userBooking(@RequestBody UserBookingRequest userBookingRequest) {
        return usersService.techBooking(userBookingRequest);
    }

    @GetMapping("/showbooking/{email}")
    public ResponseEntity<List<UserBookingResponse>> showBooking(@PathVariable String email) {
        return usersService.allBooking(email);
    }


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        return usersService.registerUser(registerRequest);
    }

    @PostMapping("/cancel/{bookId}")
    public ResponseEntity<String> cancel(@PathVariable int bookId) {
        return usersService.cancelBook(bookId);
    }

    @PostMapping("/editbooking/{bookId}")
    public ResponseEntity<Booking> editBooking(@PathVariable int bookId, @RequestBody UpdateBookingRequest updateBookingRequest) {
        //if(userBookingRequest.getMessage().isEmpty() && userBookingRequest.getEmail().isEmpty())
        System.out.println("for edit");
        return usersService.editMyBooking(bookId, updateBookingRequest);
    }

    @PostMapping("/feedbacks")
    public ResponseEntity<Feedback> feedback(@RequestBody FeedbackRequest feedbackRequest) {
        return usersService.giveFeedback(feedbackRequest);
    }

    @GetMapping("/bookingcompleted/{bookingId}")
    public ResponseEntity<Booking> bookingCompleted(@PathVariable int bookingId) {
        return usersService.completed(bookingId);
    }
}
