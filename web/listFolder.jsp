<%-- 
    Document   : listFile
    Created on : Nov 9, 2020, 9:41:26 AM
    Author     : admin123
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>List File Page</title>
    </head>
    <body>
        <h1>List File Page</h1>
        <c:set var="preFolderName" value="${folderName}"/>
        <c:set var="preFolder" value="${fn:substringBefore(preFolderName,'/')}" />
        <p>Previous Folder <a href="listFolderServlet?folderName=${preFolder}">Previous</a></p>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th style="width: 5%"scope="col">Icon</th>
                    <th scope="col">File Name</th>
                    <th scope="col">Size</th>
                    <th scope="col">Date</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="file" items="${listFile}">
                    <c:choose>
                        <c:when test="${file.isFile() == true}">
                            <tr>
                                <td><i class="material-icons">cloud_queue</i></td>
                                <td>${file.getName()}</td>
                                <td>${file.getSize()}</td>
                                <td></td>
                                <td>   <button type="button"style="font-size:24px" data-toggle="modal" data-target="#${file.getName()}">Download File </button></td>
                            </tr>
                        </c:when>
                        <c:when test="${file.isDirectory() == true}">
                            <tr>
                                <td><i class="fa fa-folder"></i></td>
                                <td>${file.getName()}</td>
                                <td></td>
                                <td></td>
                                <c:set var="pathFolder" value="${folderName}/${file.getName()}"/>
                                <td><a href="listFolderServlet?folderName=${pathFolder}"><button>Open File </button></a></td>
                            </tr>
                        </c:when>
                    </c:choose>    
                </c:forEach>

            </tbody>
        </table>

         <c:forEach var="file" items="${listFile}">   
             <c:choose>
                 <c:when test="${file.isFile() == true}">
                       <div class="modal fade" id="${file.getName()}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            Download <b> ${file.getName()} <b>
                                        </div>
                                        <form action="downloadServlet" method="POST" >
                                            <div class="modal-body">
                                                <input type="hidden" name="folderName" value="${folderName}">
                                                <input type="hidden" name="fileName" value="${file.getName()}">
                                                <input type="text" placeholder="Where to save ?" name="folderSave">
                                            </div>
                                            <div class="modal-footer">
                                                <button type="submit" class="btn btn-primary">Download</button>
                                                <button type="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                            </div>
                                        </form>
        
                                    </div>
                                </div>
                       </div>
                 </c:when>
             </c:choose>
           

        </c:forEach>
        <h1 style="display:none" id="messagesError">${message}</h1>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script type="text/javascript">
          window.onload = function(){ 
               var avai = document.getElementById("messagesError").textContent;
               var check1 = avai.localeCompare("Download Sucessfully");
             
               if (check1==0 ) alert(avai); 
                    //alert("${message}");
           }
    </script>
</html>
