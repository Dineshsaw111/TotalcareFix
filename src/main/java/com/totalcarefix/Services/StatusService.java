package com.totalcarefix.Services;

import com.totalcarefix.DTO.RequestStates;
import com.totalcarefix.DTO.RequestStatus;
import com.totalcarefix.Entities.States;
import com.totalcarefix.Entities.Status;
import com.totalcarefix.Repos.StatesRepo;
import com.totalcarefix.Repos.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;





@Service
public class StatusService   {
    @Autowired
    private StatusRepo statusRepo;
    public  String addStatus(RequestStatus requestStatus){
        String CheckNull=requestStatus.getName();
        if(requestStatus.getName().isEmpty()){

            return "null object";
        }
        else {
            List<Status> mylist=new ArrayList<>();
            statusRepo.findAll().forEach(mylist::add);
            for (Status obj :mylist){
                if(obj.getName().equals(CheckNull)){
                    // System.out.println("hii");
                    return "Already exist";
                }
            }
            Status user1=Status.builder().name(requestStatus.getName()).build();
            statusRepo.save(user1);

            return "added"+CheckNull;

        }
    }
    public List<Status> getAllStatus(){
        List<Status> mylist=new ArrayList<>();
        statusRepo.findAll().forEach(mylist::add);
        return mylist;
    }

    public boolean deleteStatus(int id){
        boolean check=false;
        check=statusRepo.existsById(id);
        if(check){
            statusRepo.deleteById(id);
            return check;
        }
        else{
            return false;
        }
    }
}

