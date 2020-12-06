/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftp;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.apache.commons.net.tftp.TFTP;
import org.apache.commons.net.tftp.TFTPClient;

/**
 *
 * @author macbookpro
 */
public class TFTPService {

       public static int receive(final int transferMode, final String hostname, final String localFilename,
            final String remoteFilename) throws FileNotFoundException, SocketException, UnknownHostException, IOException {
        OutputStream output = null;
        File file;

        file = new File(localFilename);
       
        // Try to open local file for writing
        output = new BufferedOutputStream(new FileOutputStream(file));
        TFTPClient tftp = new TFTPClient();
        tftp.setDefaultTimeout(6000);
        
        tftp.open();

        int check = tftp.receiveFile(remoteFilename, transferMode, output, InetAddress.getByName(hostname));
        close(tftp, output);
        return check;

    }

    private static boolean close(final TFTPClient tftp, final Closeable output) {
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
    
    
    public static void send (final int transferMode, final String hostname, final String localFilename,
            final String remoteFilename) throws FileNotFoundException, SocketException, IOException{
       
       
       FileInputStream input = new FileInputStream(new File(localFilename));
       TFTPClient tftp = new TFTPClient();
        tftp.setDefaultTimeout(600);
       tftp.open();
       tftp.sendFile(remoteFilename, transferMode, input, hostname);
       tftp.close();
       
       
   }

}
