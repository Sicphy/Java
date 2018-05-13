package http.protocol;

import java.lang.reflect.Method;
import java.util.PropertyResourceBundle;

public class GetCommand {
    private Class cl;
    private GetMethod getMethod;

    GetCommand() {

    }

    public String chooseGetClass(String url, String fileName) {
        Wallpaper wallpaper = new Wallpaper("src/wallpaper");
        PropertyResourceBundle pr = (PropertyResourceBundle)
                PropertyResourceBundle.getBundle("http.protocol.get");
        String className = null;

        if(pr.containsKey(url) && (fileName == null || wallpaper.isExist(fileName))) {
            if(url.equals("/wallpaper") && fileName == null) {
                className = pr.getString("Error404");
            } else {
                className = pr.getString(url);
            }
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

    public String getResponseMethod(String fileName) throws Exception {
        Class[] paramTypes = new Class[] {String.class};
        Method method = this.cl.getMethod("arrangeResponse", paramTypes);
        String answer = (String)method.invoke(this.getMethod, fileName);
        return answer;
    }

    public byte[] getBodyResponseMethod(String fileName) throws Exception {
        Class[] paramTypes = new Class[] {String.class};
        Method method = this.cl.getMethod("getBodyResponse", paramTypes);
        byte[] answer = (byte[])method.invoke(this.getMethod, fileName);
        return answer;
    }
}
