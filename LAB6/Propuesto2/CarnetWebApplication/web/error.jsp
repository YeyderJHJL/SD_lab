<%-- 
    Document   : error
    Created on : 26/05/2025, 02:47:49 PM
    Author     : User
--%>

<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Error Detectado</title></head>
<body>
    <h2>Se ha producido un error:</h2>
    <p><strong>Tipo:</strong> <%= exception.getClass().getName() %></p>
    <p><strong>Mensaje:</strong> <%= exception.getMessage() %></p>
</body>
</html>