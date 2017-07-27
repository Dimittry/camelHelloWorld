package com.javacodegeeks.camel;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelXPathSplitNamespaceUsingSpring {
	public static final void main(String[] args) throws Exception {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"xpathSplitWithNsApplicationContext.xml");
		CamelContext camelContext = SpringCamelContext.springCamelContext(
				appContext, false);
		InputStream is = new FileInputStream("src/main/resources/ordersWithNamespace.xml");	        
		camelContext.start();
		ProducerTemplate template = camelContext.createProducerTemplate();
		template.sendBody("direct:start", is);
	}
}
