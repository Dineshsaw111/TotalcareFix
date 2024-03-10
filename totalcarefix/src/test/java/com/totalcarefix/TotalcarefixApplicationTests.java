package com.totalcarefix;

import com.totalcarefix.DTO.requestRolesDTO;
import com.totalcarefix.Entities.roles;
import com.totalcarefix.Repos.TestH2Repo;
import com.totalcarefix.Repos.rolesRepo;
import com.totalcarefix.Services.rolesService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestAotProcessor;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TotalcarefixApplicationTests {


// Integration testing
//@LocalServerPort
//	private int port;
//
//	private  String baseeUrl="http://localhost";
//
//	private static RestTemplate restTemplate;
//
//	@Autowired
//	private TestH2Repo test2repo;
//
//	@BeforeAll
//	 static void init(){
//		restTemplate=new RestTemplate();
//	}
//	@BeforeEach
//	 void setup(){
//		baseeUrl=baseeUrl.concat(":").concat(port+"").concat("/roles");
//	}
//
//	@Test
//	void testAddRole(){
//		requestRolesDTO reqdto1=new requestRolesDTO("zzz");
//		ResponseEntity<String> responseEntity = restTemplate.postForEntity((baseeUrl.concat("/add")), reqdto1, String.class);
//		assertEquals("one role added",responseEntity.getBody());
//	}



//	@Test
//	void contextLoads() {
//	}

}
