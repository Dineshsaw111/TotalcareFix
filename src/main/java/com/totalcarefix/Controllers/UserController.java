package com.totalcarefix.Controllers;

import com.totalcarefix.DTO.RegisterRequest;
import com.totalcarefix.DTO.UserBookingRequest;
import com.totalcarefix.DTO.UserBookingResponse;
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
        return usersService.techBooking(userBookingRequest);
    }

    @GetMapping("/showbooking/{email}")
    public ResponseEntity<List<UserBookingResponse>> showBooking(@RequestParam String email){
        return usersService.allBooking(email);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterRequest> register(@RequestBody RegisterRequest registerRequest){
        return usersService.registerUser(registerRequest);
    }
}
