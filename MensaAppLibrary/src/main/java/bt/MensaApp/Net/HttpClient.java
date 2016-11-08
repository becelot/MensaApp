package bt.MensaApp.Net;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by bened on 11/6/2016.
 */

public class HttpClient {
    private static final int PORT = 80;
    private String host;
    private Socket socket;


    public HttpClient(String host) {
        this.host = host;
    }

    public void connect() throws IOException {
        socket = new Socket(host, PORT);
        //socket.setSoTimeout(10);
    }

    public void connect(String host) throws IOException {
        socket = new Socket(host, PORT);
        socket.setSoTimeout(10);
    }

    public String requestData(String ressource) throws IOException, HttpHeaderException {
        if (!socket.isConnected()) {
            this.connect();
        }

        HttpOutputStream outStream = new HttpOutputStream(socket.getOutputStream(), ressource);
        outStream.setHeaderOption("host", host);
        outStream.flush();

        HttpInputStream stream = new HttpInputStream(socket.getInputStream());

        return stream.getBody();
    }
}
