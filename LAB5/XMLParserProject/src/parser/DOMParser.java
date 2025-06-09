package parser;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class DOMParser {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("src/data/Ejer01.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(false);

            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new org.xml.sax.helpers.DefaultHandler());
            Document doc = builder.parse(xmlFile);

            System.out.println("Documento XML válido.");
            System.out.println("Elemento raíz: " + doc.getDocumentElement().getNodeName());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}