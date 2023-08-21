package com.example.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TypeConverterExampleApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TypeConverterExampleApplication.class, args);
		
		CamelContext context = new DefaultCamelContext();
		
		context.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				
				from("direct:start")
				.process(exchange ->{
					int intValue = exchange.getIn().getBody(Integer.class);
					String stringValue = "Converted value: " + intValue;
					exchange.getIn().setBody(stringValue);
				})
				.log("${body}")
				.to("mock:result");
				
			}
		});
		
		context.start();
		
		ProducerTemplate ptemp = context.createProducerTemplate();
		ptemp.sendBody("direct:start", 123456789);
		
		
		Thread.sleep(3000);
		
		context.stop();
	}

}
