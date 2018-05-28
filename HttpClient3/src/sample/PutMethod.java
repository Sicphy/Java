package sample;
import java.io.*;
import java.net.Socket;

public class PutMethod {
    private String filePath;
    //private OutputStream socketOut;

    PutMethod(String filePath) {
        this.filePath = filePath;
        //this.socketOut = out;
    }

    public void sendRequest() {
        try {
            File f = new File(filePath);
            int port = 8080;
            Socket socket;
            socket = new Socket("localhost", port);
            OutputStream socketOut = socket.getOutputStream();

            socketOut.write(("PUT /" + f.getName() + " HTTP/1.1\r\n").getBytes());
            socketOut.write("Host: localhost:8080\r\n".getBytes());
            socketOut.write("Content-Type: image/jpeg\r\n".getBytes());
            socketOut.write(("Content-Length: " + f.length() + "\r\n").getBytes());
            socketOut.write("Connection: close\r\n".getBytes());
            socketOut.write("\r\n".getBytes());
            socketOut.flush();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                byte[] byteArray = new byte[1024];
                FileInputStream fis = new FileInputStream(f.getPath());
                long s;
                s = f.length();
                BufferedOutputStream bos = new BufferedOutputStream(socketOut);
                while (s > 0) {
                    int i = fis.read(byteArray);
                    bos.write(byteArray, 0, i);
                    s -= i;
                }
                bos.flush();
                fis.close();
                socketOut.close();
                socket.close();
            } catch (FileNotFoundException e) {
                System.err.println("File not found!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

