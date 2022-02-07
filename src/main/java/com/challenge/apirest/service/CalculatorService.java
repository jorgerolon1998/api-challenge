package com.challenge.apirest.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface CalculatorService {

    ResponseEntity<?> sum(long value1 , long value2, HttpServletRequest request);
}
