package com.challenge.apirest.service;

import com.challenge.apirest.model.RequestHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RequestHistoryService {
    Page<RequestHistory> getRequestHistory(Pageable pageable);

    void saveRequest(String endpoint, String method, String body);
}
