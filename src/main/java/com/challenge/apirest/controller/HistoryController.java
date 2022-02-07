package com.challenge.apirest.controller;


import com.challenge.apirest.model.RequestHistory;
import com.challenge.apirest.service.RequestHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    RequestHistoryService requestHistoryService;

    @GetMapping("/request")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getRequestHistory(Pageable pageable) {
        Page<RequestHistory> requests = requestHistoryService.getRequestHistory(pageable);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> paging = new HashMap<>();
        paging.put("page", requests.getNumber());
        paging.put("total", requests.getTotalElements());
        paging.put("size", requests.getTotalPages());
        response.put("paging", paging);
        response.put("items", requests.getContent());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
