package com.test.interview.controller;

import com.test.interview.payload.LoginRequest;
import com.test.interview.payload.SignupRequest;
import com.test.interview.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addUser(@RequestBody SignupRequest signupRequest) {
        return authService.createUser(signupRequest);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody LoginRequest loginRequest) {
         Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
         if (authentication.isAuthenticated()) {
             return authService.generateToken(loginRequest.getUsername());
         } else {
             throw new RuntimeException("invalid username or password!");
         }

    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "token is validated";
    }
}
