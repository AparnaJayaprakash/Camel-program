package com.example.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProcessorExample1Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProcessorExample1Application.class, args);
		
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new SpecificFileProcessingRoute());
		context.start();
		Thread.sleep(5000);
		context.stop();
	}

}
