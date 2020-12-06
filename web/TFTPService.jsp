<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>TFTP Server</title>
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
                <!--             Menu -->
                <div class="menu">
                    <ul class="list">
                        <li class="header">MENU</li>
                        <li>
                            <a href="listFileServlet">
                                <span>Home</span>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <span>TFTP Manager</span>
                            </a>
                            <ul class="ml-menu">
                                <li>
                                    <a href="#">TTFTP Manager</a>
                                </li>
                                <li>
                                    <a href="#">TTFTP Manager</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <span>TFTP Manager</span>
                            </a>
                            <ul class="ml-menu">
                                <li>
                                    <a href="#">TFTP Manager</a>
                                </li>
                                <li>
                                    <a href="#">TFTP Manager</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!--             #Menu 
                             Footer -->
                <div class="legal">
                    <div class="copyright">
                        &copy; 2018 <a href="javascript:void(0);">abc123</a>.
                    </div>
                    <div class="version">
                        <b>Version: </b> 1.0.0
                    </div>
                </div>
                <!--             #Footer -->
            </aside>
            <!--         #END# Left Sidebar -->
        </section>

        <section class="content">
            <div class="container-fluid">
                <div class="block-header align-center">
                    <h2>TFTP SERVER</h2>
                </div>
                <!-- Basic Table -->
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <h3><center> What do you want to do ?</center></h3>
                                <div id="choice" style="margin-left: 760px;margin-top: 15px">
                                <input type="radio" id="radioDownload" name="typeTFTP" value="Download"  >
                                <label for="radioDownload">Download</label>


                                <input type="radio" id="radioUpload"  name="typeTFTP" value="Upload" >
                                <label for="radioUpload">Upload</label>
                                </div>
                            </div>

                            <div class="body table-responsive">
                                <div id="forDownload" style="display:none">
                                    <form class="form-horizontal" action="downloadTFTPServlet" method="POST">

                                        <div class="row clearfix">
                                            <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                                <label for="">Host Name</label>
                                            </div>
                                            <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                                <div class="form-group">
                                                    <div class="form-line">
                                                        <input type="text" name="hostname" value="${sessionScope.hostname}" class="form-control" placeholder="Your IP Server" required="required" >
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row clearfix">
                                            <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                                <label for=""></label>
                                            </div>
                                            <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                                <div class="form-group">
                                                    <div class="form-line">
                                                        <input  type="text" name="fileName" placeholder="File Name You Want To Get In Server ?" required="required"  class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row clearfix">
                                            <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                                <label for=""></label>
                                            </div>
                                            <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                                <div class="form-group">
                                                    <div class="form-line">
                                                        <input  type="text" name="downloadFolder" value="${sessionScope.downloadFolder}" placeholder="Where to save in your computer ?" required="required" class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row clearfix">
                                            <div style="padding-left: 45%"class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                                <button type="submit" class="btn btn-success m-t-15 w-90 waves-effect">Download</button>
                                                <button type="reset" class="btn btn-warning m-t-15 w-90 waves-effect">Cancel</button>
                                            </div>
                                        </div>

                                    </form>
                                </div>


                                <div id="forUpload" style="display:none">
                                    <form class="form-horizontal" action="upoadTFTPServlet" method="POST" enctype="multipart/form-data">

                                        <div class="row clearfix">
                                            <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                                <label for="">Host Name</label>
                                            </div>
                                            <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                                <div class="form-group">
                                                    <div class="form-line">
                                                        <input type="text" name="hostnameForUpload" value="${sessionScope.hostname}" class="form-control" placeholder="Your IP Server" required="required" >
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row clearfix">
                                            <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                                <label for="">File  Want To upload to Server </label>
                                            </div>
                                            <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                                <div class="form-group">
                                                    <div class="form-line">
                                                        <input  type="file" name="fileName"  required="required"  multiple="multiple">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row clearfix">
                                            <div style="padding-left: 45%"class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                                <button type="submit" class="btn btn-success m-t-15 w-90 waves-effect">Upload</button>
                                                <button type="reset" class="btn btn-warning m-t-15 w-90 waves-effect">Cancel</button>
                                            </div>
                                        </div>
                                    </form>  
                                </div>

                            </div>
                        </div>
                    </div>
                </div>

                <h1 style="display:none" id="messagesError">${messageError}</h1>
                <!-- #END# Basic Table -->


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
            window.onload = function () {
                var avai = document.getElementById("messagesError").textContent;
                var check1 = avai.localeCompare("Wrong File Direction");
                var check2 = avai.localeCompare("Error: could not open local UDP socket.");
                var check3 = avai.localeCompare("Wrong host name");
                var check4 = avai.localeCompare("No File Name In Server");
                var check5 = avai.localeCompare("Download Successfully");
                var check6 = avai.localeCompare("Upload Successfully");

                if (check1 == 0 || check2 == 0 || check3 == 0 || check4 == 0 || check5 == 0 || check6 == 0)
                    alert(avai);
                //alert("${message}");
            }
        </script>
        <script type="text/javascript">
            window.onchange = function () {

                var radioDownload = document.getElementById("radioDownload");
                var radioUpload = document.getElementById("radioUpload");

                if (radioDownload.checked == true) {
                    document.getElementById("forUpload").style.display = 'none';
                    document.getElementById("forDownload").style.display = 'block';
                } else if (radioUpload.checked == true) {

                    document.getElementById("forDownload").style.display = 'none';
                    document.getElementById("forUpload").style.display = 'block';
                }

            }

        </script>
    </body>

</html>
