package com.totalcarefix.Services;

import com.totalcarefix.DTO.rolesDTO;
import com.totalcarefix.Entities.roles;
import com.totalcarefix.Repos.rolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class rolesService {

    @Autowired
    rolesRepo rolesrepo;

    public boolean roleAdd(rolesDTO rolesdto){
        roles role= roles.builder()
                .name(rolesdto.getName())
                .build();
         rolesrepo.save(role);
         return  true;
    }

    public boolean roleDelete(){

    }
    public boolean getRoleByID(){

    }
    public boolean getAllRoles(){

    }
    public boolean roleUpdated(){

    }
}
