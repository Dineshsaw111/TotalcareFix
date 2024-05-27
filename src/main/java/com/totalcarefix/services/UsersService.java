package com.totalcarefix.services;

import com.totalcarefix.dto.*;
import com.totalcarefix.entities.*;
import com.totalcarefix.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private AddressesRepo addressesRepo;
    @Autowired
    private ContactsRepo contactsRepo;
    @Autowired
    private rolesRepo rolesRepo1;
    @Autowired
    private CitiesRepo citiesRepo;
    @Autowired
    private SkillsRepo skillsRepo;
    @Autowired
    private TechniciansRepo techniciansRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private  BookingRepo bookingRepo;

    @Autowired
    private FeedbacksRepo feedbacksRepo;

    public ResponseEntity<UserBookingResponse> techBooking(UserBookingRequest userBookingRequest){
        Optional<Users> optionalUsers = Optional.ofNullable(usersRepo.findByEmail(userBookingRequest.getEmail()));
        Users users = optionalUsers.orElse(null);
        int addId=0;
        List<Addresses> addressesArrayList=new ArrayList<>();
        addressesRepo.findAll().forEach(addressesArrayList::add);
        for (Addresses addresses :addressesArrayList){
            if(addresses.getUser_id()==(users.getUser_id())){
                addId=addresses.getAddress_id();
            }
        }

        Skills skill=skillsRepo.findByName(userBookingRequest.getSkill());

        Booking booking=Booking.builder()
                .bookerId(users.getUser_id())
                .statusId(1)
                .skillId(skill.getSkill_id())
                .message(userBookingRequest.getMessage())
                .serviceDate(userBookingRequest.getServiceDate())
                .expectedTime(userBookingRequest.getTime())
                .addressId(addId)
                .logtime(Timestamp.from(Instant.now()))
                .build();
           booking= bookingRepo.save(booking);

        Status status=statusRepo.findById(1).get();
        UserBookingResponse userBookingResponse=UserBookingResponse.builder()
                .BookingId(booking.getBookingId())
                .date(userBookingRequest.getServiceDate())
                .skill(userBookingRequest.getSkill())
                .time(userBookingRequest.getTime())
                .status(status.getName())
                .message(booking.getMessage())
                .feedbackId(0)
                .build();

        return new ResponseEntity<>(userBookingResponse, HttpStatus.OK);    }



    public ResponseEntity<List<UserBookingResponse>> allBooking(String email) {
        Optional<Users> optionalUser = Optional.ofNullable(usersRepo.findByEmail(email));
        Users user = optionalUser.orElse(null);
        int id=user.getUser_id();
        List<Booking> bookingList=new ArrayList<>();
        bookingRepo.findAll().forEach(bookingList::add);

        List<UserBookingResponse> userBookingResponses = new ArrayList<>();

        int feebackId=0;
        if (!bookingList.isEmpty()) {
            for (Booking booking : bookingList){
               Status status= statusRepo.findById(booking.getStatusId()).get();

               Skills skill=skillsRepo.findById(booking.getSkillId()).get();

               Optional<Feedback> feedbackOptional=Optional.ofNullable(feedbacksRepo.findByBookingId(booking.getBookingId()));
               if(feedbackOptional.isEmpty()){
                   feebackId=0;
               }
               else{
                   feebackId=1;
               }

                UserBookingResponse userBookingResponse=UserBookingResponse.builder()
                        .BookingId(booking.getBookingId())
                        .message(booking.getMessage())
                        .skill(skill.getName())
                        .date(booking.getServiceDate())
                        .time(booking.getExpectedTime())
                        .status(status.getName())
                        .feedbackId(feebackId)
                        .build();
                if(id==booking.getBookerId()){
                 //   if(id==booking.getBookerId() && booking.getStatusId()!=3){

                        userBookingResponses.add(userBookingResponse);
                }

            }
            return new ResponseEntity<>(userBookingResponses, HttpStatus.OK);
        } else {
         //   Users users1=new Users("mesaage"+email,"not found");
            return new ResponseEntity<>(userBookingResponses, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<RegisterResponse> registerUser(RegisterRequest registerRequest) {
//        Optional<Users> optionalUser = Optional.ofNullable(usersRepo.findByEmail(registerRequest.getEmail()));
//        Users user = optionalUser.orElse(null);
//        if(user!=null){
        Optional<Users> userOptional = Optional.ofNullable(usersRepo.findByEmail(registerRequest.getEmail()));

        if(userOptional.isEmpty()) {

            // rolesRepo1 find role ny role_id
            int roleid = 0;
            List<roles> Allroles = new ArrayList<>();
            rolesRepo1.findAll().forEach(Allroles::add);
            for (roles role1 : Allroles) {
                if (role1.getName().equals(registerRequest.getRole())) {
                    roleid = role1.getRole_id();
                }
            }
            Users user = Users.builder()
                    .first_name(registerRequest.getFirstName())
                    .last_name(registerRequest.getLastName())
                    .email(registerRequest.getEmail())
                    .role_id(roleid)
                    .status_id(1)
                    .creation_time(Timestamp.from(Instant.now()))
                    .build();
            user = usersRepo.save(user);

            Contacts contact = Contacts.builder()
                    .contact_number(registerRequest.getContact())
                    .userId(user.getUser_id())
                    .build();

            int cityid = 0;
            List<Cities> citylist = new ArrayList<>();
            citiesRepo.findAll().forEach(citylist::add);
            for (Cities obj : citylist) {
                if (obj.getName().equals(registerRequest.getCity())) {
                    cityid = obj.getCity_id();
                }
            }

            Addresses address = Addresses.builder()
                    .user_id(user.getUser_id())
                    .house_number(registerRequest.getHouseNo())
                    .street(registerRequest.getStreet())
                    .society(registerRequest.getSociety())
                    .locality(registerRequest.getLocality())
                    .pincode(registerRequest.getPincode())
                    .city_id(cityid)
                    .address_type("fixed")
                    .build();
            addressesRepo.save(address);

            int skillid = 0;
            List<Skills> skillist = new ArrayList<>();
            skillsRepo.findAll().forEach(skillist::add);
            for (Skills obj : skillist) {
                if (obj.getName().equals(registerRequest.getSkill())) {
                    skillid = obj.getSkill_id();
                }
            }

            Contacts contact1 = Contacts.builder()
                    .userId(user.getUser_id())
                    .contact_number(registerRequest.getContact())
                    .build();
            contactsRepo.save(contact1);

            if (("Technician").equals(registerRequest.getRole())) {
                Technicians tech1 = Technicians.builder()
                        .tech_id(user.getUser_id())
                        .skill_id(skillid).build();
                techniciansRepo.save(tech1);
            }

            RegisterResponse registerResponse=RegisterResponse.builder()
                    .state(false)
                    .message("not exist")
                    .role("none")
                    .build();

            return new ResponseEntity<>(registerResponse, HttpStatus.OK);

        }else{
            Optional<Users> userOption = Optional.ofNullable(usersRepo.findByEmail(registerRequest.getEmail()));
            Users users = userOption.orElse(null);

            roles role=rolesRepo1.findById(users.getRole_id()).get();
            RegisterResponse registerResponse=RegisterResponse.builder()
                    .state(true)
                    .message("exist")
                    .role(role.getName())
                    .build();
            return new ResponseEntity<>(registerResponse, HttpStatus.ALREADY_REPORTED);
        }

    }
    public ResponseEntity<String> cancelBook(int bookId) {
        Booking booking=bookingRepo.findById(bookId).get();
        booking.setStatusId(3);
        bookingRepo.save(booking);
        return new ResponseEntity<>("updated",HttpStatus.OK);
    }

    public ResponseEntity<Feedback> giveFeedback(FeedbackRequest feedbackRequest) {
       Booking booking=bookingRepo.findById(feedbackRequest.getBookingId()).get();


        Feedback feedback= Feedback.builder()
                .bookingId(feedbackRequest.getBookingId())
                .user_id(booking.getBookerId())
                .techId(booking.getTechId())
//                .tech_id(user.getUser_id())
                .message(feedbackRequest.getMessage())
                .rating(feedbackRequest.getRating())
                .creation_time(Timestamp.from(Instant.now()))
                .build();
        feedback=feedbacksRepo.save(feedback);

     return  new ResponseEntity<>(feedback,HttpStatus.OK);
    }

    public ResponseEntity<Booking> editMyBooking(int bookingId,UpdateBookingRequest updateBookingRequest) {
            Booking booking=bookingRepo.findById(bookingId).get();
//          Skills skill= skillsRepo.findByName(userBookingRequest.getSkill());
//          booking.setSkillId(skill.getSkill_id());
          booking.setMessage(updateBookingRequest.getMessage());
          booking.setServiceDate(updateBookingRequest.getServiceDate());
          booking.setExpectedTime(updateBookingRequest.getTime());

         booking= bookingRepo.save(booking);
            return new ResponseEntity<>(booking,HttpStatus.OK);
    }

    public ResponseEntity<Booking> completed(int bookingId) {
        Booking booking=bookingRepo.findById(bookingId).get();
        booking.setStatusId(4);

        booking=bookingRepo.save(booking);
        return new ResponseEntity<>(booking,HttpStatus.OK);
    }
}
