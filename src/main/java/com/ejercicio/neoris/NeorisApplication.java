package com.ejercicio.neoris;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "com.ejercicio.neoris")
public class NeorisApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeorisApplication.class, args);
	}

}
