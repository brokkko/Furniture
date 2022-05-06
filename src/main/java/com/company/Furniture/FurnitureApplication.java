package com.company.Furniture;

import com.company.Furniture.components.furniture.Chair;
import com.company.Furniture.config.StorageConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@SpringBootApplication
//@EnableConfigurationProperties(StorageConfig.class)
public class FurnitureApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurnitureApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(){
		return args -> {

//			List<Component> elements = new ArrayList<>();
//			Shaped chairShape = new RectangularShape(10, 5, 3);
//			elements.add(new Unit(chairShape, TypeColor.BLACK, TypeMaterial.WOODEN));
//			for(int i=0; i<2; i++){
//				elements.add(new Unit(chairShape, TypeColor.BLACK, TypeMaterial.WOODEN));
//			}
//			elements.add(new Unit(chairShape, TypeColor.BLACK, TypeMaterial.WOODEN));
//			Chair chair = Chair.builder().elements(elements).build();
//			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//			String json = ow.writeValueAsString(chair);
//			System.out.println(json);

//			File file = new File("src/main/java/com/company/Furniture/data/json.json");
//			InputStream inputStream = new FileInputStream(file);
//			//inputStream.close();
//
//			ObjectMapper objectMapper = new ObjectMapper();
//			Chair chair = objectMapper.readValue(inputStream, Chair.class);
//			System.out.println(chair.getElements());
		};
	}


}
