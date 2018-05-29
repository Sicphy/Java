package http.protocol;

import java.io.*;

import static http.protocol.StringConstants.okResponse;

public class DeleteMethod implements HttpMethod {
    @Override
    public void executeMethod(String fileName, OutputStream os) {
        System.out.println(fileName);
        File file = new File("src/repository/" + fileName);
        file.delete();
        Response response = new Response();

        response.setState(okResponse);

        try {
            os.write(response.getResponse().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
