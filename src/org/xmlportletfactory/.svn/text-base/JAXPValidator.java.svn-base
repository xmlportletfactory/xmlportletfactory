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

package org.xmlportletfactory;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class JAXPValidator {

    public void validateSchema(String SchemaUrl, String XmlDocumentUrl) {
        try {
            System.setProperty("javax.xml.parsers.DocumentBuilderFactory",
                    "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(true);
            factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                    "http://www.w3.org/2001/XMLSchema");
            factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", SchemaUrl);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Validator handler = new Validator();
            builder.setErrorHandler(handler);
            builder.parse(XmlDocumentUrl);
            if (handler.validationError == true) {
                System.out.println("XML Document has Error:" + handler.validationError + " " + handler.saxParseException.getMessage());
            } else {
                System.out.println("XML Document is valid");
            }
        } catch (java.io.IOException ioe) {
            System.out.println("IOException " + ioe.getMessage());
        } catch (SAXException e) {
            System.out.println("SAXException" + e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException                    " + e.getMessage());
        }
    }

    private class Validator extends DefaultHandler {

        public boolean validationError = false;
        public SAXParseException saxParseException = null;

        public void error(SAXParseException exception) throws SAXException {
            validationError = true;
            saxParseException = exception;
        }

        public void fatalError(SAXParseException exception) throws SAXException {
            validationError = true;
            saxParseException = exception;
        }

        public void warning(SAXParseException exception) throws SAXException {
        }
    }
}
