package com.totalcarefix.services;

import com.totalcarefix.dto.AddressResponse;
import com.totalcarefix.dto.ServiceResponse;
import com.totalcarefix.entities.*;
import com.totalcarefix.exception.InvalidBookingId;
import com.totalcarefix.exception.InvalidEmailException;
import com.totalcarefix.repos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private CitiesRepo citiesRepo;

    @Autowired
    private FeedbacksRepo feedbacksRepo;

    private Logger log = LoggerFactory.getLogger(UsersService.class);

    public ResponseEntity<List<ServiceResponse>> getAllOpensevice(String email) {
        Optional<Users> optionalUsers = Optional.ofNullable(usersRepo.findByEmail(email));
        Users user = optionalUsers.orElse(null);
        //   Users user=usersRepo.findByEmail(email);
        if (optionalUsers.isEmpty()) {
            log.error("invalid email");
            throw new InvalidEmailException("Invalid email");
        } else {
            Optional<Technicians> optionalTechnician = techniciansRepo.findById(user.getUser_id());
            Technicians technician = optionalTechnician.orElse(null);

            List<Booking> bookingList = new ArrayList<>();
            bookingRepo.findAllBySkillId(technician.getSkill_id()).forEach(booking -> bookingList.add(booking));

            //List<Addresses> addressesList=new ArrayList<>();
            List<ServiceResponse> serviceResponseList = new ArrayList<>();
            if (bookingList != null) {
                for (Booking booking : bookingList) {
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
            } else {
                return new ResponseEntity<>(serviceResponseList, HttpStatus.NOT_FOUND);

            }
        }
    }

    public ResponseEntity<Booking> serviceBooked(int id, String email) {
        Optional<Users> optionalUsers = Optional.ofNullable(usersRepo.findByEmail(email));
        Users user = optionalUsers.orElse(null);
        if (optionalUsers.isEmpty()) {
            log.error("invalid email");
            throw new InvalidEmailException("Invalid email");
        } else {
            Optional<Booking> optionalBooking = Optional.ofNullable((bookingRepo.findById(id))).get();
            Booking booking = optionalBooking.orElse(null);
            if (optionalBooking.isEmpty()) {
                throw new InvalidBookingId("this booking_id is not found");
            } else {
//                if(booking.getTechId()!=null){
//                    throw new InvalidBookingId("this booking_id is not found already booked");
//                }
//                else {
                    //   Booking booking=bookingRepo.findById(id).get();
                    if (booking.getStatusId() == 1) {
                        booking.setTechId(user.getUser_id());
                        booking.setStatusId(2);
                        booking = bookingRepo.save(booking);
                    }
                    return new ResponseEntity<>(booking, HttpStatus.OK);
            //    }
            }
        }
    }

    public ResponseEntity<List<ServiceResponse>> getMyOrders(String email) {
        Optional<Users> optionalUsers = Optional.ofNullable(usersRepo.findByEmail(email));
        Users user = optionalUsers.orElse(null);
        if (optionalUsers.isEmpty()) {
            log.error("invalid email");
            throw new InvalidEmailException("Invalid email");
        }else {
            List<Booking> bookingList = bookingRepo.findAllByTechId(user.getUser_id());

            List<ServiceResponse> serviceResponseList = new ArrayList<>();
            for (Booking booking : bookingList) {
                if (booking.getStatusId() == 2) {
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

            return new ResponseEntity<>(serviceResponseList, HttpStatus.OK);
        }
    }

    public ResponseEntity<Booking> cancelOrder(int bookingId) {
        Optional<Booking> optionalBooking = Optional.ofNullable((bookingRepo.findById(bookingId))).get();
        Booking bookings = optionalBooking.orElse(null);
        if (optionalBooking.isEmpty()) {
            throw new InvalidBookingId("this booking_id is not found");
        }
        else {
            Booking booking = bookingRepo.findById(bookingId).get();
            if(booking.getStatusId()==2) {
                log.debug("2");
                booking.setStatusId(1);
                Booking bookingResponse = bookingRepo.save(booking);
                return new ResponseEntity<>(bookingResponse, HttpStatus.OK);
            }else{
                throw new InvalidBookingId("this booking_id is not found at confirm status");
            }
        }
    }

    public ResponseEntity<Double> techRating(String email) {
        Optional<Users> optionalUser = Optional.ofNullable(usersRepo.findByEmail(email));
        Users user = optionalUser.orElse(null);

        if (optionalUser.isEmpty()) {
            log.error("invalid email");
            throw new InvalidEmailException("Invalid email");
        }else {
            List<Feedback> feedbackList = new ArrayList<>();
            feedbacksRepo.findAllByTechId(user.getUser_id()).forEach(feed -> feedbackList.add(feed));
            int size = feedbackList.size();
            double rating = 0;
            if (size != 0) {
                for (Feedback feedback : feedbackList) {
                    rating = rating + feedback.getRating();
                }
                rating = rating / size;
            }
            return new ResponseEntity<>(rating, HttpStatus.OK);
        }
    }

    public ResponseEntity<Integer> myTasked(String email) {
        Optional<Users> optionalUser = Optional.ofNullable(usersRepo.findByEmail(email));
        Users user = optionalUser.orElse(null);
        log.debug("user1");
        if (optionalUser.isEmpty()) {
            log.error("invalid email");
            throw new InvalidEmailException("Invalid email");
        }else {
            log.debug("user2");

            List<Booking> bookingList = bookingRepo.findAllByTechId(user.getUser_id());

            //   List<Booking> myList=new ArrayList<>();
            int tasked = 0;
            for (Booking booking : bookingList) {
                if (booking.getStatusId() == 4) {
                    tasked = tasked + 1;
                }
            }
            return new ResponseEntity<>(tasked, HttpStatus.OK);
        }

    }
}
