package com.blogapplication;

import com.blogapplication.controller.PostController;
import com.blogapplication.entity.Role;
import com.blogapplication.repository.RoleRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class BlogApplication {
	@Autowired
	RoleRepository role;

	public static void main(String[] args) {

		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	public void run(String... args)throws Exception
	{
		Role adminRole=new Role();
		adminRole.setName("ROLE_ADMIN");
		role.save(adminRole);

		Role userRole=new Role();
		adminRole.setName("ROLE_USER");
		role.save(userRole);
	}
}
