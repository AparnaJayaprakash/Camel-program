package com.example.camel;

import org.apache.camel.builder.RouteBuilder;

public class SpecificFileProcessingRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		from("file:C:/Users/jayap/OneDrive/Desktop/Input?fileName=File2.txt&noop=true")
		.setHeader("File2",constant("Header for file2"))
		.log("Original Header: ${header.File2}")
		.log("Original body: ${body}")
		.process(exchange -> {
			String body = exchange.getIn().getBody(String.class);
			String newBody = "Modified Line: Always and Forever " + body;
			
			exchange.getIn().setBody(newBody);
			exchange.getIn().setHeader("Modified_Header", "Header value for Modified_Header");
		})
		.log("Modified body: ${body}")
		.log("Custom Header: ${header.Modified_Header}")
		.to("file:C:/Users/jayap/OneDrive/Desktop/Output");
		
	}

}