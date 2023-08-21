package com.jmsexample;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class JmsActivemq {

	public static void main(String[] args) throws Exception {
		
		CamelContext context = new DefaultCamelContext();
		
		ConnectionFactory cfactory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(cfactory));
		context.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				
				from("direct:start")
				.to("jms:queue:myQueue");
				
				from("jms:queue:myQueue")
				.log("Received message from JMS: ${body}");
				
				
				
			}
		});
		
		context.start();
		
		context.createProducerTemplate().sendBody("direct:start", "Hello! ActiveMQ :)");
		Thread.sleep(5000);
		context.stop();

	}

}
