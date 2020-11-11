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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>List File Page</title>
    </head>
    <body>
        <h1>List File Page</h1>
        <h3> Path has ${listFile.size()} </h3>
        <c:forEach var="file" items="${listFile}">
            <c:choose>
                <c:when test="${file.isFile() == true}">
                    <p> File Name : ${file.getName()}</p>
                     <form action="downloadServlet" method="POST" >
                                    
                                        <input type="hidden" value="${folderName}}" name="folderName">
                                        <input type="hidden" value="${file.getName()}" name="pathFile">
                                        <input type="text" placeholder="Where to save" name="folderSave">
                                        <button type="submit" class="btn btn-primary">Download</button>
                    </form>
      
<!--                    <p> <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong"> Download File</button></p>-->
                </c:when>
            </c:choose>    
            <c:choose>
                <c:when test="${file.isDirectory() == true}">
                    <p> Folder Name : ${file.getName()}</p>
                    <p> <a href="listFolderServlet?fileName=${file.getName()}"> See Folder</a></p>
                </c:when>
            </c:choose>    
                   
        </c:forEach>
<!--                      Modal 
                    <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    Dowload File
                                </div>
                                <form action="downloadServlet" method="POST" >
                                    <div class="modal-body">
                                        <input type="text" placeholder="Where to save" name="pathSave">
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-primary">Download</button>
                                        <button type="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>-->

       
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
