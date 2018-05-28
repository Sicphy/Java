package sample;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class DeleteMethod {
    private String fileName;

    DeleteMethod(String fileName) {
        this.fileName = fileName;
    }

    public void sendRequest() {
        try {
            int port = 8080;
            Socket socket;
            socket = new Socket("localhost", port);
            OutputStream socketOut = socket.getOutputStream();

            socketOut.write(("DELETE /" + fileName + " HTTP/1.1\r\n").getBytes());
            socketOut.write("Host: localhost:8080\r\n".getBytes());
            socketOut.write("Content-Type: image/jpeg\r\n".getBytes());
            socketOut.write("Connection: close\r\n".getBytes());
            socketOut.write("\r\n".getBytes());
            socketOut.flush();
            System.out.println(fileName);
            socketOut.close();
            socket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

