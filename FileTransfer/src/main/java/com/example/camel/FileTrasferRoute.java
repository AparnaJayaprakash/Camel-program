package com.example.camel;

import org.apache.camel.builder.RouteBuilder;

public class FileTrasferRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		from("file:C:/Users/jayap/OneDrive/Desktop/Input?noop=true")
		.log(">>>>>> Header: ${header.File1}")
		.log(">>>>>> Body: ${body}")
		.to("file:C:/Users/jayap/OneDrive/Desktop/Output");
		
	}

}