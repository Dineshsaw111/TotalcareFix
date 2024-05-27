package com.totalcarefix.services;

import com.totalcarefix.dto.RequestStates;
import com.totalcarefix.entities.States;
import com.totalcarefix.repos.StatesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatesService  {
    @Autowired
    private StatesRepo statesRepo;
    public  String addState(RequestStates requestStates){
        String CheckNull=requestStates.getName();
        if(requestStates.getName().isEmpty()){

            return "null object";
        }
        else {
            List<States> mylist=new ArrayList<>();
            statesRepo.findAll().forEach(mylist::add);
            for (States obj :mylist){
                if(obj.getName().equals(CheckNull)){
                    // System.out.println("hii");
                    return "Already exist";
                }
            }
            States user1=States.builder().name(requestStates.getName()).build();
            statesRepo.save(user1);

            return "added"+CheckNull;

        }
    }
    public List<States> getAllState(){
        List<States> mylist=new ArrayList<>();
        statesRepo.findAll().forEach(mylist::add);
        return mylist;
    }

    public boolean deleteState(int id){
        boolean check=false;
        check=statesRepo.existsById(id);
        if(check){
            statesRepo.deleteById(id);
            return check;
        }
        else{
            return false;
        }
    }
}
