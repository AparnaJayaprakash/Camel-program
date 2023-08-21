package com.example.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileTransferApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(FileTransferApplication.class, args);
		
		CamelContext context = new DefaultCamelContext();
		
		context.addRoutes(new FileTrasferRoute());
		
		context.start();
		
		Thread.sleep(3000);
		
		context.stop();
	}

}
