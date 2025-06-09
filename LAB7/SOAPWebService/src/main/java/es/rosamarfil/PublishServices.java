/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.rosamarfil;

import javax.xml.ws.Endpoint;
import es.rosamarfil.soap.SOAPImpl;

public class PublishServices {

    public static void main(String[] args) {
        /*
         * Se publican los servicios a través de un servidor virtual.
         * El puerto puede ser cualquiera.
         * Una vez ejecutada la aplicación, se publica el contrato WSDL
         */
        Endpoint.publish("http://localhost:1516/WS/Users", new SOAPImpl());
        System.out.println("Servicio SOAP publicado en: http://localhost:1516/WS/Users");
        System.out.println("WSDL disponible en: http://localhost:1516/WS/Users?wsdl");
        System.out.println("Presiona Enter para detener el servicio...");

        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
