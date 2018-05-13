package http.protocol;

public interface GetMethod {
     String arrangeResponse(String fileName);
     byte[] getBodyResponse(String fileName);
}

