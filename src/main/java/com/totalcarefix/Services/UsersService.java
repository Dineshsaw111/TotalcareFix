package com.totalcarefix.Services;

import com.totalcarefix.DTO.NewRequestUser;
import com.totalcarefix.Entities.*;
import com.totalcarefix.Repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

}
