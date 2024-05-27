package com.totalcarefix.services;


import com.totalcarefix.dto.requestRolesDTO;
import com.totalcarefix.entities.roles;
import com.totalcarefix.repos.rolesRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@SpringBootTest
public class rolesServiceTest {

    // unitesting of roleAdd
    @Autowired
    private rolesService roleservice;

    @MockBean
    private rolesRepo rolesrepo;

    @Test
    void addUnitTest1(){
        requestRolesDTO dto1=new requestRolesDTO("aaa");
        roles role= roles.builder()
                .name(dto1.getName())
                .build();
        when(rolesrepo.save(role)).thenReturn(role);
        assertTrue(roleservice.roleAdd(dto1));

    }

//    @Test
//    void addUnitTest_withNullObject(){
//        requestRolesDTO dto1=new requestRolesDTO(null);
//        roles role= roles.builder()
//                .name(dto1.getName())
//                .build();
//        when(rolesrepo.save(role)).thenReturn(role);
//        assertFalse(roleservice.roleAdd(dto1));
//
//    }




}
