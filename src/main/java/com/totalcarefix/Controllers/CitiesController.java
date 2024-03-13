package com.totalcarefix.Controllers;

import com.totalcarefix.DTO.RequestCities;
import com.totalcarefix.DTO.RequestSkills;
import com.totalcarefix.Entities.Cities;
import com.totalcarefix.Entities.Skills;
import com.totalcarefix.Services.CitiesService;
import com.totalcarefix.Services.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/cities")
 public class CitiesController  {

    @Autowired
    CitiesService citiesService;
    @PostMapping("/add")
    public ResponseEntity<String> addCities1(@RequestBody RequestCities requestCities){
        String message=citiesService.addCities(requestCities);
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

    @GetMapping("/getAllUserCities")
    public ResponseEntity<List<Cities>> allCities(){
        return new ResponseEntity<>(citiesService.getAllCities(),HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserCities/{id}")
    public ResponseEntity<String> deleteCities(@PathVariable int id){
        boolean check=citiesService.deleteCities(id);
        if(check){
            return new ResponseEntity<>("one role deleted", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("no role exist", HttpStatus.OK);
        }
    }

}