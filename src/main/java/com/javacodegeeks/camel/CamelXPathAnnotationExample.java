package com.javacodegeeks.camel;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelXPathAnnotationExample {
	public static void main(String[] args) throws Exception {	
		CamelContext camelContext = new DefaultCamelContext();
		try {
			camelContext.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
//					from("direct:start")
					from("file:src/main/resources/data?noop=true")
//							.split().tokenizeXML("order1")
//		            .filter().method(new OrderProcessor(), "processItem")
						.bean(OrderProcessor.class, "process")
		                .to("stream:out");
				}
			});

//			InputStream is = new FileInputStream("src/main/resources/orders.xml");
			camelContext.start();
			Thread.sleep(10000);
//			Thread.currentThread().join();
//			ProducerTemplate template = camelContext.createProducerTemplate();
//			template.sendBody("direct:start", is);


//			camelContext.start();
//			ProducerTemplate template = camelContext.createProducerTemplate();
//			//...//order[@product='electronics']/items/item/text()
//			String ordersXml = "<orders><order product=\'electronics\'><items><item>Laptop</item><item>Mobile</item></items></order></orders>";
//			template.sendBody("direct:start", ordersXml);
//
//			ordersXml = "<orders><order product=\'books\'><items><item>Design Patterns</item><item>XML</item></items></order></orders>";
//			template.sendBody("direct:start", ordersXml);
		} finally {
			camelContext.stop();
		}
	}
}
