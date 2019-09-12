package com.fcv.expressCourier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ExpressCourierApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpressCourierApplication.class, args);
	}

}
