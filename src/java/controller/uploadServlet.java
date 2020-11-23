/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ftp.FTPService;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author macbookpro
 */
public class uploadServlet extends HttpServlet {
    
    
    private static final long serialVersionUID = 1L;
	
	// location to store file uploaded
	private static final String UPLOAD_DIRECTORY = "uploadTemp";

	// upload settings
	private static final int MEMORY_THRESHOLD 	= 1024 * 1024 * 3; 	// 3MB
	private static final int MAX_FILE_SIZE 		= 1024 * 1024 * 1024; // 1GB
	private static final int MAX_REQUEST_SIZE	= 1024 * 1024 * 1024; // 1GB
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
            out.println("<title>Servlet uploadServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet uploadServlet at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response,String fileNameForGet,String uploadPath)
            throws ServletException, IOException {
//       String folderName = request.getParameter("folderNameForUpload");
//        System.out.println(folderName);
//        request.setAttribute("message", "Folder Name :" + folderName + "  message Success");
//        request.getRequestDispatcher("message.jsp").forward(request, response);
          String folderName = (String) request.getSession().getAttribute("folderNameUpload"); 
          int check;
          if(folderName == null || folderName.equalsIgnoreCase("")){
                  check = FTPService.uploadFile(uploadPath+"/"+fileNameForGet, fileNameForGet,"");
          }
          else {
              System.out.println("Folder Name :" +folderName );
                check = FTPService.uploadFile(uploadPath+"/"+fileNameForGet, fileNameForGet,folderName);
          }
        
         if(check==1){
            if(folderName == null || folderName.equalsIgnoreCase("")){
                System.out.println("at1");
                request.setAttribute("message", "Upload Sucessfully");
             //   request.getRequestDispatcher("listFileServlet").forward(request, response);
                response.sendRedirect("listFileServlet");
            }
            else {
                System.out.println("at2");
                System.out.println(folderName);
                request.getSession().setAttribute("message", "Upload Sucessfully");
                request.setAttribute("folderName",folderName);
               // request.getRequestDispatcher("listFolderServlet").forward(request, response);
                response.sendRedirect("listFolderServlet");
            }
        }
         else if (check == 553){
             request.getSession().setAttribute("message", "Sorry. You don't have permission to upload at here ");
              response.sendRedirect("listFolderServlet");
         }
        else {
            request.getRequestDispatcher("403.jsp").forward(request, response);
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
        
//        HttpSession session = request.getSession(true);
//        String folderName = (String) session.getAttribute("folderNameUpload");

      
      // checks if the request actually contains upload file
                String fileNameForGet = null ;
		if (!ServletFileUpload.isMultipartContent(request)) {
			// if not, we stop here
			PrintWriter writer = response.getWriter();
			writer.println("Error: Form must has enctype=multipart/form-data.");
			writer.flush();
			return;
		}

		// configures upload settings
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// sets memory threshold - beyond which files are stored in disk 
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// sets temporary location to store files
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// sets maximum size of upload file
		upload.setFileSizeMax(MAX_FILE_SIZE);
		
		// sets maximum size of request (include file + form data)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// constructs the directory path to store upload file
		// this path is relative to application's directory
		String uploadPath = getServletContext().getRealPath("")
				 + UPLOAD_DIRECTORY;
		
		// creates the directory if it does not exist
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			// parses the request's content to extract file data
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				// iterates over form's fields
				for (FileItem item : formItems) {
					// processes only fields that are not form fields
					if (!item.isFormField()) {
                                                String fileName = new File(item.getName()).getName();
                                                fileNameForGet = fileName;
                                                System.out.println("File Name In Netbeans" + fileName);
						String filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
                                                System.out.println("File Path In Netbeans" + filePath);
						// saves the file on disk
						item.write(storeFile);
						request.setAttribute("message",
							"Upload has been done successfully!");
					}
				}
			}
		} catch (Exception ex) {
			request.setAttribute("message",
					"There was an error: " + ex.getMessage());
		}
		// redirects client to message page
//		getServletContext().getRequestDispatcher("/message.jsp").forward(
//				request, response);
               
        
                doGet(request, response,fileNameForGet,uploadPath);
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
