package com.totalcarefix.Controllers;

import com.totalcarefix.DTO.FeedbackRequest;
import com.totalcarefix.DTO.RegisterRequest;
import com.totalcarefix.DTO.UserBookingRequest;
import com.totalcarefix.DTO.UserBookingResponse;
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

//    public ResponseEntity<Users> register(){
//
//        return
//    }
    @PostMapping("/userbooking")
    public ResponseEntity<UserBookingResponse> userBooking(@RequestBody UserBookingRequest userBookingRequest){
        //if(userBookingRequest.getMessage().isEmpty() && userBookingRequest.getEmail().isEmpty())
        System.out.println("booking controller");
        return usersService.techBooking(userBookingRequest);
    }

    @GetMapping("/showbooking/{email}")
    public ResponseEntity<List<UserBookingResponse>> showBooking(@PathVariable String email) {
        return usersService.allBooking(email);
    }


    @PostMapping("/register")
    public ResponseEntity<RegisterRequest> register(@RequestBody RegisterRequest registerRequest){
        return usersService.registerUser(registerRequest);
    }

    @PostMapping("/cancel/{bookId}")
    public ResponseEntity<String> cancel(@PathVariable int bookId){
        return usersService.cancelBook(bookId);
    }

    @PostMapping("/editbooking/{bookId}")
    public ResponseEntity<Booking> editBooking(@PathVariable int bookId, @RequestBody UserBookingRequest userBookingRequest){
        //if(userBookingRequest.getMessage().isEmpty() && userBookingRequest.getEmail().isEmpty())
        System.out.println("for edit");
        return usersService.editMyBooking(bookId,userBookingRequest);
    }
    @PostMapping("/feedbacks")
    public  ResponseEntity<Feedback>feedback(@RequestBody FeedbackRequest feedbackRequest){
        return usersService.giveFeedback(feedbackRequest);
    }
    @PostMapping("/bookingcompleted/{bookingId}")
    public ResponseEntity<Booking> bookingCompleted(@PathVariable int bookingId){
        return  usersService.completed(bookingId);
    }
}
