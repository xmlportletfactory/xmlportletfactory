/**
 *     Copyright (C) 2009-2011  Jack A. Rider All rights reserved.
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 */
package org.xmlportletfactory.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Jack A. Rider
 */
public class XMLUtils {
    public static void validateAgainstSchema(String XMLDocPath ,String XSDDocPath) throws SAXException, IOException {
    try {
            // define the type of schema - we use W3C:
            String schemaLang = "http://www.w3.org/2001/XMLSchema";

            // get validation driver:
            SchemaFactory factory = SchemaFactory.newInstance(schemaLang);

            // create schema by reading it from an XSD file:
            Schema schema = factory.newSchema(new StreamSource(new File(XSDDocPath)));
            Validator validator = schema.newValidator();

            // at last perform validation:
            validator.validate(new StreamSource(new File(XMLDocPath)));

        } catch (IOException ex) {
            Logger.getLogger(XMLUtils.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException(ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLUtils.class.getName()).log(Level.SEVERE, null, ex);
            throw new SAXException(ex);
        }
    }

 public static Document parseXmlFile(String filename, boolean validating) {
	try {
	    // Create a builder factory
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    factory.setValidating(validating);
	
	    // Create the builder and parse the file
	    Document doc = factory.newDocumentBuilder().parse(new File(filename));
	    return doc;
	} catch (ParserConfigurationException e) {
	    System.out.println("-----------------------------------------------");
	    System.out.println("Error ParserConfiguration");
	    System.out.println("-----------------------------------------------");
	} catch (SAXException e) {
	    System.out.println("-----------------------------------------------");
	    System.out.println("Error XML input is not valid");
	    System.out.println("-----------------------------------------------");
	} catch (IOException e) {
	    System.out.println("-----------------------------------------------");
	    System.out.println("Error IO exception");
	    System.out.println("-----------------------------------------------");
	}
	return null;
  }
 

 public static String toUnicode(String input) {     
	 StringBuffer ret = new StringBuffer();
	 if(input!=null){
	     for (int i = 0; i < input.length(); i++) {
	         char ch = input.charAt(i);
	         if (!Character.isWhitespace(ch) && ch < 0x20 || ch > 0x7e) {
	             ret.append("\\u");
	             // requires 1.5 VM
	             // ret.append(String.format("%1$04x", new Object[] { Integer.valueOf(ch) }));
	             ret.append(leading4Zeros(Integer.toHexString(ch)));
	         } else {
	             ret.append(ch);
	         }
	     }
	 }
     return ret.toString();
 }
 
 /**
  * @param hexString max 4 characters length
  * @return same string with leading zeros
  */
 public static char[] leading4Zeros(String hexString) {
     char[] chars = "0000".toCharArray();
     int length = hexString.length();
     hexString.getChars(0, length, chars, 4 - length);
     return chars;
 }
 
 public static Node findFirstElementChild(Node parentNode) {
	 	
	 	Node foundNode = null;
	 
	 	NodeList childNodes = parentNode.getChildNodes();
		
		for (int i = 0; i < childNodes.getLength() && foundNode == null; i++) {
			Node item = childNodes.item(i);
			if(item.getNodeType() == Node.ELEMENT_NODE) {
				foundNode = item;
			}
		}
		
		return foundNode;
 }
 
 public static Node findFirstElementChildWithName(Node parentNode, String childName) {
	 	
	 	Node foundNode = null;
	 
	 	NodeList childNodes = parentNode.getChildNodes();
		
		for (int i = 0; i < childNodes.getLength() && foundNode == null; i++) {
			Node item = childNodes.item(i);
			if(childName.equals(item.getLocalName())) {
				foundNode = item;
			}
		}
		
		return foundNode;
 }
 

 
 
 
 	

}
