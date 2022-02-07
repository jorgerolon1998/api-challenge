package com.challenge.apirest.service;

import com.challenge.apirest.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.MessageFormat;

@Service
@Transactional
public class CalculatorServiceImpl implements CalculatorService {

    @Autowired
    RequestHistoryService requestHistoryService;

    @Override
    public ResponseEntity<?> sum(long value1, long value2, HttpServletRequest request) {

        long result = value1 + value2;

        requestHistoryService.saveRequest(request.getServletPath(), request.getMethod(), null);

        return ResponseEntity.ok(new MessageResponse(MessageFormat.format("The result of the operation is: {0}.", result)));
    }
}
