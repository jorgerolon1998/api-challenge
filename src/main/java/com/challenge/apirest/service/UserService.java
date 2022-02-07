package com.challenge.apirest.service;

import com.challenge.apirest.request.LoginRequest;
import com.challenge.apirest.request.SignupRequest;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    ResponseEntity<?> registerUser(SignupRequest signUpRequest, HttpServletRequest request);

    ResponseEntity<?> loginUser(LoginRequest loginRequest, HttpServletRequest request);

    ResponseEntity<?> logoutUser(HttpServletRequest request);

}
