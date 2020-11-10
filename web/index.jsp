<%-- 
    Document   : index
    Created on : Nov 9, 2020, 8:06:56 AM
    Author     : admin123
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Connected To Server </h1>
       
        <form action="listFileServlet" method="POST">
            <input type="text" name="term" required="required">
            <button type="submit"> List File</button
        </form>
    </body>
</html>
