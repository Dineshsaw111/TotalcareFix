package com.totalcarefix.services;

import com.totalcarefix.dto.requestRolesDTO;
import com.totalcarefix.entities.roles;
import com.totalcarefix.repos.rolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class rolesService {

    @Autowired
    rolesRepo rolesrepo;

    public boolean roleAdd(requestRolesDTO rolesdto){
        String name=rolesdto.getName();
        List<roles> list=new ArrayList<>();
        rolesrepo.findAll().forEach(list::add);
        for(roles data : list){
            if(data.getName().equals(name)){
                return false;
            }
        }
        boolean check=false;
        if(!rolesdto.getName().isEmpty()){
            roles role= roles.builder()
                    .name(rolesdto.getName())
                    .build();
            rolesrepo.save(role);
            return  true;
        }
        else{
            return check;
        }
    }

    public boolean roleDelete(int id){
        boolean check=false;
          check=rolesrepo.existsById(id);
          if(check){
              rolesrepo.deleteById(id);
              return check;
          }
          else{
              return false;
          }
    }
    public roles getRoleByID(int id){
        Optional<roles> obj= rolesrepo.findById(id);
        //rolesrepo.findById(id).get();
        return obj.orElseGet(() -> new roles(0, null));
    }
    public List<roles> getAllRoles(){
        List<roles> rolesList=new ArrayList<>();
         rolesrepo.findAll()
                 .forEach(rolesList ::add);
         return rolesList;
    }
    public boolean roleUpdated( requestRolesDTO roledto,int id){
        boolean check=false;
        check=rolesrepo.existsById(id);
        if (check){
            roles role=rolesrepo.findById(id).get();
            role.setName(roledto.getName());
            rolesrepo.save(role);
            return check;
        }
        else {
            return false;
        }
    }
}
