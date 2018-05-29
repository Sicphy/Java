package http.protocol;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.PropertyResourceBundle;

public class ChooseMethod {
    private Class cl;
    private HttpMethod httpMethod;

    ChooseMethod() {

    }

    public String chooseMethodClass(String method) {
        Folder wallpaper = new Folder("src/wallpaper");
        PropertyResourceBundle pr = (PropertyResourceBundle)
                PropertyResourceBundle.getBundle("http.protocol.methods");
        String className = null;

        if(pr.containsKey(method)) {
                className = pr.getString(method);
        } else {
            className = pr.getString("Error404");
        }

        try {
            this.cl = Class.forName(className);
            this.httpMethod = (HttpMethod) cl.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace(System.out);
        }

        return null;
    }

    public String executeMethod(String url, OutputStream os) throws Exception {
        Class[] paramTypes = new Class[] {String.class, OutputStream.class};
        Method method = this.cl.getMethod("executeMethod", paramTypes);
        String answer = (String)method.invoke(this.httpMethod, url, os);
        return answer;
    }

}