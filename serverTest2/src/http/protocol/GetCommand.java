package http.protocol;

import java.lang.reflect.Method;
import java.util.PropertyResourceBundle;

public class GetCommand {
    private Class cl;
    private GetMethod getMethod;

    GetCommand() {

    }

    public String chooseGetClass(String url) {
        PropertyResourceBundle pr = (PropertyResourceBundle)
                PropertyResourceBundle.getBundle("http.protocol.get");
        String className = null;

        if(pr.containsKey(url)) {
            className = pr.getString(url);
        } else {
            className = pr.getString("Error404");
        }

        try {
            Class cl = Class.forName(className);
            this.cl = cl;
            GetMethod method = (GetMethod) cl.newInstance();
            this.getMethod = method;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace(System.out);
        }

        return null;
    }

    public String getResponseMethod() throws Exception {
        Method method1 = this.cl.getMethod("arrangeResponse");
        String simple = (String)method1.invoke(this.getMethod);
        return simple;
    }

    public byte[] getBodyResponseMethod() throws Exception {
        Method method1 = this.cl.getMethod("getBodyResponse");
        byte[] simple = (byte[])method1.invoke(this.getMethod);
        return simple;
    }
}
