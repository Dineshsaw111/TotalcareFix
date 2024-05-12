package com.totalcarefix.Controllers;

import com.totalcarefix.DTO.LoginDto;
import com.totalcarefix.Entities.Users;
import com.totalcarefix.Repos.UsersRepo;
import com.totalcarefix.Services.LoginService;
import com.totalcarefix.Services.UsersService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    private static final String SECRET_KEY = "5R@hP2A+gQkzXK9vS4M*E7jWdGdF5aJd";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days in milliseconds

    @PostMapping("/auth")
    public Map<String, String> generateToken(@RequestBody Map<String, String> request) {


        String email = request.get("email");
       LoginDto loginDto= loginService.getToken(email);


        // Calculate token expiration time
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        // Create a JWT token
        String token = Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        // Create a response object containing the token
        Map<String, String> response = new HashMap<>();
         response.put("token", token);
         response.put("email",loginDto.getEmail());
         response.put("role",loginDto.getRole());


        return response;
    }
}
