/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ftp.FTPService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author macbookpro
 */
public class downloadServlet extends HttpServlet {

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
            out.println("<title>Servlet downloadServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet downloadServlet at " + request.getContextPath() + "</h1>");
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
        
        String folderName = (String) request.getParameter("folderName");
        System.out.println("path  " + folderName);
        String fileName = (String) request.getParameter("fileName");
        System.out.println("fileName   " + fileName);
        String folderSave = (String) request.getParameter("folderSave");
        System.out.println("folderSave" + folderSave);
        try {
            
        boolean check = false;
        try {
            check = FTPService.dowloadFile(folderName + "/" + fileName, folderSave + "/" + fileName);
        } catch (Exception e){
          
         if (folderName == null) {
            request.getSession().setAttribute("message", "File Download Path Not Correct");
           request.getRequestDispatcher("listFileServlet").forward(request, response);
        } else {
            request.getSession().setAttribute("message", "File Download Path Not Correct");
            request.setAttribute("folderName", folderName);
            //request.getRequestDispatcher("listFolderServlet").forward(request, response);
            request.getRequestDispatcher("listFolderServlet").forward(request, response);
        }
        }

    if(check  ==true){
            if (folderName == null) {
            request.setAttribute("message", "Download Sucessfully");
            request.getRequestDispatcher("listFileServlet").forward(request, response);
        } else {
            request.getSession().setAttribute("message", "Download Sucessfully");
            request.setAttribute("folderName", folderName);
            //request.getRequestDispatcher("listFolderServlet").forward(request, response);
              request.getRequestDispatcher("listFolderServlet").forward(request, response);
        }

    }
        } catch (NullPointerException ex) {
             if (folderName == null) {
            request.getSession().setAttribute("message", "File Download Path Not Correct");
           request.getRequestDispatcher("listFileServlet").forward(request, response);
        } else {
            request.getSession().setAttribute("message", "File Download Path Not Correct");
            request.setAttribute("folderName", folderName);
            //request.getRequestDispatcher("listFolderServlet").forward(request, response);
            request.getRequestDispatcher("listFolderServlet").forward(request, response);
        }
            
        }
        

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
