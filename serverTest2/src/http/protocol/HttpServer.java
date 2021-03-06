package http.protocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static http.protocol.StringConstants.badRequestResponse;
import static http.protocol.StringConstants.notImplementedResponse;
import static http.protocol.StringConstants.notSupportedHttpVersionResponse;

public class HttpServer {

    public static void main(String[] args) throws Throwable {
        int portNumber = 8080;
        ServerSocket ss = new ServerSocket(portNumber);
        ThreadPool threadPool = new ThreadPool();
        while (true) {
            Socket s = ss.accept();
            System.err.println("Client accepted");
            Thread thread = threadPool.getThread(new SocketProcessor(s));
            while(thread == null) {
                thread = threadPool.getThread(new SocketProcessor(s));
            }
            thread.start();
            //new Thread(new SocketProcessor(s)).start();
        }
    }

    public static class SocketProcessor implements Runnable {

        private Socket s;
        private HttpRequest request;
        private HttpResponse response;
        private BadRequestResponse badRequest;

        private SocketProcessor(Socket s) throws Throwable {
            this.s = s;
            this.request = new HttpRequest(s.getInputStream());
            this.response = new HttpResponse(s.getOutputStream());
            this.badRequest = new BadRequestResponse(s.getOutputStream());

        }

        public void run() {
            try {
                request.readInputHeaders();
                response.setRequest(request);
                response.writeResponse();
            }  catch (BadRequestException e) {
              badRequest.setResponse(badRequestResponse);
              badRequest.sendResponse();
            } catch (BadHttpVersionException e) {
                badRequest.setResponse(notSupportedHttpVersionResponse);
                badRequest.sendResponse();
            } catch (BadMethodException e) {
                badRequest.setResponse(notImplementedResponse);
                badRequest.sendResponse();
            } catch (Throwable e) {

            } finally {
                try {
                    s.close();
                } catch (Throwable t) {
                    /*do nothing*/
                }
            }
            System.err.println("Client processing finished");
            ThreadPool.freeThread();
        }
    }
}