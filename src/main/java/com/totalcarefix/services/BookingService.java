package com.totalcarefix.services;

import com.totalcarefix.dto.RequestBooking;
import com.totalcarefix.entities.Booking;
import com.totalcarefix.repos.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class BookingService {
    @Autowired
    private BookingRepo bookingRepo;

    public String bookingAdd(RequestBooking requestBooking)
    {
        try {
            Booking booking=new Booking();
            booking.setBookerId(requestBooking.getBookerId());
            booking.setTechId(requestBooking.getTechId());
            booking.setStatusId(requestBooking.getStatusId());
            booking.setAddressId(requestBooking.getAddressId());
            booking.setMessage(requestBooking.getMessage());
            booking.setServiceDate(requestBooking.getServiceDate());
            booking.setExpectedTime(requestBooking.getExpectedTime());
            booking.setLogtime(Timestamp.from(Instant.now()));
            bookingRepo.save(booking);
            return "Booking Successful";
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return "Booking Fail";
        }
    }
}