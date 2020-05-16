import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.security.*;
import java.security.cert.CertificateException;

public class SSLServer {
    public static void main(String[] args) {




        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            char[] keyStorePassword = "iwmOVPNfombom".toCharArray();
            InputStream keyStoreInputStream = new FileInputStream("C:\\Users\\aljam\\IdeaProjects\\SSLSocketExample\\test.p12");
            keyStore.load(keyStoreInputStream, keyStorePassword);
            keyStoreInputStream.close();
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, keyStorePassword);
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");               //configure SSL Context to use TLS v1.2
            sslContext.init(keyManagerFactory.getKeyManagers(), null, null);
            SSLServerSocketFactory socketFactory = sslContext.getServerSocketFactory();


            SSLServerSocket sslServerSocket = (SSLServerSocket) socketFactory.createServerSocket(2121);
            Socket sslSocket = sslServerSocket.accept();
            System.out.println("New Socket");
            InputStream inputStream = sslSocket.getInputStream();
            OutputStream outputStream = sslSocket.getOutputStream();
            byte[] buffer = new byte[1024];
            while (true) {
                int readLen = inputStream.read(buffer, 0, 1024);
                if (readLen == -1) break;
                String str = new String(buffer, 0, readLen);
                System.out.println(str);
                String reply = "Reply " + str;
                outputStream.write(reply.getBytes());
            }


        } catch (IOException | KeyStoreException e) {

            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

    }
}
