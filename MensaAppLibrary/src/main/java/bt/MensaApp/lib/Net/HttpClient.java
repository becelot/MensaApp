package bt.MensaApp.lib.Net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by bened on 11/6/2016.
 */

public class HttpClient {
    protected int port = 80;
    protected String host;
    private Socket socket;


    public HttpClient(String host) {
        this.host = host;
    }

    public HttpClient(String host, int port ) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress(host, port), 15000);
        socket.setSoTimeout(15000);
    }

    public void connect(String host) throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress(host, port), 15000);
        socket.setSoTimeout(15000);
    }

    public String requestData(String ressource) throws IOException, HttpHeaderException {
        if (!socket.isConnected()) {
            this.connect();
        }

        HttpOutputStream outStream = new HttpOutputStream(socket.getOutputStream(), ressource);
        outStream.setHeaderOption("host", host);
        outStream.flush();

        System.out.println(ressource);
        HttpInputStream stream = new HttpInputStream(socket.getInputStream());

        return stream.getBody();
    }
}
