package com.totalcarefix.Services;

import com.totalcarefix.DTO.RequestUsersStatus;
import com.totalcarefix.Entities.UsersStatus;
import com.totalcarefix.Entities.roles;
import com.totalcarefix.Repos.UsersStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersStatusService {
    @Autowired
    UsersStatusRepo usersStatusRepo;

    public  String addUsersStatus(RequestUsersStatus requestUsersStatus){
        String CheckNull=requestUsersStatus.getName();
        if(CheckNull==null){
           return "null object";
        }
        else {
            List<UsersStatus> mylist=new ArrayList<>();
            usersStatusRepo.findAll().forEach(mylist::add);
            for (UsersStatus obj :mylist){
                if(obj.getName().equals(CheckNull)){
                    return "Already exist"+CheckNull;
                }
            }
            UsersStatus user1=UsersStatus.builder().name(requestUsersStatus.getName()).build();
            usersStatusRepo.save(user1);

            return "added"+CheckNull;
        }
    }
    public List<UsersStatus> getAllUsersStatus(){
        List<UsersStatus> mylist=new ArrayList<>();
        usersStatusRepo.findAll().forEach(mylist::add);
        return mylist;
    }

    public  UsersStatus getByIDUsersStatus(int id){
        Optional<UsersStatus> userStatus= usersStatusRepo.findById(id);
        return userStatus.orElseGet(() -> new UsersStatus(0, null));
        }
    }

