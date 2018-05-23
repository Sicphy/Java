package http.protocol;

import java.io.*;
import java.util.Properties;

public class DeleteMethod implements HttpMethod {
    @Override
    public void executeMethod(String url, OutputStream os) {
        Properties properties = new Properties();
        try {
            DataInputStream is = new DataInputStream(new FileInputStream("src/http/protocol/get.properties"));
            properties.load(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        properties.remove(url);

        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/http/protocol/get.properties"));
            properties.save(dos, null);
            dos.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: some-test-server\r\n" +
                "Content-Type: text/html\r\n" +
                //"Content-Length: " + file.length() + "\r\n" +
                "Connection: keep-alive\r\n\r\n";

        try {
            os.write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
