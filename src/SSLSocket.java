import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SSLSocket {
    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\aljam\\IdeaProjects\\SSLSocketExample\\client.p12");
        System.setProperty("javax.net.ssl.keyStorePassword", "iwmOVPNfombom");
        System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\aljam\\IdeaProjects\\SSLSocketExample\\test.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "iwmOVPNfombom");
        try {
            Socket sslSocket = SSLSocketFactory.getDefault().createSocket("localhost", 21201);
            for (; ; ) {
                sslSocket.getOutputStream().write("Hello".getBytes());
                byte[] bytes = new byte[1024];
                int readLen = sslSocket.getInputStream().read(bytes, 0, 1024);
                String str=new String(bytes,0,readLen);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
