<%-- 
    Document   : listFile
    Created on : Nov 9, 2020, 9:41:26 AM
    Author     : admin123
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List File Page</title>
    </head>
    <body>
        <h1>List File Page</h1>
        <h3> Path has ${listFile.size()} </h3>
        <c:forEach var="file" items="${listFile}">
            <p> ${file.getName()}</p>
        </c:forEach>
        
    </body>
</html>
