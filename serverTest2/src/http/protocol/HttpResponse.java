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
        //GetCommand getCommand = new GetCommand();
        //Wallpaper wallpaper = new Wallpaper("src/wallpaper");
        //getCommand.chooseGetClass(this.request.getUrl(), this.request.getImageName());
        //os.write(getCommand.getResponseMethod(this.request.getImageName()).getBytes());
        //os.write(getCommand.getBodyResponseMethod(this.request.getImageName()));
        //os.write(result.getBytes());
        ChooseMethod chooseMethod = new ChooseMethod();
        chooseMethod.chooseMethodClass(this.request.getMethod());
        chooseMethod.executeMethod(this.request.getUrl(), this.os);
        os.flush();
    }
}


