<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>FTP Server</title>
    <!-- Favicon-->
        <link rel="icon" href="/resources/images/favicon.ico" type="image/x-icon">

        <!-- Google Fonts -->
        <link href="<c:url value="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" />"rel="stylesheet" type="text/css" >
        <link href="<c:url value="https://fonts.googleapis.com/icon?family=Material+Icons"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">

        <!-- Bootstrap Core Css -->
        <link href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"/>" rel="stylesheet" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>" rel="stylesheet">

        <!-- Waves Effect Css -->
        <link href="<c:url value="/resources/plugins/node-waves/waves.css" />" rel="stylesheet" />

        <!-- Animation Css -->
        <link href="<c:url value="/resources/plugins/animate-css/animate.css" />"rel="stylesheet" />

        <!-- Custom Css -->
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

        <!-- AdminBSB Themes. You can choose a theme from css/themes instead of get all themes -->
        <link href="<c:url value="/resources/css/themes/theme-green.css" />" rel="stylesheet" />

        <link href="<c:url value="/resources/css/custom.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/paymentDetail.css" />" rel="stylesheet">  
</head>

<body class="theme-green">
    <!-- Page Loader -->
    <div class="page-loader-wrapper">
        <div class="loader">
            <div class="preloader">
                <div class="spinner-layer pl-green">
                    <div class="circle-clipper left">
                        <div class="circle"></div>
                    </div>
                    <div class="circle-clipper right">
                        <div class="circle"></div>
                    </div>
                </div>
            </div>
            <p>Please wait...</p>
        </div>
    </div>
    <!-- #END# Page Loader -->
    <!-- Overlay For Sidebars -->
    <div class="overlay"></div>
    <!-- #END# Overlay For Sidebars -->
    <!-- Search Bar -->
    <div class="search-bar">
        <div class="search-icon">
            <i class="material-icons">search</i>
        </div>
        <input type="text" placeholder="START TYPING...">
        <div class="close-search">
            <i class="material-icons">close</i>
        </div>
    </div>
    <!-- #END# Search Bar -->
    <!-- Top Bar -->
    <nav class="navbar">
        <div class="container-fluid">
            <div class="navbar-header">

            </div>
            <div class="collapse navbar-collapse" id="navbar-collapse">
                
            </div>
        </div>
    </nav>
<!--     #Top Bar -->
    <section>
<!--         Left Sidebar -->
        <aside id="leftsidebar" class="sidebar">
            <div class="menu">
            <ul class="list">
                   
                    <li>
                        <a href="listFileServlet">
                            <span >Server Reply</span>
                        </a>
                    </li>
                    <li>
                     <%
                            ArrayList<String> replyServer =(ArrayList<String>) request.getSession().getAttribute("replyServer");
                             for(int i = replyServer.size()-1;i>=0;i--){
                        %>
                        
                        <p style="margin-left:10px"> <%=replyServer.get(i).toString()%></p>
                        <%
                            }
                        %>        
                    </li>
                </ul>
             </div>       
        </aside>
<!--         #END# Left Sidebar -->
    </section>

    <section class="content">
        <div class="container-fluid">
            <div class="block-header align-center">
                <h2>FTP SERVER</h2>
            </div>
            <!-- Basic Table -->
            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="card">
                        <div class="header">

                            <c:set var="folderName" value="${folderName}"/> 
                            <c:set var="preFolder" value="${fn:substringBefore(folderName,'/')}" />
                            <c:choose>
                                <c:when test="${preFolder != ''}">
                                    <a href="listFolderServlet?folderName=${preFolder}" class="btn btn-success waves-effect">Previous Folder</a>
                                </c:when>
                                <c:otherwise >
                                    <a href="listFileServlet" class="btn btn-success waves-effect">Previous Folder</a>
                                </c:otherwise>
                            </c:choose>
                            
                             <form style="margin-top:6%" action="newFolderServlet" method="POST" >
                                 <input type="hidden" name="folderName" value="${folderName}">
                                <input type="text" placeholder="New Folder Name " name="newFolderName" required="required">
                                <button type="submit">Create New Folder</button>
                            </form>
                             <form style="margin-left: 39%;margin-top: -3%" action="uploadServlet" method="POST" enctype="multipart/form-data"  multiple="multiple">                               
                                 <label for="myfile">Select a file:</label>
                                <input type="file" name="fileName" required="required">
                              
                                <button type="submit">Upload</button>
                            </form>
                                 
                            <a style="margin-left:89%" href="disconnectServlet" class="btn btn-success waves-effect">Disconnect</a>
                        </div>
                        <div class="body table-responsive">
                            <table class="table table-bordered table-striped">
                                <thead class="btn-success">
                                    <tr>
                                        <th style="width: 2%">Icon</th>
                                        <th style="width: 15%">File Name</th>
                                        <th style="width: 15%">Size</th>
                                        <th style="width: 15%">Date</th>
                                        <th style="width: 15%">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="file" items="${listFile}">
                                        <c:choose>
                                            <c:when test="${file.isFile() == true}">
                                                <tr>
                                                    <td>  <img style="width: 50px;height: 40px" src="<c:url value="/resources/images/file.png"/>"/></td>
                                                    <td>${file.getName()}</td>
                                                    <td><fmt:formatNumber type="number" maxIntegerDigits="2" value="${file.getSize()/1000000}"/> MB</td>
                                                   <td><fmt:formatDate pattern = "HH:mm:ss dd-MM-yyyy" value = "${file.getTimestamp().getTime()}"  /></td>
                                                    <td>   
                                                        <button type="button"style="font-size:24px" data-toggle="modal" data-target="#${file.getName()}"><i class="fa fa-download"></i></button>
                                                        <c:set var="pathFolder" value="${folderName}/${file.getName()}"/>
                                                        <a href="deleteServlet?fileName=${pathFolder}"><button>Delete File</button></a>
                                                    </td>
                                                </tr>
                                            </c:when>
                                            <c:when test="${file.isDirectory() == true}">
                                                <tr>
                                                    <td> <img style="width: 50px;height: 40px" src="<c:url value="/resources/images/folder.png"/>"/></td>
                                                    <td>${file.getName()}</td>
                                                    <td></td>
                                                    <td></td>
                                                     
                                                     <td>
                                                         <c:set var="pathFolder" value="${folderName}/${file.getName()}"/>
                                                         <a href="listFolderServlet?folderName=${pathFolder}"><button><i class="material-icons">folder_open</i></button></a>
                                                     
                                                     </td>
                                                </tr>
                                            </c:when>
                                        </c:choose>    
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# Basic Table -->
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
            
            <h1 style="display:none" id="messagesError">${sessionScope.message}</h1>
          
            
        </div>
    </section>

     <script src="<c:url value="https://code.jquery.com/jquery-3.2.1.slim.min.js" />"></script>
            <script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" />"></script>
            <script src="<c:url value="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js" />"></script>
            <!-- Bootstrap Core Js -->
            <script src="<c:url value="/resources/plugins/bootstrap/js/bootstrap.js" />"></script>

            <!-- Select Plugin Js -->
            <script src="<c:url value="/resources/plugins/bootstrap-select/js/bootstrap-select.js"/>"></script>

            <!-- Slimscroll Plugin Js -->
            <script src="<c:url value="/resources/plugins/jquery-slimscroll/jquery.slimscroll.js"/>"></script>

            <!-- Waves Effect Plugin Js -->
            <script src="<c:url value="/resources/plugins/node-waves/waves.js"/>"></script>

            <!-- Custom Js -->
            <script src="<c:url value="/resources/js/admin.js"/>"></script>
            <script src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"/>"></script>
            <script src="<c:url value="//code.jquery.com/jquery-1.11.1.min.js"/>"></script>
         <script type="text/javascript">
              window.onload = function(){ 
                 var avai = document.getElementById("messagesError").textContent;
                   var check1 = avai.localeCompare("Download Sucessfully");
                   var check2 = avai.localeCompare("Upload Sucessfully");
                   var check3 = avai.localeCompare("Sorry. You don't have permission to upload at here ");
                    var check4 = avai.localeCompare("Delete Sucessfully");
                    var check5 = avai.localeCompare("File Download Path Not Correct");
                    var check6 = avai.localeCompare("File Already Existed");
                    var check7 = avai.localeCompare("File Already Existed In Server");
                   if (check1==0 || check2==0|| check3 == 0 || check4 == 0 || check5 == 0 || check6 == 0 || check7==0) alert(avai); 
                        //alert("${message}");
               }
        </script>
</body>

</html>
