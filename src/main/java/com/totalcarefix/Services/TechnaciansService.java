package com.totalcarefix.Services;

import com.totalcarefix.DTO.AddressResponse;
import com.totalcarefix.DTO.ServiceResponse;
import com.totalcarefix.Entities.*;
import com.totalcarefix.Repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TechnaciansService {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private TechniciansRepo techniciansRepo;

    @Autowired
    private SkillsRepo skillsRepo;

    @Autowired
    private AddressesRepo addressesRepo;

    @Autowired
    private ContactsRepo contactsRepo;

    @Autowired
    private StatesRepo statesRepo;

    @Autowired
    private  CitiesRepo citiesRepo;

    @Autowired
    private FeedbacksRepo feedbacksRepo;
    public ResponseEntity<List<ServiceResponse>> getAllOpensevice(String email) {
        Users user=usersRepo.findByEmail(email);

        Optional<Technicians> optionalTechnician=techniciansRepo.findById(user.getUser_id());
        Technicians technician = optionalTechnician.orElse(null);

        List<Booking> bookingList=new ArrayList<>();
        bookingRepo.findAllBySkillId(technician.getSkill_id()).forEach(booking -> bookingList.add(booking));

        //List<Addresses> addressesList=new ArrayList<>();
        List<ServiceResponse> serviceResponseList=new ArrayList<>();
        if(bookingList!=null){
        for (Booking booking :bookingList) {
            if (booking.getStatusId() == 1) {
                Optional<Addresses> optionalAddress = addressesRepo.findById(booking.getAddressId());
                Addresses address = optionalAddress.orElse(null);

                Optional<Users> optionalUser = usersRepo.findById(booking.getBookerId());
                Users myUser = optionalUser.get();

                String name = myUser.getFirst_name() + myUser.getLast_name();

                Optional<Contacts> optionalContact = Optional.ofNullable(contactsRepo.findByUserId(booking.getBookerId()));
                Contacts contact = optionalContact.get();

                Optional<Cities> optionalCity = citiesRepo.findById(address.getCity_id());
                Cities city = optionalCity.get();

                Optional<States> optionState = statesRepo.findById(city.getState_id());
                States state = optionState.get();

                AddressResponse addressResponse = AddressResponse.builder()
                        .houseNo(address.getHouse_number())
                        .street(address.getStreet())
                        .society(address.getSociety())
                        .state(state.getName())
                        .city(city.getName())
                        .locality(address.getLocality())
                        .build();

                ServiceResponse serviceResponse = ServiceResponse.builder()
                        .bookingId(booking.getBookingId())
                        .name(name)
                        .mobileNumber(contact.getContact_number())
                        .serviceDate(booking.getServiceDate())
                        .expectedTime(booking.getExpectedTime())
                        .message(booking.getMessage())
                        .addressResponse(addressResponse)
                        .build();
                serviceResponseList.add(serviceResponse);

             }
          }
            return new ResponseEntity<>(serviceResponseList, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(serviceResponseList,HttpStatus.NOT_FOUND);

        }
    }

    public ResponseEntity<Booking> serviceBooked(int id, String email) {
        Optional<Users> optionalUsers = Optional.ofNullable(usersRepo.findByEmail(email));
        Users user = optionalUsers.orElse(null);
        Booking booking=bookingRepo.findById(id).get();
        if(booking.getStatusId()==1) {
            booking.setTechId(user.getUser_id());
            booking.setStatusId(2);
             booking = bookingRepo.save(booking);
        }
            return new ResponseEntity<>(booking, HttpStatus.OK);

    }

    public ResponseEntity<List<ServiceResponse>> getMyOrders(String email) {
        Optional<Users> optionalUsers = Optional.ofNullable(usersRepo.findByEmail(email));
        Users user = optionalUsers.orElse(null);

        List<Booking> bookingList=bookingRepo.findAllByTechId(user.getUser_id());

        List<ServiceResponse> serviceResponseList=new ArrayList<>();
        for (Booking booking:bookingList){
            if(booking.getStatusId()==2) {
                Contacts contact = contactsRepo.findByUserId(booking.getBookerId());
                Addresses address = addressesRepo.findById(booking.getAddressId()).get();
                Users users = usersRepo.findById(booking.getBookerId()).get();
                String name = users.getFirst_name() + users.getLast_name();

                Cities city = citiesRepo.findById(address.getCity_id()).get();
                States state = statesRepo.findById(city.getState_id()).get();

                AddressResponse addressResponse = AddressResponse.builder()
                        .locality(address.getLocality())
                        .city(city.getName())
                        .state(state.getName())
                        .society(address.getSociety())
                        .street(address.getStreet())
                        .houseNo(address.getHouse_number())
                        .build();

                ServiceResponse serviceResponse = ServiceResponse.builder()
                        .name(name)
                        .serviceDate(booking.getServiceDate())
                        .expectedTime(booking.getExpectedTime())
                        .message(booking.getMessage())
                        .bookingId(booking.getBookingId())
                        .mobileNumber(contact.getContact_number())
                        .addressResponse(addressResponse)
                        .build();
                serviceResponseList.add(serviceResponse);
            }
        }

        return new ResponseEntity<>(serviceResponseList,HttpStatus.OK);
    }

    public ResponseEntity<Booking> cancelOrder(int bookingId) {
        Booking booking=bookingRepo.findById(bookingId).get();
        booking.setStatusId(3);
        Booking bookingResponse=bookingRepo.save(booking);
        return  new ResponseEntity<>(bookingResponse,HttpStatus.OK);
    }

    public ResponseEntity<Double> techRating(String email) {
        Optional<Users> optionalUser = Optional.ofNullable(usersRepo.findByEmail(email));
        Users user = optionalUser.orElse(null);

        List<Feedback> feedbackList=new ArrayList<>();
        feedbacksRepo.findAllByTechId(user.getUser_id()).forEach(feed->feedbackList.add(feed));
        int size= feedbackList.size();
        double rating=0;
        if(size!=0){
            for (Feedback feedback:feedbackList){
                rating=rating+feedback.getRating();
            }
           rating=rating/size;
        }
        return  new ResponseEntity<>(rating,HttpStatus.OK);
    }
    public ResponseEntity<Integer> myTasked(String email) {
        Optional<Users> optionalUser = Optional.ofNullable(usersRepo.findByEmail(email));
        Users user = optionalUser.orElse(null);

        List<Booking> bookingList=bookingRepo.findAllByTechId(user.getUser_id());

     //   List<Booking> myList=new ArrayList<>();
        int tasked=0;
        for(Booking booking:bookingList){
            if(booking.getStatusId()==4){
                tasked=tasked+1;
            }
        }
        return new ResponseEntity<>(tasked,HttpStatus.OK);

    }
}
