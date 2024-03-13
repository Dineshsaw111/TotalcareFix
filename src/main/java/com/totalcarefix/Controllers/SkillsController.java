package com.totalcarefix.Controllers;

import com.totalcarefix.DTO.RequestSkills;
import com.totalcarefix.DTO.RequestUsersStatus;
import com.totalcarefix.Entities.Skills;
import com.totalcarefix.Entities.UsersStatus;
import com.totalcarefix.Services.SkillsService;
import com.totalcarefix.Services.UsersStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/skills")
public class SkillsController {

    @Autowired
    SkillsService skillsService;
    @PostMapping("/add")
    public ResponseEntity<String> addSkills1(@RequestBody RequestSkills requestSkills){
        String message=skillsService.addSkills(requestSkills);
        if(message.equals("null object")){
            return new ResponseEntity<>("null object can not be added", HttpStatus.OK);
        }
        else if(message.equals("Already exist")){
            return new ResponseEntity<>(" already there", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("added", HttpStatus.CREATED);
        }
    }

    @GetMapping("/getAllUserStatus")
    public ResponseEntity<List<Skills>> allSkills(){
        return new ResponseEntity<>(skillsService.getAllSkills(),HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserStatus/{id}")
    public ResponseEntity<String> deleteSkills(@PathVariable int id){
        boolean check=skillsService.deleteSkills(id);
        if(check){
            return new ResponseEntity<>("one role deleted", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("no role exist", HttpStatus.OK);
        }
    }
}
