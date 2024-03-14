package com.totalcarefix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TotalcarefixApplication {

	public static void main(String[] args) {
		SpringApplication.run(TotalcarefixApplication.class, args);
		System.out.println("Server is running on port number 8080");
	}

}
