package com.challenge.apirest.service;

import com.challenge.apirest.model.RequestHistory;
import com.challenge.apirest.repository.RequestHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RequestHistoryServiceImpl implements RequestHistoryService {

    @Autowired
    RequestHistoryRepository requestHistoryRepository;

    @Override
    public Page<RequestHistory> getRequestHistory(Pageable pageable) {
        return requestHistoryRepository.findAll(pageable);
    }

    @Override
    public void saveRequest(String endpoint, String method, String body) {
        RequestHistory requestHistory = new RequestHistory(endpoint, method, body);
        requestHistoryRepository.save(requestHistory);
    }
}
