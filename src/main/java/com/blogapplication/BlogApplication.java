package com.blogapplication;

import com.blogapplication.controller.PostController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		System.out.println("I got a code");
		System.out.println("I will write code");
		SpringApplication.run(BlogApplication.class, args);
	}

}
