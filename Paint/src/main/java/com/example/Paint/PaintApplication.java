package com.example.Paint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class PaintApplication {

	public static void main(String[] args) {
		var ctx = new SpringApplicationBuilder(PaintApplication.class)
			.headless(false).run(args);
	}

}
