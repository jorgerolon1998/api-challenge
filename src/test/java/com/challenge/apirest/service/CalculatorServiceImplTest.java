package com.challenge.apirest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class CalculatorServiceImplTest {

    @Autowired
    private CalculatorService service;

    private MockHttpServletRequest request = new MockHttpServletRequest();

    @BeforeEach
    public void setUp(){
        request.setMethod("GET");
        request.setServletPath("/test");
    }

    @Test
    public void shouldReturnSumResult() {
        assertThat(service.sum(1L,2l,request).equals(3l));
    }


}
