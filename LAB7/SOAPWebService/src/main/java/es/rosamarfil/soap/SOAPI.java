/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.rosamarfil.soap;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import es.rosamarfil.model.User;

@WebService
public interface SOAPI {
    
    @WebMethod
    public List<User> getUsers();
    
    @WebMethod
    public void addUser(User user);
}