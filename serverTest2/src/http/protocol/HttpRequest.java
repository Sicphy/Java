package http.protocol;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class HttpRequest {
    private InputStream is;
    private String request;
    private String method;
    private String version;
    private String url;
    private String imageName;
    private HashMap<String, String> attributes;
    private Socket s;

    HttpRequest(InputStream is) {
        this.is = is;
        request = new String();
        imageName = null;
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
        }

        setHttpHeader(request);

        if(method.equals("PUT")) {
            long s;
            DataInputStream dis = new DataInputStream(is);
            s = dis.readLong();
            String fileName = dis.readUTF();
            System.out.println("File size: " + s);
            byte[] byteArray = new byte[1024];
            File f = new File("src/" + fileName);
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            int sp = (int) (s / 1024);
            if (s % 1024 != 0) sp++;
            BufferedInputStream bis = new BufferedInputStream(is);
            while (s > 0) {
                int i = bis.read(byteArray);
                fos.write(byteArray, 0, i);
                s -= i;
            }
            fos.close();
        }

        System.out.println(request);
        System.out.println(method);
        System.out.println(url);
        System.out.println(version);
        System.out.println(attributes);
        System.out.println(imageName);
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
        if(url.contains("/wallpaper/")) {
            imageName = url.substring(url.lastIndexOf("/wallpaper/")+11);
            url = url.substring(0, url.lastIndexOf("/wallpaper/")+10);
        }

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
    public String getImageName() { return this.imageName; }
    public String  getMethod() { return this.method; }
}
