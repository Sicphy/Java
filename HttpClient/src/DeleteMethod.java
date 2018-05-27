import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class DeleteMethod {
    private OutputStream socketOut;
    private File fileToDelete;

    DeleteMethod(OutputStream out) {
        socketOut = out;

    }

    public void sendRequest() {
        try {
            //socketOut.write("DELETE /images/" + fileName + "HTTP/1.1\r\n".getBytes());
            socketOut.write("Host: localhost:8080\r\n".getBytes());
            //socketOut.write("Content-Type:" + contentType + "\r\n".getBytes());
            socketOut.write("Connection: close\r\n".getBytes());
            socketOut.write("\r\n".getBytes());
            socketOut.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
