
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

public class TFTPService {
        static boolean receiveFile = true, closed;
        static int transferMode = TFTP.BINARY_MODE;
        static String  hostname, localFilename, remoteFilename;
        static TFTPClient tftp;
        static int timeout = 60000;
        static boolean verbose = false;
    private static void open(TFTPClient tftp) {
                // Get host and file arguments
        hostname = "192.168.1.7";
        localFilename = "P:\\Cn Web\\New folder\\CongNgheWebTest.pdf";
        remoteFilename = "/CongNgheWeb.pdf";

        // Create our TFTP instance to handle the file transfer.
        if (verbose) {
            tftp = new TFTPClient() {
                protected void trace(final String direction, final TFTPPacket packet) {
                    System.out.println(direction + " " + packet);
                }
            };
        } else {
            tftp = new TFTPClient();
        }

        // We want to timeout if a response takes longer than 60 seconds
        tftp.setDefaultTimeout(timeout);

        // We haven't closed the local file yet.
        closed = false;
        try
        {
            tftp.open();
        }
        catch (final SocketException e)
        {
            throw new RuntimeException("Error: could not open local UDP socket.", e);
        }
    }
    private static boolean send(final int transferMode, final String hostname, final String localFilename,
            final String remoteFilename, final TFTPClient tftp) {
        boolean closed;
        FileInputStream input = null;

        // Try to open local file for reading
        try
        {
            input = new FileInputStream(new File(localFilename));
        }
        catch (final IOException e)
        {
            tftp.close();
            throw new RuntimeException("Error: could not open local file for reading.", e);
        }

        open(tftp);
        System.out.println(remoteFilename+"  :  " +input);
        // Try to send local file via TFTP
        try
        {
            tftp.sendFile(remoteFilename, transferMode, input, "192.168.1.7", 69);
//            final String [] parts = hostname.split(":");
//            if (parts.length == 2) {
//                tftp.sendFile(remoteFilename, transferMode, input, parts[0], Integer.parseInt(parts[1]));
//            } else {
//                tftp.sendFile(remoteFilename, transferMode, input, hostname);
//            }
        }
        catch (final UnknownHostException e)
        {
            System.err.println("Error: could not resolve hostname.");
            System.err.println(e.getMessage());
            System.exit(1);
        }
        catch (final IOException e)
        {
            System.err.println("Error: I/O exception occurred while sending file.");
            System.err.println(e.getMessage());
            System.exit(1);
        }
        finally
        {
            // Close local socket and input file
            closed = close(tftp, input);
        }

        return closed;
    }
//        localFilename = "P:\\Cn Web\\New folder\\CongNgheWebTest.pdf";
//        remoteFilename ko bao gom /tftp/
//        remoteFilename = "/CongNgheWeb.pdf";
    private static boolean receive(final int transferMode, final String hostname, final String localFilename,
            final String remoteFilename, final TFTPClient tftp) {
        boolean closed;
        OutputStream output = null;
        File file;

        file = new File(localFilename);

        // If file exists, don't overwrite it.
        if (file.exists())
        {
            System.err.println("Error: " + localFilename + " already exists.");
            return false;
        }

        // Try to open local file for writing
        try
        {
            output = new BufferedOutputStream(new FileOutputStream(file));
        }
        catch (final IOException e)
        {
            tftp.close();
            throw new RuntimeException("Error: could not open local file for writing.", e);
        }

        open(tftp);

        // Try to receive remote file via TFTP
        System.err.println(remoteFilename + "  " + localFilename);
        try
        {
            tftp.receiveFile(remoteFilename, transferMode, output,"192.168.1.7", 69);
        }
        catch (final UnknownHostException e)
        {
            System.err.println("Error: could not resolve hostname.");
            System.err.println(e.getMessage());
            System.exit(1);
        }
        catch (final IOException e)
        {
            System.err.println(
                "Error: I/O exception occurred while receiving file.");
            System.err.println(e.getMessage());
            System.exit(1);
        }
        finally
        {
            // Close local socket and output file
            closed = close(tftp, output);
        }

        return closed;
    }
    
    
    
    private static boolean close(final TFTPClient tftp, final Closeable output) {
        boolean closed;
        tftp.close();
        try
        {
            if (output != null) {
                output.close();
            }
            closed = true;
        }
        catch (final IOException e)
        {
            closed = false;
            System.err.println("Error: error closing file.");
            System.err.println(e.getMessage());
        }
        return closed;
    }    
  
}
