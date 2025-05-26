/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compra.productos;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author User
 */
@WebService(serviceName = "compraproductos")
public class compraproductos {


    /**
     * Web service operation
     */
    @WebMethod(operationName = "comprasProductos")
    public String comprasProductos(@WebParam(name = "CantidadPan") int CantidadPan, @WebParam(name = "CantidadQueso") int CantidadQueso, @WebParam(name = "CantidadPlatanos") int CantidadPlatanos, @WebParam(name = "CantidadNaranjas") int CantidadNaranjas) {
        //TODO write your implementation code here:
        String mensaje="";
        double total=0;
        if (CantidadPan<1|| CantidadQueso<1|| CantidadPlatanos<1|| CantidadNaranjas<1)
            mensaje+="Ingreso incorrecto de las cantidades de los productos";
        else
        {
            mensaje+="\n";
            total+=CantidadPan*0.50;
            mensaje+="Pan: "+CantidadPan+"Unidades --> SubTotal:"+ CantidadPan*0.50;
            mensaje+="\n";
            total+=CantidadQueso*2.50;
            mensaje+="Queso: "+CantidadQueso+"Unidades --> SubTotal:"+ CantidadQueso*2.50;
            mensaje+="\n";
            total+=CantidadPlatanos*0.40;
            mensaje+="Platanos: "+CantidadPlatanos+"Unidades --> SubTotal:"+ CantidadPlatanos*0.40;
            mensaje+="\n";
            total+=CantidadNaranjas*0.40;
            mensaje+="Naranjas: "+CantidadNaranjas+"Unidades --> SubTotal:"+ CantidadNaranjas*0.40;
        }
        return mensaje;
    }
}
