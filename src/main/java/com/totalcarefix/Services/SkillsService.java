package com.totalcarefix.Services;

import com.totalcarefix.DTO.RequestSkills;
import com.totalcarefix.DTO.RequestUsersStatus;
import com.totalcarefix.Entities.Skills;
import com.totalcarefix.Entities.UsersStatus;
import com.totalcarefix.Repos.SkillsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillsService {
    @Autowired
    private SkillsRepo skillsRepo;
    public  String addSkills(RequestSkills requestSkills){
        String CheckNull=requestSkills.getName();
        if(requestSkills.getName().isEmpty()){

            return "null object";
        }
        else {
            List<Skills> mylist=new ArrayList<>();
            skillsRepo.findAll().forEach(mylist::add);
            for (Skills obj :mylist){
                if(obj.getName().equals(CheckNull)){
                    // System.out.println("hii");
                    return "Already exist";
                }
            }
            Skills user1=Skills.builder().name(requestSkills.getName()).build();
            skillsRepo.save(user1);

            return "added"+CheckNull;

        }
    }
    public List<Skills> getAllSkills(){
        List<Skills> mylist=new ArrayList<>();
        skillsRepo.findAll().forEach(mylist::add);
        return mylist;
    }

    public boolean deleteSkills(int id){
        boolean check=false;
        check=skillsRepo.existsById(id);
        if(check){
            skillsRepo.deleteById(id);
            return check;
        }
        else{
            return false;
        }
    }
}
