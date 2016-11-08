package bt.MensaApp.Net;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bened on 11/6/2016.
 */

public class HttpOutputStream {
    private final PrintWriter httpStream;
    private final Map<String, String> headerInformation;
    private String ressource;

    public HttpOutputStream(OutputStream httpStream) {
        this.httpStream = new PrintWriter(httpStream, true);
        this.headerInformation = new HashMap<>();
    }

    public HttpOutputStream(OutputStream httpStream, String ressource) {
        this.httpStream = new PrintWriter(httpStream, true);
        this.headerInformation = new HashMap<>();
        this.ressource = ressource;
    }

    public void setHeaderOption(String option, String value) {
        headerInformation.put(option, value);
    }

    public String getHeaderOption(String option) {
        return headerInformation.get(option);
    }

    public String getRessource() {
        return ressource;
    }

    public void flush() {
        httpStream.write("GET " + this.ressource + " HTTP/1.1\r\n");
        for(String option : headerInformation.keySet()) {
             httpStream.write(option + ": " + headerInformation.get(option) + "\r\n");
        }
        httpStream.write("\r\n");
        httpStream.flush();
    }
}
