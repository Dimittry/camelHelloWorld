package com.javacodegeeks.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

import java.io.FileInputStream;
import java.io.InputStream;

public class CamelXPathFilterExample {
	public static void main(String[] args) throws Exception {
		JndiContext jndiContext = new JndiContext();
		jndiContext.bind("orderBean", new OrderProcessor());		
		CamelContext camelContext = new DefaultCamelContext(jndiContext);
		try {
			camelContext.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					 from("direct:start")
	                    .choice()
	                        .when().xpath("//available = 'false'").to("bean:orderBean?method=processItem")
	                    .end()
	                    .to("stream:out");
				}
			});
			InputStream is = new FileInputStream("src/main/resources/orders.xml");
			camelContext.start();
			ProducerTemplate template = camelContext.createProducerTemplate();
			template.sendBody("direct:start", is);
//			template.sendBody("direct:start", "<order><product>books</product><available>false</available></order>");
		} finally {
			camelContext.stop();
		}
	}
}
