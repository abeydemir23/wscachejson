package com.beydemir.assignment.wscachejson;

import com.beydemir.assignment.wscachejson.repository.Subscriber;
import com.beydemir.assignment.wscachejson.service.SubscriberService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class WscachejsonApplication {

	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(WscachejsonApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(SubscriberService subscriberService) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Subscriber>> typeReference = new TypeReference<List<Subscriber>>(){};
			String dataFile = environment.getProperty("data.file.location");
			File initialFile = new File(dataFile);
			InputStream targetStream = new FileInputStream(initialFile);
			try {
				List<Subscriber> subscribers = mapper.readValue(targetStream,typeReference);
				subscriberService.saveAll(subscribers);
				System.out.println("Subscribers Saved!");
			} catch (IOException e) {
				System.err.println("Unable to save users: " + e.getMessage());
			}
		};
	}

}
