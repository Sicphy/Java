package http.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class MyWrapper extends PushbackInputStream {
    MyWrapper(InputStream in) {
        super(in);
    }

    @Override
    public int available() throws IOException {
        int b = super.read();
        // do something specific?

        super.unread(b);
        return b;
    }
}