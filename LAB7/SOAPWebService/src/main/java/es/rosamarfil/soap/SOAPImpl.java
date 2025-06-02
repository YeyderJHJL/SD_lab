/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.rosamarfil.soap;

import java.util.List;
import javax.jws.WebService;
import es.rosamarfil.model.User;

@WebService(endpointInterface = "es.rosamarfil.soap.SOAPI")
public class SOAPImpl implements SOAPI {

    @Override
    public List<User> getUsers() {
        return User.getUsers();
    }

    @Override
    public void addUser(User user) {
        User.getUsers().add(user);
    }
}
