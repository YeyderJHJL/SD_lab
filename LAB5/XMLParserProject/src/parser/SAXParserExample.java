package parser;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;

public class SAXParserExample extends DefaultHandler {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("src/data/Ejer01.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);

            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(xmlFile, new SAXParserExample());

            System.out.println("Documento XML válido.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        System.out.println("Inicio de elemento: " + qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        System.out.println("Fin de elemento: " + qName);
    }
}
