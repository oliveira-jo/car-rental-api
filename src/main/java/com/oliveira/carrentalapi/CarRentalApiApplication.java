package com.oliveira.carrentalapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Car Rental", version = "1", description = "Discover the latest offers for rent a car. Check out or offers now."))
public class CarRentalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApiApplication.class, args);
	}

}
