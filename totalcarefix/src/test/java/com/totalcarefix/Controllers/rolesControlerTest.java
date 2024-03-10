package com.totalcarefix.Controllers;

import com.totalcarefix.DTO.requestRolesDTO;
import com.totalcarefix.Repos.TestH2Repo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class rolesControlerTest {
    // Integration testing of "/add"
    @LocalServerPort
    private int port;

    private  String baseeUrl="http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private TestH2Repo test2repo;

    @BeforeAll
    static void init(){
        restTemplate=new RestTemplate();
    }
    @BeforeEach
    void setup(){
        baseeUrl=baseeUrl.concat(":").concat(port+"").concat("/roles");
    }

    @Test
    void testAddRole(){
        requestRolesDTO reqdto1=new requestRolesDTO("zzz");
        ResponseEntity<String> responseEntity = restTemplate.postForEntity((baseeUrl.concat("/add")), reqdto1, String.class);
        assertEquals("one role added",responseEntity.getBody());
    }
}
