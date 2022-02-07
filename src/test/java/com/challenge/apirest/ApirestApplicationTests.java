package com.challenge.apirest;

import com.challenge.apirest.controller.AuthController;
import com.challenge.apirest.controller.CalculatorController;
import com.challenge.apirest.controller.HistoryController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApirestApplicationTests {

	@MockBean
	private AuthController authController;

	@MockBean
	private CalculatorController calculatorController;

	@MockBean
	private HistoryController historyController;


	@Test
	public void applicationContextLoaded() {
	}

	@Test
	void contextLoads() {
		assertThat(authController).isNotNull();
		assertThat(calculatorController).isNotNull();
		assertThat(historyController).isNotNull();

	}

	@Test
	public void applicationContextTest() {
		ApirestApplication.main(new String[] {});
	}

}
