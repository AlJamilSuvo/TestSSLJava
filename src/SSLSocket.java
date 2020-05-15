import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SSLSocket {
    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\aljam\\IdeaProjects\\SSLSocketExample\\test.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "iwmOVPNfombom");
        System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\aljam\\IdeaProjects\\SSLSocketExample\\test.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "iwmOVPNfombom");
        try {
            Socket sslSocket = SSLSocketFactory.getDefault().createSocket("localhost", 2121);
            sslSocket.getOutputStream().write("Hello".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
