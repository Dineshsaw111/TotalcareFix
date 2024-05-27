package com.totalcarefix.Controllers;

import com.totalcarefix.DTO.RequestStates;
import com.totalcarefix.Entities.States;
import com.totalcarefix.Services.StatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/States")
public class StatesController {

    @Autowired
    StatesService statesService;
    @PostMapping("/add")
    public ResponseEntity<String> addStates1(@RequestBody RequestStates requestStates){
        String message=statesService.addState(requestStates);
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

    @GetMapping("/getAllStates")
    public ResponseEntity<List<States>> allStates(){
        return new ResponseEntity<>(statesService.getAllState(),HttpStatus.OK);
    }

    @DeleteMapping("/deleteStates/{id}")
    public ResponseEntity<String> deleteStates(@PathVariable int id){
        boolean check=statesService.deleteState(id);
        if(check){
            return new ResponseEntity<>("one state deleted", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("no state exist", HttpStatus.OK);
        }
    }
}