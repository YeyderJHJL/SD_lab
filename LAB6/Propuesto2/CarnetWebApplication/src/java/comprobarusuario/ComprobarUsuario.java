/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comprobarusuario;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author User
 */
@WebService(serviceName = "ComprobarUsuario")
public class ComprobarUsuario {

    @WebMethod (operationName="Comprobar")
    public boolean Comprobar(@WebParam(name="usuario") String user1, @WebParam(name="contrasenia") String contra){
        try{
            if("usuario".equals(user1) && "contrasenia".equals(contra)){
                return true;
            }
            else if("usuario".equals(user1) && !"contrasenia".equals(contra)){
                return false;
            }else if(!"usuario".equals(user1) && !"contrasenia".equals(contra))
                return false;
        }catch(Exception e){
            return false; 
        }
        return false;
    }
}
