import java.io.*;
import java.net.Socket;

public class HttpClient {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        Socket socket;
        socket = new Socket("localhost", port);

        OutputStream out = socket.getOutputStream();

        File f = new File("src/car.jpg");

        out.write("PUT /hello HTTP/1.1\r\n".getBytes());
        out.write("Host: localhost:8080\r\n".getBytes());
        out.write("Content-Type: image/jpeg\r\n".getBytes());
        out.write(("Content-Length: " + f.length() + "\r\n").getBytes());
        out.write("Connection: close\r\n".getBytes());
        out.write("\r\n".getBytes());
        out.flush();
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        System.out.println("Sending " + f.getName() + "...");
        try {
            byte[] byteArray = new byte[1024];
            FileInputStream fis = new FileInputStream(f.getPath());
            long s;
            s = f.length();
            dos.writeLong(s);
            dos.writeUTF(f.getName());
            int sp = (int)(s / 1024);
            if (s % 1024 != 0) sp++;
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
            Thread.sleep(500);
            while (s > 0) {
                int i = fis.read(byteArray);
                bos.write(byteArray, 0, i);
                s-= i;
            }
            bos.flush();
            fis.close();
            dos.close();
            socket.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {

        }
    }
}
