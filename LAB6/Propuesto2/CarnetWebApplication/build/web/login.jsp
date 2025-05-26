<%-- 
    Document   : login
    Created on : 26/05/2025, 02:46:17 PM
    Author     : User
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Login</title></head>
<body>
    <h2>Inicio de Sesión</h2>
    <form action="validar.jsp" method="post">
        Usuario: <input type="text" name="usuario" required/><br/>
        Contraseña: <input type="password" name="clave" required/><br/>
        <input type="submit" value="Ingresar"/>
    </form>
</body>
</html>
