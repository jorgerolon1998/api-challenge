package com.challenge.apirest.service;

import com.challenge.apirest.model.RequestHistory;
import com.challenge.apirest.repository.RequestHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RequestHistoryServiceImplTest {

    @MockBean
    private RequestHistoryRepository requestHistoryRepository;

    @Autowired
    private RequestHistoryService service;

    private Pageable pageable = PageRequest.of(0,5);

    private List<RequestHistory> requests = new ArrayList<>();

    @BeforeEach
    public void setUp(){

        RequestHistory requestHistory = RequestHistory.builder()
                .body("{test}")
                .method("GET")
                .date(new Date()).endpoint("/test")
                .build();

        requests.add(requestHistory);

        Page<RequestHistory> page = new PageImpl<RequestHistory>(requests);

        when(requestHistoryRepository.findAll(pageable)).thenReturn(page);
    }

    @Test
    public void shouldReturnRequestHistory() {
        assertThat(service.getRequestHistory(pageable).get()).isEqualTo(requests);
    }
}
