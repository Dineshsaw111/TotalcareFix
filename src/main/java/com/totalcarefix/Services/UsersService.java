package com.totalcarefix.Services;

import com.totalcarefix.DTO.NewRequestUser;
import com.totalcarefix.DTO.RegisterRequest;
import com.totalcarefix.DTO.UserBookingRequest;
import com.totalcarefix.DTO.UserBookingResponse;
import com.totalcarefix.Entities.*;
import com.totalcarefix.Repos.*;
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
    public String addUser(NewRequestUser newRequestUser){
        System.out.println(newRequestUser.getSkill_name());
        if(newRequestUser.getStreet().isEmpty()||newRequestUser.getLast_name().isEmpty()
        ||newRequestUser.getEmail().isEmpty()||newRequestUser.getFirst_name().isEmpty()
        ||newRequestUser.getLocality().isEmpty()||newRequestUser.getHouse_number().isEmpty()
        ||newRequestUser.getPincode().isEmpty()||newRequestUser.getSociety().isEmpty()
        ||newRequestUser.getContact_number().isEmpty()||newRequestUser.getSkill_name().isEmpty()
        ||newRequestUser.getUsertype().isEmpty()||newRequestUser.getCity().isEmpty()){
            System.out.println("hii1");
            return "null object";
        }
        else {
            List<Users> mylist=new ArrayList<>();
            usersRepo.findAll().forEach(mylist::add);
            for (Users obj :mylist){
                if(obj.getEmail().equals(newRequestUser.getEmail())){
                     System.out.println("hii");
                    return "Already exist";
                }
            }

            int statusid=2;
            int roleid=0;
            List<roles> Allroles=new ArrayList<>();
            rolesRepo1.findAll().forEach(Allroles::add);
            for(roles role1:Allroles){
                if(role1.getName().equals(newRequestUser.getUsertype())){
                    roleid=role1.getRole_id();
                }
            }

            Users user1=Users.builder()
                    .first_name(newRequestUser.getFirst_name())
                    .last_name(newRequestUser.getLast_name())
                    .email(newRequestUser.getEmail())
                    .role_id(roleid)
                    .status_id(statusid)
                    .creation_time(Timestamp.from(Instant.now()))
                    .build();
            usersRepo.save(user1);

            int userid=0;
            List<Users> userlist=new ArrayList<>();
            usersRepo.findAll().forEach(userlist::add);
            for (Users obj :userlist){
                if(obj.getEmail().equals(newRequestUser.getEmail())){
                    // System.out.println("hii");
                    userid=obj.getUser_id();
                }
            }

            int cityid=0;
            List<Cities> citylist=new ArrayList<>();
            citiesRepo.findAll().forEach(citylist::add);
            for (Cities obj :citylist){
                if(obj.getName().equals(newRequestUser.getCity())){
                    // System.out.println("hii");
                    cityid=obj.getCity_id();
                }
            }

            Addresses address1=Addresses.builder()
                    .user_id(userid)
                    .house_number(newRequestUser.getHouse_number())
                    .street(newRequestUser.getStreet())
                    .society(newRequestUser.getSociety())
                    .locality(newRequestUser.getLocality())
                    .city_id(cityid)
                    .pincode(newRequestUser.getPincode())
                    .address_type("fixed")
                    .build();
            addressesRepo.save(address1);

            Contacts contact1=Contacts.builder()
                    .user_id(userid)
                    .contact_number(newRequestUser.getContact_number())
                    .build();
            contactsRepo.save(contact1);

            int skillid=0;
            List<Skills> skillist=new ArrayList<>();
            skillsRepo.findAll().forEach(skillist::add);
            for (Skills obj :skillist){
                if(obj.getName().equals(newRequestUser.getSkill_name())){
                    // System.out.println("hii");
                    skillid=obj.getSkill_id();
                }
            }
            if(("Technician").equals(newRequestUser.getUsertype())){

                Technicians tech1= Technicians.builder()
                        .tech_id(userid)
                        .skill_id(skillid ).build();
                System.out.println(userid);
                System.out.println(skillid);

                techniciansRepo.save(tech1);
                System.out.println("hi2");
            }
            return "added";
        }
    }

    //client

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

        Booking booking=Booking.builder()
                .bookerId(users.getUser_id())
                .statusId(1)
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
                .time(userBookingRequest.getTime())
                .status(status.getName())
                .message(booking.getMessage())
                .build();

        return new ResponseEntity<>(userBookingResponse, HttpStatus.OK);    }



    public ResponseEntity<List<UserBookingResponse>> allBooking(String email) {
        Optional<Users> optionalUser = Optional.ofNullable(usersRepo.findByEmail(email));
        Users user = optionalUser.orElse(null);
        int id=user.getUser_id();
        List<Booking> bookingList=new ArrayList<>();
        bookingRepo.findAll().forEach(bookingList::add);

        List<UserBookingResponse> userBookingResponses = new ArrayList<>();

        if (!bookingList.isEmpty()) {
            for (Booking booking : bookingList){
               Status status= statusRepo.findById(booking.getStatusId()).get();
                UserBookingResponse userBookingResponse=UserBookingResponse.builder()
                        .BookingId(booking.getBookingId())
                        .message(booking.getMessage())
                        .date(booking.getServiceDate())
                        .time(booking.getExpectedTime())
                        .status(status.getName())
                        .build();
                if(id==booking.getBookerId()){
                    userBookingResponses.add(userBookingResponse);
                }

            }
            return new ResponseEntity<>(userBookingResponses, HttpStatus.OK);
        } else {
         //   Users users1=new Users("mesaage"+email,"not found");
            return new ResponseEntity<>(userBookingResponses, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<RegisterRequest> registerUser(RegisterRequest registerRequest) {
       String name=registerRequest.getName();
        String[] parts = name.split(" ");
        String firstName=null ;
        String lastName=null ;

        // Check if the string has been split into two parts
        if (parts.length == 2) {
            firstName = parts[0];
             lastName = parts[1];

        } else {
            System.out.println("Invalid format: String does not contain two parts");
        }
       // rolesRepo1 find role ny role_id
        int roleid=0;
        List<roles> Allroles=new ArrayList<>();
        rolesRepo1.findAll().forEach(Allroles::add);
        for(roles role1:Allroles){
            if(role1.getName().equals(registerRequest.getRole())){
                roleid=role1.getRole_id();
            }
        }
        Users user=Users.builder()
                .first_name(firstName)
                .last_name(lastName)
                .email(registerRequest.getEmail())
                .role_id(roleid)
                .status_id(1)
                .creation_time(Timestamp.from(Instant.now()))
                .build();
        user=usersRepo.save(user);

        Contacts contact=Contacts.builder()
                .contact_number(registerRequest.getContact())
                .user_id(user.getUser_id())
                .build();

        int cityid=0;
        List<Cities> citylist=new ArrayList<>();
        citiesRepo.findAll().forEach(citylist::add);
        for (Cities obj :citylist){
            if(obj.getName().equals(registerRequest.getCity())){
                cityid=obj.getCity_id();
            }
        }

        Addresses address=Addresses.builder()
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

        int skillid=0;
        List<Skills> skillist=new ArrayList<>();
        skillsRepo.findAll().forEach(skillist::add);
        for (Skills obj :skillist){
            if(obj.getName().equals(registerRequest.getSkill())){
                skillid=obj.getSkill_id();
            }
        }

        Contacts contact1=Contacts.builder()
                .user_id(user.getUser_id())
                .contact_number(registerRequest.getContact())
                .build();
        contactsRepo.save(contact1);

        if(("Technician").equals(registerRequest.getRole())){
            Technicians tech1= Technicians.builder()
                    .tech_id(user.getUser_id())
                    .skill_id(skillid ).build();
            techniciansRepo.save(tech1);
        }

        return new ResponseEntity<>(registerRequest, HttpStatus.OK);
    }

//    public Optional<Integer> getAddressIdByUserId(int userId) {
//        return addressesRepo.findAddressIdByUserId(userId);
//    }
}
