package http.protocol;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main implements GetMethod {
    File file = new File("src/index.html");

    @Override
    public String arrangeResponse() {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: some-test-server\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + file.length() + "\r\n" +
                "Connection: keep-alive\r\n\r\n";
        return response;
    }

    public byte[] getBodyResponse() {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}