package com.totalcarefix.Services;

import com.totalcarefix.DTO.RequestCities;
import com.totalcarefix.DTO.RequestSkills;
import com.totalcarefix.Entities.Cities;
import com.totalcarefix.Entities.Skills;
import com.totalcarefix.Repos.CitiesRepo;
import com.totalcarefix.Repos.SkillsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class CitiesService  {
    @Autowired
    private CitiesRepo citiesRepo;
    public  String addCities(RequestCities requestCities){
        String CheckNull=requestCities.getName();
        if(requestCities.getName().isEmpty()){

            return "null object";
        }
        else {
            List<Cities> mylist=new ArrayList<>();
            citiesRepo.findAll().forEach(mylist::add);
            for (Cities obj :mylist){
                if(obj.getName().equals(CheckNull)){
                    // System.out.println("hii");
                    return "Already exist";
                }
            }
            Cities user1=Cities.builder()
                    .state_id(requestCities.getState_id())
                    .name(requestCities.getName()).build();
            citiesRepo.save(user1);

            return "added"+CheckNull;

        }
    }
    public List<Cities> getAllCities(){
        List<Cities> mylist=new ArrayList<>();
        citiesRepo.findAll().forEach(mylist::add);
        return mylist;
    }

    public boolean deleteCities(int id){
        boolean check=false;
        check=citiesRepo.existsById(id);
        if(check){
            citiesRepo.deleteById(id);
            return check;
        }
        else{
            return false;
        }
    }
}

