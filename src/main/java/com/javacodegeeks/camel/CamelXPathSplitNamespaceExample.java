package com.javacodegeeks.camel;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelXPathSplitNamespaceExample {
	public static void main(String[] args) throws Exception {	
		CamelContext camelContext = new DefaultCamelContext();
		try {
			camelContext.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					from("direct:start")
					.split(xpath("//n:order[@product='electronics']/n:items/n:item/text()")
							.namespace("n", "http://com.javacodegeeks.data/schema/orders"))
					.to("stream:out")
					.end();
				}
			});
			InputStream is = new FileInputStream("src/main/resources/ordersWithNamespace.xml");	        
			camelContext.start();
			ProducerTemplate template = camelContext.createProducerTemplate();
			template.sendBody("direct:start", is);
		} finally {
			camelContext.stop();
		}
	}
}
