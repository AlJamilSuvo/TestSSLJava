import javax.net.ssl.*;
import javax.net.ssl.SSLSocket;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;

public class SSLSocketAndr {
    public static void main(String[] args) {
        SSLSocket socket = null;


        try {
            KeyStore trustStore = KeyStore.getInstance("PKCS12");
            char[] trustStorePassword = "iwmOVPNfombom".toCharArray();


            InputStream trustStoreInputStream = new FileInputStream("C:\\Users\\aljam\\IdeaProjects\\SSLSocketExample\\test.p12");
            trustStore.load(trustStoreInputStream, trustStorePassword);
            trustStoreInputStream.close();
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(trustStore, trustStorePassword);

            TrustManagerFactory trustManagerFactory = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(trustStore);

            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");               //configure SSL Context to use TLS v1.2
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            socket = (SSLSocket) socketFactory.createSocket("localhost", 2121);
            socket.startHandshake();
            for (int i = 0; i < 100; i++) {
                String str = "Android to Server " + i;
                System.out.println(str);
                socket.getOutputStream().write(str.getBytes());
                byte[] bytes = new byte[1024];
                int len = socket.getInputStream().read(bytes, 0, 1024);
                String receive = new String(bytes, 0, len);
                System.out.println(receive);
                Thread.sleep(100);
            }


        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
