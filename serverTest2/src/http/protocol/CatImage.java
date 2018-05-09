package http.protocol;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CatImage implements GetMethod {
    private File file = new File("src/test.jpg");

    @Override
    public String arrangeResponse() {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: some-test-server\r\n" +
                "Content-Type: image/jpeg\r\n" +
                "Content-Length: " + file.length() + "\r\n" +
                "Connection: close\r\n\r\n";
        return response;
    }

    @Override
    public byte[] getBodyResponse() {try {
        return Files.readAllBytes(file.toPath());
    } catch (IOException e) {
        e.printStackTrace();
    }
        return null;
    }
}