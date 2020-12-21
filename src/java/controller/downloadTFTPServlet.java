/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.net.tftp.TFTP;
import tftp.TFTPService;

/**
 *
 * @author macbookpro
 */
public class downloadTFTPServlet extends HttpServlet {

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
            out.println("<title>Servlet downloadTFTPServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet downloadTFTPServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
         String hostname = (String) request.getParameter("hostname");
         System.out.println(hostname);
         request.getSession().setAttribute("hostname", hostname);
         String fileName = (String) request.getParameter("fileName"); // file Name In Server
                System.out.println(fileName);
         String downloadFolder = (String) request.getParameter("downloadFolder"); // folder save in client
              request.getSession().setAttribute("downloadFolder", downloadFolder);
                 System.out.println(downloadFolder);
                 
                 
         String pathDownload = downloadFolder+"/" + fileName;        
         try {
          
             
             int check = TFTPService.receive(TFTP.BINARY_MODE, hostname, pathDownload,fileName );
        }
         catch(UnknownHostException e) {    
            request.setAttribute("messageError", "Wrong host name");
            request.getRequestDispatcher("TFTPService.jsp").forward(request, response);
        } 
         catch ( FileNotFoundException e) {
            request.setAttribute("messageError", "Wrong File Direction");
            request.getRequestDispatcher("TFTPService.jsp").forward(request, response);
        }catch (IOException e){
            
            if(e.getMessage().equals("Connection timed out.")){
                request.setAttribute("messageError", "Wrong host name");
              request.getRequestDispatcher("TFTPService.jsp").forward(request, response);
            }else {
                 request.setAttribute("messageError", "No File Name In Server");
              request.getRequestDispatcher("TFTPService.jsp").forward(request, response);
            }
              
        } catch(Exception e){
                System.out.println(e.getMessage());
              request.getRequestDispatcher("403.jsp").forward(request, response);
        }

            request.setAttribute("messageError", "Download Successfully");
            request.getRequestDispatcher("TFTPService.jsp").forward(request, response);

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
