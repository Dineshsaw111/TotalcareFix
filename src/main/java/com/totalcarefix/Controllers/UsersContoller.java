package com.totalcarefix.Controllers;

import com.totalcarefix.DTO.NewRequestUser;
import com.totalcarefix.DTO.RequestStates;
import com.totalcarefix.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersContoller {

    @Autowired
    private UsersService usersService;
    @PostMapping("/addnewuser")
    public ResponseEntity<String> addStates1(@RequestBody NewRequestUser newRequestUser){
        String message=usersService.addUser(newRequestUser);
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
}
