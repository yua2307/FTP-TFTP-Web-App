/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author admin123
 */
public class FTPService {

  //  private static final String FTP_SERVER_ADDRESS = "192.168.1.127";
    private static String FTP_SERVER_ADDRESS ;
    private  static int FTP_SERVER_PORT_NUMBER ;
    private static String FTP_USERNAME ;
    private static String FTP_PASSWORD;

    public FTPService(String FTP_SERVER_ADDRESS, int FTP_SERVER_PORT_NUMBER, String FTP_USERNAME, String FTP_PASSWORD) {
        this.FTP_SERVER_ADDRESS = FTP_SERVER_ADDRESS;
        this.FTP_SERVER_PORT_NUMBER = FTP_SERVER_PORT_NUMBER;
        this.FTP_USERNAME = FTP_USERNAME;
        this.FTP_PASSWORD = FTP_PASSWORD;
    }


    private static final int FTP_TIMEOUT = 60000;
    private static final int BUFFER_SIZE = 1024 * 1024 * 1;
    
    private static final String SLASH = "/";
    private static FTPClient ftpClient =getConnectionServer2();
    
    
    
    public static boolean dowloadFile(String filePath, String downloadFilePath) throws IOException{
        

//        
//        System.out.println("File PAth in server : " + filePath); // Đường dẫn download server
//        System.out.println("File PAth in client : " + downloadFilePath); // đường dẫn tải về client
        //long numOfSplash = filePath.chars().filter(ch -> ch == '/').count();
        if(filePath.startsWith(SLASH)){
                filePath = filePath.substring(1, filePath.length());
        }
        else {
            filePath = "/" + filePath;
        }
     //   System.out.println("File PAth in server After Edit : " + filePath);
      //  getConnectionServer();
            
         File downloadFile = new File(downloadFilePath); // Example in client : /Users/macbookpro/Desktop/testLTM + ten File 
         OutputStream  outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile)); // Example in server : /download + ten File 
         // download file from FTP Server
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftpClient.setBufferSize(BUFFER_SIZE);
        
        boolean success = ftpClient.retrieveFile(filePath, outputStream);
        showServerReply(ftpClient);
        outputStream.close();

        return success;
        
    }
   
    public static List<FTPFile> getListFileFromFTPServer(){
    
        List<FTPFile> listFiles = new ArrayList<FTPFile>();
        // connect ftp server
     //   getConnectionServer();
        try {
            FTPFile[] ftpFiles = ftpClient.listFiles();
           // System.out.println("Dri" + " has " + ftpFiles.length + "  file(s)");
            if (ftpFiles.length > 0) {
                for (FTPFile ftpFile : ftpFiles) {
                    listFiles.add(ftpFile);
                        
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
        return listFiles;
    }
    
    public static boolean makeNewFolder(String newFolderName) throws IOException{
        boolean check =  ftpClient.makeDirectory(newFolderName);
        showServerReply(ftpClient);
        return check;
    }
    
    public static List<FTPFile> getListFileFromFTPServer(String path){
    
        List<FTPFile> listFiles = new ArrayList<FTPFile>();
        // connect ftp server
       // getConnectionServer();
        try {
            FTPFile[] ftpFiles = ftpClient.listFiles("/"+ path);
           // System.out.println("Dri" + " has " + ftpFiles.length + "  file(s)");
            if (ftpFiles.length > 0) {
                for (FTPFile ftpFile : ftpFiles) {
                  
                    listFiles.add(ftpFile);
                        
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
        return listFiles;
    }
    
    private static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }
    public static int getConnectionServer() {       
        ftpClient = new FTPClient();
        try {
            System.out.println("connecting ftp server...");
            // connect to ftp server
            ftpClient.setDefaultTimeout(FTP_TIMEOUT);           
            ftpClient.connect(FTP_SERVER_ADDRESS, FTP_SERVER_PORT_NUMBER);
           
            // run the passive mode command
            ftpClient.enterLocalPassiveMode();
            // check reply code
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                
                //       disconnectFTPServer(ftpClient);
                //  throw new IOException("FTP server not respond!");
                return 0;
            } else {
                ftpClient.setSoTimeout(FTP_TIMEOUT);
                // login ftp server
                if(ftpClient.login(FTP_USERNAME, FTP_PASSWORD) == false)
                {
                    return 1;
                }
                else {
                     showServerReply(ftpClient);
                    ftpClient.setDataTimeout(FTP_TIMEOUT);
                    return 2;
                }
                
                //  System.out.println("connected");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public static FTPClient getConnectionServer2() {       
        ftpClient = new FTPClient();
        try {
            System.out.println("connecting ftp server...");
            // connect to ftp server
            ftpClient.setDefaultTimeout(FTP_TIMEOUT);

            ftpClient.connect(FTP_SERVER_ADDRESS, FTP_SERVER_PORT_NUMBER);
            showServerReply(ftpClient);
            // run the passive mode command
            ftpClient.enterLocalPassiveMode();
            // check reply code
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                //       disconnectFTPServer(ftpClient);
                //  throw new IOException("FTP server not respond!");
                return null;
            } else {
                ftpClient.setSoTimeout(FTP_TIMEOUT);
                // login ftp server
                if(ftpClient.login(FTP_USERNAME, FTP_PASSWORD) == false)
                {
                    return null;
                }
                else {
                    ftpClient.setDataTimeout(FTP_TIMEOUT);
                    return ftpClient;
                }
                
                //  System.out.println("connected");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    

    public static void disconnectFTPServer() {
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
                System.out.println("Disconnected to Server");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
