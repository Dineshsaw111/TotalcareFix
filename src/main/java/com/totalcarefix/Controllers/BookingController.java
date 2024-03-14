package com.totalcarefix.Controllers;

import com.totalcarefix.DTO.RequestBooking;
import com.totalcarefix.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @PostMapping("/bookingadd")
    public ResponseEntity<String> bookingAdd(@RequestBody RequestBooking requestBooking)
    {
        try {
            return new ResponseEntity<>(bookingService.bookingAdd(requestBooking), HttpStatus.OK);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>("Unable to add booking", HttpStatus.NOT_MODIFIED);
        }
    }
}