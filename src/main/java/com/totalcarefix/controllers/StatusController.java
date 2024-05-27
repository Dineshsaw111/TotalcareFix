package com.totalcarefix.controllers;

import com.totalcarefix.dto.RequestStatus;
import com.totalcarefix.entities.Status;
import com.totalcarefix.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    StatusService statusService;
    @PostMapping("/add")
    public ResponseEntity<String> addStatus1(@RequestBody RequestStatus requestStatus){
        String message=statusService.addStatus(requestStatus);
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

    @GetMapping("/getAllStatus")
    public ResponseEntity<List<Status>> allStatus(){
        return new ResponseEntity<>(statusService.getAllStatus(),HttpStatus.OK);
    }

    @DeleteMapping("/deleteStatus/{id}")
    public ResponseEntity<String> deleteStatus(@PathVariable int id){
        boolean check=statusService.deleteStatus(id);
        if(check){
            return new ResponseEntity<>("one status deleted", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("no status exist", HttpStatus.OK);
        }
    }
}
