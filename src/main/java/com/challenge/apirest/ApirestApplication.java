package com.challenge.apirest;

import com.challenge.apirest.model.ERoles;
import com.challenge.apirest.model.Role;
import com.challenge.apirest.repository.RoleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ApirestApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext =
				SpringApplication.run(ApirestApplication.class, args);

		RoleRepository roleRepository = configurableApplicationContext.getBean(RoleRepository.class);

		List<Role> roleList = new ArrayList<>();
		Role roleAdmin = new Role(ERoles.ROLE_ADMIN);
		Role roleUser = new Role(ERoles.ROLE_USER);
		roleList.add(roleAdmin);
		roleList.add(roleUser);
		roleRepository.saveAll(roleList);

	}

}
