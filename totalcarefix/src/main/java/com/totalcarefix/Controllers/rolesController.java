package com.totalcarefix.Controllers;

import com.totalcarefix.DTO.rolesDTO;
import com.totalcarefix.Services.rolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("roles")
public class rolesController {

    @Autowired
    rolesService roleservice;

    @PostMapping("/add")
    public ResponseEntity<String> addRole(@RequestBody rolesDTO rolesdto){
        boolean check=roleservice.roleAdd(rolesdto);
        if(check){
            return new ResponseEntity<>("one role added", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("role already there", HttpStatus.MULTI_STATUS);
        }
    }
@DeleteMapping("/deleteRole/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable int id){
    boolean check=roleservice.
    if(check){
        return new ResponseEntity<>("one role added", HttpStatus.CREATED);
    }
    else{
        return new ResponseEntity<>("role already there", HttpStatus.MULTI_STATUS);
    }
    }

    public String allRoles(){

    }
    public String findRoleByID(){

    }
    public String updateRoleByID(){

    }
}
