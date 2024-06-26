package com.totalcarefix.controllers;

import com.totalcarefix.dto.ServiceResponse;
import com.totalcarefix.entities.Booking;
import com.totalcarefix.services.TechnaciansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tech")
public class TechnicianController {
    @Autowired
    private TechnaciansService technaciansService;

    @GetMapping("/serviceorder/{email}")
    public ResponseEntity<List<ServiceResponse>> serviceOrder(@PathVariable String email){

        return technaciansService.getAllOpensevice(email);
    }

    @GetMapping("/confirm/{id}/{email}")
    public ResponseEntity<Booking>confirmService(@PathVariable int id, @PathVariable String email){
        return  technaciansService.serviceBooked(id,email);
    }
    @GetMapping("/myorder/{email}")
    public  ResponseEntity<List<ServiceResponse>>myOrder(@PathVariable String email){
        return technaciansService.getMyOrders(email);
    }
    @GetMapping("/cancel/{bookingId}")
    public  ResponseEntity<Booking> cancelBooking(@PathVariable int bookingId){
        return technaciansService.cancelOrder(bookingId);
    }
    @GetMapping("/rating/{email}")
    public  ResponseEntity<Double> rating(@PathVariable String email){
        return technaciansService.techRating(email);
    }
    @GetMapping("/taskcompleted/{email}")
    public ResponseEntity<Integer> taskCompleted(@PathVariable String email){
        return technaciansService.myTasked(email);
    }
}
