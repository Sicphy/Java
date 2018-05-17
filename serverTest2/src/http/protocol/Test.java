/*package http.protocol;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Test implements GetMethod {
    private Wallpaper wallpaper = new Wallpaper("src/wallpaper");
    private String wallpapers = wallpaper.getWallpapers();

    @Override
    public String arrangeResponse(String fileName) {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: some-test-server\r\n" +
                "Content-Type: text/plain\r\n" +
                "Access-Control-Allow-Origin: *\r\n"+
                "Content-Length: " + wallpapers.length() + "\r\n" +
                "Connection: close\r\n\r\n";
        return response;
    }

    @Override
    public byte[] getBodyResponse(String fileName) {
        return wallpapers.getBytes();
    }
}*/