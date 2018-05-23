package http.protocol;

import java.net.ServerSocket;
import java.net.Socket;


public class HttpServer {

    public static void main(String[] args) throws Throwable {
        ServerSocket ss = new ServerSocket(8080);
        while (true) {
            Socket s = ss.accept();
            System.err.println("Client accepted");
            new Thread(new SocketProcessor(s)).start();
        }
    }

    private static class SocketProcessor implements Runnable {

        private Socket s;
        private HttpRequest request;
        private HttpResponse response;

        private SocketProcessor(Socket s) throws Throwable {
            this.s = s;
            this.request = new HttpRequest(s.getInputStream());
            this.response = new HttpResponse(s.getOutputStream());
        }

        public void run() {
            try {
                request.readInputHeaders();
                response.setRequest(request);
                response.writeResponse();
            } catch (Throwable t) {
                /*do nothing*/
            } finally {
                try {
                    s.close();
                } catch (Throwable t) {
                    /*do nothing*/
                }
            }
            System.err.println("Client processing finished");
        }
    }
}
