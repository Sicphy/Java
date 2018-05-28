package http.protocol;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.PropertyResourceBundle;

import static http.protocol.StringConstants.*;

public class GetMethod implements HttpMethod {
     private File file;

     public void executeMethod(String fileName, OutputStream os) {
          try {
               os.write(arrangeResponse(fileName).getBytes());
               os.write(getBodyResponse(fileName));
          } catch (IOException e) {
               e.printStackTrace();
          }
     }

     private String arrangeResponse(String fileName) {
          Folder folder = new Folder(
                  "src/repository");
          PropertyResourceBundle contentTypePR = (PropertyResourceBundle)
                  PropertyResourceBundle.getBundle("http.protocol.contentType");
          String codeResponse;
          String contentType = "text/html";
          String expansion;

          expansion = fileName.substring(fileName.lastIndexOf('.'));

          if (folder.isExist(fileName)) {
               codeResponse = okResponse;
               contentType = contentTypePR.getString(expansion);
               file = new File("src/repository/" + fileName);
          } else {
               codeResponse = notFoundRespones;
               file = new File("src/repository/Error404.html");
          }

          String response = "HTTP/1.1" + codeResponse + "\r\n" +
                  "Server: some-test-server\r\n" +
                  "Content-Type: " + contentType + "\r\n" +
                  "Content-Length: " + file.length() + "\r\n" +
                  "Connection: close\r\n\r\n";

          return response;
     }

     private byte[] getBodyResponse(String fileName) {
          try {
               return Files.readAllBytes(file.toPath());
          } catch (IOException e) {
               e.printStackTrace();
          }
          return null;
     }
}