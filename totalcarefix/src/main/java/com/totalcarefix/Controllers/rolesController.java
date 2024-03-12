package com.totalcarefix.Controllers;

import com.totalcarefix.DTO.requestRolesDTO;
import com.totalcarefix.Entities.roles;
import com.totalcarefix.Services.rolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class rolesController {

    @Autowired
    rolesService roleservice;
    @PostMapping("/add")
    public ResponseEntity<String> addRole(@RequestBody requestRolesDTO rolesdto){
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
    boolean check=roleservice.roleDelete(id);
    if(check){
        return new ResponseEntity<>("one role added", HttpStatus.CREATED);
    }
    else{
        return new ResponseEntity<>("role already there", HttpStatus.MULTI_STATUS);
    }
    }
  @GetMapping("/getAllRoles")
    public ResponseEntity<List<roles>> allRoles(){
          return new ResponseEntity<>(roleservice.getAllRoles(),HttpStatus.OK);
    }
    @GetMapping("/getRoleByID/{id}")
    public ResponseEntity<roles> findRoleByID(@PathVariable int id){
       return new ResponseEntity<>(roleservice.getRoleByID(id),HttpStatus.OK);
    }
    @PutMapping("/updateRole/{id}")
    public ResponseEntity<String> updateRoleByID( @RequestBody requestRolesDTO rolesdto ,@PathVariable int id ){
      boolean check;
      check= roleservice.roleUpdated(rolesdto,id);
      if(check){
          return new ResponseEntity<>("updated", HttpStatus.OK);
      }
      else{
          return new ResponseEntity<>("not found", HttpStatus.OK);
      }
    }
}