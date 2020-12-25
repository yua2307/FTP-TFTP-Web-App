/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ftp.FTPService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author macbookpro
 */
public class listFolderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet listFolderServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet listFolderServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
              String folderName=(String) request.getParameter("folderName");
        String folderNameUpload = (String) request.getSession().getAttribute("folderNameUpload"); 
        HttpSession session = request.getSession();
         ArrayList<String> replyServer = (ArrayList<String>) request.getSession().getAttribute("replyServer");
         if(folderNameUpload == null || folderNameUpload.equals("")){
              replyServer.add("257 /" +folderName+ " is the current directory");
         }else {
                 replyServer.add("257 /" +folderNameUpload+ " is the current directory");
         }
        
            
             
        System.out.println(folderNameUpload);
        if(folderNameUpload !=null && folderName == null){
              List<FTPFile> listFile =  FTPService.getListFileFromFTPServer("/"+folderNameUpload);
               FTPService.showServerReply2(FTPService.getFtpClientGlobal(), replyServer);
                // List<FTPFile> listFile =  FTPService.getListFileFromFTPServer("/download");
                 request.setAttribute("listFile", listFile);
                request.setAttribute("folderName", folderNameUpload); // 'Download'
             //   session.setAttribute("folderNameUpload", folderName);
                 request.getRequestDispatcher("listFolder1.jsp").forward(request, response);
                 request.getSession().removeAttribute("message");
        }
        else if(folderName == null || folderName.equalsIgnoreCase("")){
            session.removeAttribute("folderNameUpload");
          //  request.getRequestDispatcher("listFileServlet").forward(request, response);
            response.sendRedirect("listFileServlet");
        }
        else {
         List<FTPFile> listFile =  FTPService.getListFileFromFTPServer("/"+folderName);
          FTPService.showServerReply2(FTPService.getFtpClientGlobal(), replyServer);
       // List<FTPFile> listFile =  FTPService.getListFileFromFTPServer("/download");
        request.setAttribute("listFile", listFile);
        request.setAttribute("folderName", folderName); // 'Download'
        session.setAttribute("folderNameUpload", folderName);
        request.getRequestDispatcher("listFolder1.jsp").forward(request, response);
         request.getSession().removeAttribute("message");
        }
             request.getSession().setAttribute("replyServer", replyServer);
            
        } catch (Exception e) {
             response.sendRedirect("login.jsp");
//             String folderNameUpload = (String) request.getSession().getAttribute("folderNameUpload"); 
//             response.sendRedirect("listFolderServlet");
        }
      
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
