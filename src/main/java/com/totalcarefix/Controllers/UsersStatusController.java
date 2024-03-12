package com.totalcarefix.Controllers;

import com.totalcarefix.DTO.RequestUsersStatus;
import com.totalcarefix.DTO.requestRolesDTO;
import com.totalcarefix.Entities.UsersStatus;
import com.totalcarefix.Entities.roles;
import com.totalcarefix.Services.UsersStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/UserStatus")
public class UsersStatusController {

    @Autowired
    UsersStatusService usersStatusService;
    @PostMapping("/add")
    public ResponseEntity<String> addRole(@RequestBody RequestUsersStatus requestUsersStatus){
        String message=usersStatusService.addUsersStatus(requestUsersStatus);
        if(message.equals("null object")){
            return new ResponseEntity<>("null object can not be added", HttpStatus.OK);
        }
        else if(message.equals("Already exist")){
            return new ResponseEntity<>("role already there", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("added", HttpStatus.CREATED);
        }
    }

    @GetMapping("/getAllUserStatus")
    public ResponseEntity<List<UsersStatus>> allRoles(){
        return new ResponseEntity<>(usersStatusService.getAllUsersStatus(),HttpStatus.OK);
    }
    @GetMapping("/getUserStatusByID/{id}")
    public ResponseEntity<UsersStatus> findRoleByID(@PathVariable int id){
        return new ResponseEntity<>(usersStatusService.getByIDUsersStatus(id),HttpStatus.OK);
    }
}
