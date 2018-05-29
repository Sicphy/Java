package http.protocol;

import java.io.*;

import static http.protocol.StringConstants.createResponse;

public class PutMethod implements HttpMethod {
    @Override
    public void executeMethod(String fileName, OutputStream os) {

        Response response = new Response();
        response.setType(createResponse);
        response.setContentLocation(fileName);

        try {
            os.write(response.getResponse().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}