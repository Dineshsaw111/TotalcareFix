package com.totalcarefix;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
