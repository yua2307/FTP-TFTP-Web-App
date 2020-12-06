
package tftp;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.apache.commons.net.tftp.TFTP;
import org.apache.commons.net.tftp.TFTPClient;
import org.apache.commons.net.tftp.TFTPPacket;

public class TFTPService2 {
     
     private static String TFTP_SERVER_ADDRESS;

    private static int TFTP_SERVER_PORT_NUMBER;

    public TFTPService2(String TFTP_SERVER_ADDRESS, int TFTP_SERVER_PORT_NUMBER) {
        this.TFTP_SERVER_ADDRESS = TFTP_SERVER_ADDRESS;
        this.TFTP_SERVER_PORT_NUMBER = TFTP_SERVER_PORT_NUMBER;
    }

    public TFTPService2(String TFTP_SERVER_ADDRESS) {
         this.TFTP_SERVER_ADDRESS = TFTP_SERVER_ADDRESS;
    }

    
    private static final TFTPClient tftpClient = getConnection2(TFTP_SERVER_PORT_NUMBER);

    private static final int transferMode = TFTP.BINARY_MODE;

    private static final int timeOut = Integer.MAX_VALUE;
    private static boolean verbose = false;

    private static boolean getConnection(String hostName) {
        try {
            TFTPService2 tftpService = new TFTPService2(hostName);
            TFTPClient tftp = new TFTPClient();
            tftp.setDefaultTimeout(timeOut);
            tftp.open();
            return true;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }

    private static TFTPClient getConnection2(int port) {
        try {
            // TFTPService2 tftpService = new TFTPService2(hostName, port);
            TFTPClient tftp = new TFTPClient();
            tftp.setDefaultTimeout(timeOut);
            tftp.open(port);
            return tftp;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;

    }

    public static boolean send(String localFilename, String remoteFilename) {

        boolean closed;
        FileInputStream input = null;
        try {

            input = new FileInputStream(localFilename);
            tftpClient.sendFile(remoteFilename, transferMode, input, TFTP_SERVER_ADDRESS, TFTP_SERVER_PORT_NUMBER);

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        } finally {

            closed = close(tftpClient, input);
        }
        return closed;
    }

    public static boolean receive(String localFilename, String remoteFilename) {

        boolean closed;
        OutputStream output = null;
        File file = new File(localFilename);
        try {
            FileOutputStream fileOut = new FileOutputStream(file);

            output = new BufferedOutputStream(fileOut);
            tftpClient.receiveFile(remoteFilename, transferMode, output, TFTP_SERVER_ADDRESS, TFTP_SERVER_PORT_NUMBER);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        } finally {
            closed = close(tftpClient, output);
        }
        return closed;
    }

    public static boolean close(final TFTPClient tftp, final Closeable output) {
        boolean closed;
        tftp.close();
        try {
            if (output != null) {
                output.close();
            }
            closed = true;
        } catch (final IOException e) {
            closed = false;
            System.err.println("Error: error closing file.");
            System.err.println(e.getMessage());
        }
        return closed;
    }

    public String getTFTP_SERVER_ADDRESS() {
        return TFTP_SERVER_ADDRESS;
    }

    public void setTFTP_SERVER_ADDRESS(String TFTP_SERVER_ADDRESS) {
        this.TFTP_SERVER_ADDRESS = TFTP_SERVER_ADDRESS;
    }

    public int getTFTP_SERVER_PORT_NUMBER() {
        return TFTP_SERVER_PORT_NUMBER;
    }

    public void setTFTP_SERVER_PORT_NUMBER(int TFTP_SERVER_PORT_NUMBER) {
        this.TFTP_SERVER_PORT_NUMBER = TFTP_SERVER_PORT_NUMBER;
    }

        
}
