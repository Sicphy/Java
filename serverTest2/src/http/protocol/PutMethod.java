package http.protocol;

import java.io.*;
import java.util.Properties;

public class PutMethod implements HttpMethod {
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

        //properties.put(url, fileName);

        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/http/protocol/get.properties"));
            properties.save(dos, null);
            dos.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
