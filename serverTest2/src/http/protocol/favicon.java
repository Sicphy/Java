package http.protocol;

public class favicon implements GetMethod {
    @Override
    public String arrangeResponse() {
        String html = "<html><body><h1>Eto rabotaet</h1>" +
                "<p>kak eto rabotaet ??? </p></body></html>";
        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: some-test-server\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + html.length() + "\r\n" +
                "Connection: close\r\n\r\n";
        return response + html;
    }
}