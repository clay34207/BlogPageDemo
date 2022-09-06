package com.example.demo;

import com.example.demo.DAO.BlogDAO;
import com.opencsv.CSVReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@RestController
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		//readAllLines();
		SpringApplication.run(DemoApplication.class, args);
	}
	public static List<String[]> readAllLines() throws Exception {
		try (Reader reader = Files.newBufferedReader(Path.of("/Users/claymallory/Desktop/demo/src/main/java/com/example/demo/blogs.csv"))) {
			try (CSVReader csvReader = new CSVReader(reader)) {
				List<String[]> arr = csvReader.readAll();
				//System.out.println(arr.get(1)[0]);
				return csvReader.readAll();
			}
		}
	}
	@Bean
	public CorsFilter corsFilter() { //Need this to prevent unknown http error when trying to call the Wexner API
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "https://wexnermedical.osu.edu/blog/topics?blogId=F88D7CA3-2E58-4A80-AEFE-A0755FCD491D"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("*", "Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
