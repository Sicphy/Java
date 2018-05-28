package sample;

import java.io.IOException;

public class PutController {
    private PutMethod putMethod;

    PutController(PutMethod putMethod){
        this.putMethod = putMethod;
    }

    public void startMethod() {
        putMethod.sendRequest();
    }

    public void setPathName(String pathName) {
        putMethod.setFilePath(pathName);
    }
}
