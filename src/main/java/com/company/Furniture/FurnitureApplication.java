package com.company.Furniture;

import com.company.Furniture.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FurnitureApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurnitureApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(DataLoaderServiceImpl service){
		return args -> {

			ControllerService controllerService = new ControllerServiceImpl(service);
			List<String> filesList = List.of(
					"src/main/java/com/company/Furniture/data/ChairOrder.json",
					"src/main/java/com/company/Furniture/data/TableOrder.json"
			);

			controllerService.saveOrder(filesList);

		};
	}
}
