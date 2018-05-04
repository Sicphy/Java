package http.protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class HttpRequest {
    private InputStream is;
    private String request;
    private String method;
    private String version;
    private String url;
    private HashMap<String, String> attributes;

    HttpRequest(InputStream is) {
        this.is = is;
        request = new String();
    }

    public void readInputHeaders() throws Throwable {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while(true) {
            String s = br.readLine();
            if(s == null || s.trim().length() == 0) {
                break;
            }
            this.request += s;
            this.request += "\n";
            //System.out.println(s);
        }
        System.out.println(request);
        setHttpHeader(request);
        System.out.println(method);
        System.out.println(url);
        System.out.println(version);
        System.out.println(attributes);
    }

    private void setHttpHeader(String header) throws IOException
    {
        attributes = new HashMap<String, String>(); // init new attributes
        String [] headerLines = header.split("\r?\n"); // split it into all lines
        String [] firstLineFields = headerLines[0].split(" "); // split first line
        //if(firstLineFields.length != 3) throw new IOException(); // Bad request
        version = firstLineFields[2].trim(); // Version eg HTTP/1.0
        method = firstLineFields[0].trim(); // Action = GET, HEAD, POST, etc
        url = firstLineFields[1].trim(); // Parameter for action. eg URI, etc

        for(int i = 1; i < headerLines.length; ++i)
        {
            String []tokns = headerLines[i].split(": "); // Split at :
            if(tokns.length != 2) continue; // Bad attribute case
            attributes.put(tokns[0].trim().toLowerCase(), tokns[1].trim()); // param name->small, param value->original
        }
    }

    public String getUrl() {
        return this.url;
    }
}
