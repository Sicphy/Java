package http.protocol;

import java.lang.reflect.Method;
import java.util.PropertyResourceBundle;

public class GetCommand {

    private static final String GET_CLASS = "get.class";

    GetCommand() {

    }

    public String chooseGetClass(String url) {
        PropertyResourceBundle pr = (PropertyResourceBundle)
                PropertyResourceBundle.getBundle("http.protocol.get");
        String className = pr.getString(url);
        try {
            Class cl = Class.forName(className);
            GetMethod method = (GetMethod) cl.newInstance();

            try {
                String returning;
                returning = demoReflectionMethod(cl, method);
                return returning;
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace(System.out);
        }

        return null;
    }

    private static String demoReflectionMethod(Class cl, GetMethod sc) throws Exception {
        Method method1 = cl.getMethod("arrangeResponse");
        String simple = (String)method1.invoke(sc);
        return simple;
    }
}
