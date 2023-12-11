package com.test.interview.service;

import com.test.interview.payload.SignupRequest;

public interface AuthService {
    String createUser(SignupRequest signupRequest);

    String generateToken(String username);

    void validateToken(String token);
}
