package http.protocol;

import java.io.*;
import java.util.Properties;

public class PutMethod implements HttpMethod {
    @Override
    public void executeMethod(String fileName, OutputStream os) {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: some-test-server\r\n" +
                //"Content-Type: text/html\r\n" +
                //"Content-Length: " + file.length() + "\r\n" +
                "Connection: keep-alive\r\n\r\n";

        try {
            os.write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
