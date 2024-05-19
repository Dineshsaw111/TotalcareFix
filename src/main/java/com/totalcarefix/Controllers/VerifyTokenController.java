package com.totalcarefix.Controllers;

import com.totalcarefix.DTO.LoginDto;
import com.totalcarefix.Services.LoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class VerifyTokenController {

    @Autowired
    private LoginService loginService;
    private final JwtDecoder jwtDecoder;

    public VerifyTokenController(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @GetMapping("/verify")
    public ResponseEntity<Map<String, String>> verifyToken(@RequestHeader("Authorization") String token) {

        String jwtToken = token.substring(7);

        // Parse and verify the token
        Jwt decodedJwt = jwtDecoder.decode(jwtToken);

        // Extract the email from the token claims
        Map<String, Object> claims = decodedJwt.getClaims();
        String email = (String) claims.get("email");

        LoginDto loginDto= loginService.getToken(email);

                    // Create a response object containing the token
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("email",loginDto.getEmail());
            response.put("role",loginDto.getRole());
            return new ResponseEntity<>(response,HttpStatus.OK);

    }
}
