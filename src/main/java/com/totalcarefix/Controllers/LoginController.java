package com.totalcarefix.Controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    private static final String SECRET_KEY = "5R@hP2A+gQkzXK9vS4M*E7jWdGdF5aJd";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days in milliseconds

    @PostMapping("/auth")
    public Map<String, String> generateToken(@RequestBody Map<String, String> request) {
        // Extract email from the request body
        String email = request.get("email");

        // Calculate token expiration time
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        // Create a JWT token with the email as the subject
        String token = Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        // Create a response object containing the token
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return response;
    }
}
