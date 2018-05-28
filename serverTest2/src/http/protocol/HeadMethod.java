package http.protocol;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.PropertyResourceBundle;

import static http.protocol.StringConstants.notFoundRespones;
import static http.protocol.StringConstants.okResponse;

public class HeadMethod implements HttpMethod {
    @Override
    public void executeMethod(String url, OutputStream os) {
        try {
            os.write(arrangeResponse(url).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String arrangeResponse(String url) {
        Folder wallpaper = new Folder("src/wallpaper");
        PropertyResourceBundle urlPR = (PropertyResourceBundle)
                PropertyResourceBundle.getBundle("http.protocol.get");
        PropertyResourceBundle contentTypePR = (PropertyResourceBundle)
                PropertyResourceBundle.getBundle("http.protocol.contentType");
        String fileName = null;
        String codeResponse;
        String contentType = "text/html";
        File file = null;
        String expansion;

        if(urlPR.containsKey(url)) {
            codeResponse = okResponse;

            fileName = urlPR.getString(url);

            expansion = fileName.substring(fileName.lastIndexOf('.'));
            contentType = contentTypePR.getString(expansion);

            if(expansion.equals(".txt") || expansion.equals(".html") || expansion.equals(".css")) {
                file = new File("src/text/" + fileName);
            } else {
                if(expansion.equals(".gif") || expansion.equals(".jpg") || expansion.equals(".png") || expansion.equals(".ico")) {
                    file = new File("src/images/" + fileName);
                } else {
                    codeResponse = notFoundRespones;
                    file = new File("src/text/Error404.html");
                }
            }
        } else {
            codeResponse = notFoundRespones;
            file = new File("src/text/Error404.html");
        }


        String response = "HTTP/1.1" + codeResponse + "\r\n" +
                "Server: some-test-server\r\n" +
                "Content-Type: " + contentType + "\r\n" +
                "Content-Length: " + file.length() + "\r\n" +
                "Connection: close\r\n\r\n";

        return response;
    }
}
