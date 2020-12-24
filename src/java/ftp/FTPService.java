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
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.commons.net.ftp.FTPReply;
import java.io.InputStream;
import java.util.Date;
import org.apache.commons.io.FileExistsException;

/**
 *
 * @author admin123
 */
public class FTPService {

    //  private static final String FTP_SERVER_ADDRESS = "192.168.1.127";
    private static String FTP_SERVER_ADDRESS;
    private static int FTP_SERVER_PORT_NUMBER;
    private static String FTP_USERNAME;
    private static String FTP_PASSWORD;

    public FTPService(String FTP_SERVER_ADDRESS, int FTP_SERVER_PORT_NUMBER, String FTP_USERNAME, String FTP_PASSWORD) {
        this.FTP_SERVER_ADDRESS = FTP_SERVER_ADDRESS;
        this.FTP_SERVER_PORT_NUMBER = FTP_SERVER_PORT_NUMBER;
        this.FTP_USERNAME = FTP_USERNAME;
        this.FTP_PASSWORD = FTP_PASSWORD;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public static FTPClient getFtpClientGlobal() {
        return ftpClient;
    }

    private static final int FTP_TIMEOUT = Integer.MAX_VALUE;
    private static final int BUFFER_SIZE = 1024 * 1024 * 1;

    private static final String SLASH = "/";
    private static FTPClient ftpClient = getConnectionServer2();

    public static boolean checkFileExistsInServer(String filePath) throws IOException {
        System.out.println("file path to check :" + filePath);
        FTPFile[] remoteFiles = ftpClient.listFiles(filePath);
        if (remoteFiles.length > 0) {
            return true;
        }   
        return false;
    }

//    public static int uploadFile(String localFileFullName, String fileName, String hostDir) {
//        try {
////            File secondLocalFile = new File("C:\\Users\\quang\\Downloads\\Win32CmapTools_v6.04_09-24-19.exe");
//            System.out.println(hostDir);
//            System.out.println(fileName);
//            System.out.println(localFileFullName);
//           
//           ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//            System.out.println("FileUpload in netbeans " + localFileFullName);
//            File secondLocalFile = new File(localFileFullName);
//            FileInputStream inputStream = new FileInputStream(secondLocalFile);
//
//            System.out.println("Start uploading second file");
//            System.out.println(fileName);
//            OutputStream outputStream = ftpClient.storeFileStream(hostDir+"/"+fileName);
//            byte[] bytesIn = new byte[4096];
//            int read = 0;
////            long total = 0;
////            long fileLength = secondLocalFile.length();
////            long start = System.currentTimeMillis();
//            while ((read = inputStream.read(bytesIn)) != -1) {
////                total += read;
////                if ((System.currentTimeMillis() - start >= 1000) && (fileLength > 0)) {// only if total length is known
////                    System.out.println((int) (total * 100 / fileLength) + "%");
////                    start = System.currentTimeMillis();
////                }
//                outputStream.write(bytesIn, 0, read);
//            }
//
//            inputStream.close();
//            outputStream.close();
//
//            boolean completed = ftpClient.completePendingCommand();
//            if (completed) {
//             //   System.out.println("The second file is uploaded successfully.");
//                return 1;
//            }
//        } catch (Exception ex) {
//            showServerReply(ftpClient);
//           //System.out.println("Error: " + ex.getMessage());
//           return ftpClient.getReplyCode();
//        }
//        return -1;
//    }
    public static int uploadFile(String localFileFullName, String fileName, String hostDir) throws IOException {

        if (checkFileExistsInServer(hostDir + "/" + fileName) == true) {
            throw new FileExistsException("File Existed In Server");
        } else {
            try {
//            File secondLocalFile = new File("C:\\Users\\quang\\Downloads\\Win32CmapTools_v6.04_09-24-19.exe");
                System.out.println(hostDir);
                System.out.println(fileName);
                System.out.println(localFileFullName);

                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                System.out.println("FileUpload in netbeans " + localFileFullName);
                File secondLocalFile = new File(localFileFullName);
                FileInputStream inputStream = new FileInputStream(secondLocalFile);

                System.out.println("Start uploading second file");
                System.out.println(fileName);
                OutputStream outputStream = ftpClient.storeFileStream(hostDir + "/" + fileName);
                byte[] bytesIn = new byte[4096];
                int read = 0;
//            long total = 0;
//            long fileLength = secondLocalFile.length();
//            long start = System.currentTimeMillis();
                while ((read = inputStream.read(bytesIn)) != -1) {
//                total += read;
//                if ((System.currentTimeMillis() - start >= 1000) && (fileLength > 0)) {// only if total length is known
//                    System.out.println((int) (total * 100 / fileLength) + "%");
//                    start = System.currentTimeMillis();
//                }
                    outputStream.write(bytesIn, 0, read);
                    showServerReply(ftpClient);
                }

                inputStream.close();
                outputStream.close();

                boolean completed = ftpClient.completePendingCommand();
                showServerReply(ftpClient);
                if (completed) {
                    //   System.out.println("The second file is uploaded successfully.");
                    showServerReply(ftpClient);
                    return 1;
                }
            } catch (Exception ex) {
                showServerReply(ftpClient);
                //System.out.println("Error: " + ex.getMessage());
                return ftpClient.getReplyCode();
            }
            return -1;
        }
    }

//    
//ftpload.uploadDirectory("P:\\English\\Buoi 22", "/home/test/", "");
// dau vao
// localParentDir = dia chi file may client
// remoteDirPath = thu muc muon luu
// remoteParentDir =""
//    public Boolean uploadDirectory(String localParentDir, String remoteDirPath, String remoteParentDir)
//            throws IOException {
//        String dir1, dir2;
//        if (localParentDir.indexOf("/") != -1) {
//            dir1 = "/";
//        } else {
//            dir1 = "\\";
//        }
//        if (remoteDirPath.indexOf("/") != -1) {
//            dir2 = "/";
//        } else {
//            dir2 = "\\";
//        }
//        remoteDirPath = remoteDirPath + dir2 + localParentDir.substring(localParentDir.lastIndexOf(dir1) + 1);
//        boolean created = ftpClient.makeDirectory(remoteDirPath);
//        System.out.println("LISTING directory: " + localParentDir);
//        File localDir = new File(localParentDir);
//        File[] subFiles = localDir.listFiles();
//        if (subFiles != null && subFiles.length > 0) {
//            for (File item : subFiles) {
//                String remoteFilePath = remoteDirPath + dir2 + remoteParentDir
//                        + dir2 + item.getName();
//                if (remoteParentDir.equals("")) {
//                    remoteFilePath = remoteDirPath + dir2 + item.getName();
//                }
//                if (item.isFile()) {
//                    // upload the file
//                    String localFilePath = item.getAbsolutePath();
//                    System.out.println("About to upload the file: " + localFilePath);
//                .    boolean uploaded = uploadFile(localFilePath, item.getName(), remoteFilePath);
//                    if (uploaded) {
//                        System.out.println("UPLOADED a file to: "
//                                + remoteFilePath);
//                    } else {
//                        System.out.println("COULD NOT upload the file: "
//                                + localFilePath);
//                        return false;
//                    }
//                } else {
//                    // create directory on the server
//                    created = ftpClient.makeDirectory(remoteFilePath);
//                    if (created) {
//                        System.out.println("CREATED the directory: "
//                                + remoteFilePath);
//                    } else {
//                        System.out.println("COULD NOT create the directory: "
//                                + remoteFilePath);
//                        return false;
//                    }
//
//                    // upload the sub directory
//                    String parent = remoteParentDir + dir2 + item.getName();
//                    if (remoteParentDir.equals("")) {
//                        parent = item.getName();
//                    }
//
//                    localParentDir = item.getAbsolutePath();
//                    uploadDirectory(remoteDirPath, localParentDir,
//                            parent);
//                }
//            }
//        }
//        return true;
//    }
    public static boolean deleteFile(String filePath) throws IOException {
        boolean check = ftpClient.deleteFile(filePath);
        showServerReply(ftpClient);
        return check;
    }

    public static boolean deleteFolder(String folderName, String currentDir) throws IOException {

        String dirToList = folderName;
        if (!currentDir.equals("")) {
            dirToList += "/" + currentDir;
        }
        System.out.println(dirToList);
        FTPFile[] subFiles = ftpClient.listFiles(dirToList);

        if (subFiles != null && subFiles.length > 0) {
            for (FTPFile aFile : subFiles) {
                String currentFileName = aFile.getName();
                if (currentFileName.equals(".") || currentFileName.equals("..")) {
                    // skip parent directory and the directory itself
                    continue;
                }
                String filePath = folderName + "/" + currentDir + "/"
                        + currentFileName;
                if (currentDir.equals("")) {
                    filePath = folderName + "/" + currentFileName;
                }

                if (aFile.isDirectory()) {
                    // remove the sub directory
                    deleteFolder(dirToList, currentFileName);
                } else {
                    // delete the file
                    boolean deleted = ftpClient.deleteFile(filePath);
                    if (deleted) {
                        System.out.println("DELETED the file: " + filePath);
                    } else {
                        System.out.println("CANNOT delete the file: "
                                + filePath);
                    }
                }
            }

            // finally, remove the directory itself
            boolean removed = ftpClient.removeDirectory(dirToList);
            if (removed) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static boolean checkFileExistedInClient(String filePath) {
        File downloadFile = new File(filePath);
        if (downloadFile.exists()) {
            return true;
        }
        return false;

    }

    public static boolean dowloadFile(String filePath, String downloadFilePath) throws IOException {

//        
//        System.out.println("File PAth in server : " + filePath); // Đường dẫn download server
//        System.out.println("File PAth in client : " + downloadFilePath); // đường dẫn tải về client
        //long numOfSplash = filePath.chars().filter(ch -> ch == '/').count();
        if (checkFileExistedInClient(downloadFilePath)) {
            throw new FileExistsException("File Existed");
        } else {

            if (filePath.startsWith(SLASH)) {
                filePath = filePath.substring(1, filePath.length());
            } else {

                filePath = "/" + filePath;
            }
            //   System.out.println("File PAth in server After Edit : " + filePath);
            //  getConnectionServer();

            File downloadFile = new File(downloadFilePath); // Example in client : /Users/macbookpro/Desktop/testLTM + ten File 
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile)); // Example in server : /download + ten File 
            // download file from FTP Server
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.setBufferSize(BUFFER_SIZE);

            boolean success = ftpClient.retrieveFile(filePath, outputStream);
            showServerReply(ftpClient);
            outputStream.close();

            return success;
        }

    }

    public static List<FTPFile> getListFileFromFTPServer() {

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

    public static boolean makeNewFolder(String newFolderName) throws IOException {

         
            boolean check = ftpClient.makeDirectory(newFolderName);
            showServerReply(ftpClient);
            return check;
        

    }

    public static List<FTPFile> getListFileFromFTPServer(String path) {

        List<FTPFile> listFiles = new ArrayList<FTPFile>();
        // connect ftp server
        // getConnectionServer();
        try {
            FTPFile[] ftpFiles = ftpClient.listFiles("/" + path);
            // System.out.println("Dri" + " has " + ftpFiles.length + "  file(s)");
            if (ftpFiles.length > 0) {
                for (FTPFile ftpFile : ftpFiles) {

                    listFiles.add(ftpFile);

                }
            }

            showServerReply(ftpClient);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return listFiles;
    }

    public static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }

    public static void showServerReply2(FTPClient ftpClient, ArrayList<String> reply) {
        String[] listReply = ftpClient.getReplyStrings();

//        if(reply.size() >=20){
//           for(int i=0;i<10;i++){
//               reply.remove(i);
//           }
//        }
        for (String string : listReply) {

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();

            reply.add(string + "  at  " + formatter.format(date));
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
                if (ftpClient.login(FTP_USERNAME, FTP_PASSWORD) == false) {
                    return 1;
                } else {
                    showServerReply(ftpClient);
                    ftpClient.setDataTimeout(FTP_TIMEOUT);
                    return 2;
                }
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
                if (ftpClient.login(FTP_USERNAME, FTP_PASSWORD) == false) {
                    return null;
                } else {
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
