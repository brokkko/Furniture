package com.company.Furniture;

import com.company.Furniture.components.Furniture.Component;
import com.company.Furniture.components.types.TypeUnits;
import com.company.Furniture.domain.FurnitureEntity;
import com.company.Furniture.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FurnitureApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurnitureApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(DataLoaderService service){
		return args -> {
			System.out.println("START");
			service.deleteAll();

			CalculateService calculator = new CalculateServiceImpl();
			DataParserService dataParserService = new DataParserJSON();

			Component chair = dataParserService.unpackingChairOrder("src/main/java/com/company/Furniture/data/ChairOrder.json");
			Component table = dataParserService.unpackingTableOrder("src/main/java/com/company/Furniture/data/TableOrder.json");

			List<FurnitureEntity> furnitureEntityList = List.of(
					new FurnitureEntity("Chair", (int) calculator.getWeight(chair, TypeUnits.GRAMS)),
					new FurnitureEntity("Table", (int) calculator.getWeight(table, TypeUnits.GRAMS))
					);
			service.save(furnitureEntityList);

			System.out.println("DONE");
		};
	}
}
