package http.protocol;


import java.io.OutputStream;

public class HttpResponse {
    private OutputStream os;
    private HttpRequest request;

    HttpResponse(OutputStream os) {
        this.os = os;
        this.request = null;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public void writeResponse() throws Throwable {
        GetCommand getCommand = new GetCommand();
        String result = getCommand.chooseGetClass(this.request.getUrl());
        os.write(result.getBytes());
        os.flush();
    }
}


