package com.javacodegeeks.camel;

import org.apache.camel.language.XPath;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public class OrderProcessor {
	public String process(String text) throws Exception {
		String nodeName = "";
//		System.out.println(text);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document dom = db.parse( new InputSource( new StringReader(text)));
		Element docEle = dom.getDocumentElement();
		NodeList nl = docEle.getChildNodes();
		if (nl != null) {
			int length = nl.getLength();
			for (int i = 0; i < length; i++) {
				if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element el = (Element) nl.item(i);
					System.out.println(el);
					nodeName = el.getNodeName();
					if (el.getNodeName().contains("order")) {
//						System.out.println(el.getNodeName());
//						System.out.println(el.getElementsByTagName("item").item(0).getTextContent());
//                        String name = el.getElementsByTagName("name").item(0).getTextContent();
//                        String phone = el.getElementsByTagName("phone").item(0).getTextContent();
//                        String email = el.getElementsByTagName("email").item(0).getTextContent();
//                        String area = el.getElementsByTagName("area").item(0).getTextContent();
//                        String city = el.getElementsByTagName("city").item(0).getTextContent();
					}
				}
			}
		}
		return nodeName;
	}
	
//	public boolean processItem(@XPath("//order[@product='electronics']/items/item/text()") String item) {
	public boolean processItem(@XPath("/orders/*[start-with(name(), 'Q')]") String item) {
		System.out.println("here 2");
//		boolean condition = item != null && "Laptop".equals(item);
//		System.out.println("condition "  + condition);
		System.out.println(item);
//		if (condition) {
//			System.out.println("Processing item " + item);
//		}
		return true;
	}
}
