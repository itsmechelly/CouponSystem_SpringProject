package com.couponsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//TODO -> @EnableScheduling
public class CouponSystemSpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponSystemSpringProjectApplication.class, args);
		System.out.println("The git version up to here is with Session Context technique. from this point, I will change all to JWT technique");
	}

}
