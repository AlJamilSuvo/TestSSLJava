import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;

public class SSLServer {
    public static void main(String[] args) {

        System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\aljam\\IdeaProjects\\SSLSocketExample\\test.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "iwmOVPNfombom");
        System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\aljam\\IdeaProjects\\SSLSocketExample\\test.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "iwmOVPNfombom");
        try {
            SSLServerSocket sslServerSocket = (SSLServerSocket) SSLServerSocketFactory.getDefault().createServerSocket(2121);
            Socket sslSocket = sslServerSocket.accept();
            System.out.println("New Socket");
            InputStream inputStream = sslSocket.getInputStream();
            OutputStream outputStream = sslSocket.getOutputStream();
            byte[] buffer = new byte[1024];
            while (true) {
                int readLen = inputStream.read(buffer, 0, 1024);
                if(readLen==-1) break;
                String str = new String(buffer, 0, readLen);
                System.out.println(str);
                String reply = "Reply " + str;
                outputStream.write(reply.getBytes());
            }


        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
