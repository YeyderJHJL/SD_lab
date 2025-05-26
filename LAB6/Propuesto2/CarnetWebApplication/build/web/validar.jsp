<%-- 
    Document   : validar
    Created on : 26/05/2025, 02:46:46 PM
    Author     : User
--%>

<%@ page errorPage="error.jsp" contentType="text/html;charset=UTF-8" language="java" %>
<%
    String usuarioCorrecto = "admin";
    String claveCorrecta = "1234";

    String usuario = request.getParameter("usuario");
    String clave = request.getParameter("clave");

    if (!usuarioCorrecto.equals(usuario) || !claveCorrecta.equals(clave)) {
        throw new Exception("Credenciales inválidas");
    }

    out.println("<h1>Bienvenido, " + usuario + "!</h1>");
%>
