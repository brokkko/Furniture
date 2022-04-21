package com.company.Furniture;

import com.company.Furniture.components.Furniture.Component;
import com.company.Furniture.components.types.TypeUnits;
import com.company.Furniture.entity.Entity;
import com.company.Furniture.repository.EntityRepository;
import com.company.Furniture.services.CalculateService;
import com.company.Furniture.services.CalculateServiceImpl;
import com.company.Furniture.services.OrderJSON;
import com.company.Furniture.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class FurnitureApplication {
	@Autowired
	private EntityRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(FurnitureApplication.class, args);
	}

	@Bean
	public CommandLineRunner CommandLineRunnerBean() {
		return (args) -> {
			System.out.println("START");
			repo.deleteAll();

			CalculateService calculator = new CalculateServiceImpl();
			OrderService orderService = new OrderJSON();

			Component chair = orderService.packingChairOrder("src/main/java/com/company/Furniture/data/ChairOrder.json");
			Entity order = new Entity("Chair", (int) calculator.getWeight(chair, TypeUnits.GRAMS));
			repo.save(order);

			Component table = orderService.packingTableOrder("src/main/java/com/company/Furniture/data/TableOrder.json");
			System.out.println("Вес стола: " + calculator.getWeight(table, TypeUnits.GRAMS) + " гр");
			order = new Entity("Table", (int) calculator.getWeight(table, TypeUnits.GRAMS));
			repo.save(order);
			System.out.println("DONE");
		};
	}

}
