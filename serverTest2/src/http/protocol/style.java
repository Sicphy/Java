package http.protocol;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Style implements GetMethod {
    private File file = new File("src/Style.css");

    @Override
    public String arrangeResponse(String fileName) {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: some-test-server\r\n" +
                "Content-Type: text/css\r\n" +
                "Content-Length: " + file.length() + "\r\n" +
                "Connection: close\r\n\r\n";
        return response;
    }

    @Override
    public byte[] getBodyResponse(String fileName) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
