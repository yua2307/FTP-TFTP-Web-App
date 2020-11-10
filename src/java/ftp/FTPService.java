/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author admin123
 */
public class FTPService {

  //  private static final String FTP_SERVER_ADDRESS = "192.168.1.127";
    private static final String FTP_SERVER_ADDRESS = "172.16.0.87";
    private static final int FTP_SERVER_PORT_NUMBER = 5217;
    private static final int FTP_TIMEOUT = 60000;
    private static final int BUFFER_SIZE = 1024 * 1024 * 1;
    private static final String FTP_USERNAME = "admin";
    private static final String FTP_PASSWORD = "admin";
    private static final String SLASH = "/";
    private static FTPClient ftpClient;
    
    
    public static List<FTPFile> getListFileFromFTPServer(String path){
    
        List<FTPFile> listFiles = new ArrayList<FTPFile>();
        // connect ftp server
        
        getConnectionServer();
        try {
            FTPFile[] ftpFiles = ftpClient.listFiles(path);
            System.out.println(path + " has " + ftpFiles.length + "  file(s)");
            if (ftpFiles.length > 0) {
                for (FTPFile ftpFile : ftpFiles) {
                    // add file to listFiles
                    if (ftpFile.isFile()) {
                        listFiles.add(ftpFile);
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            // disconnect ftp server
            disconnectFTPServer(ftpClient);
        }
        return listFiles;
    }
    
    public static boolean getConnectionServer() {
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
                return false;
            } else {
                ftpClient.setSoTimeout(FTP_TIMEOUT);
                // login ftp server
                ftpClient.login(FTP_USERNAME, FTP_PASSWORD);

                ftpClient.setDataTimeout(FTP_TIMEOUT);
                return true;
                //  System.out.println("connected");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void disconnectFTPServer(FTPClient ftpClient) {
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
