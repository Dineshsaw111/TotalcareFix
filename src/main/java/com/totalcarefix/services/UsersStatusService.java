package com.totalcarefix.services;

import com.totalcarefix.dto.RequestUsersStatus;
import com.totalcarefix.entities.UsersStatus;
import com.totalcarefix.repos.UsersStatusRepo;
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
        if(requestUsersStatus.getName().isEmpty()){

            return "null object";
        }
        else {
            List<UsersStatus> mylist=new ArrayList<>();
            usersStatusRepo.findAll().forEach(mylist::add);
            for (UsersStatus obj :mylist){
                if(obj.getName().equals(CheckNull)){
                   // System.out.println("hii");
                    return "Already exist";
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


    public boolean deleteUserStatus(int id){
        boolean check=false;
        check=usersStatusRepo.existsById(id);
        if(check){
            usersStatusRepo.deleteById(id);
            return check;
        }
        else{
            return false;
        }
      }

    }



