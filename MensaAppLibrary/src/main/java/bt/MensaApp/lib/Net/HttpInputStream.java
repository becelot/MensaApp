package bt.MensaApp.lib.Net;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bened on 11/6/2016.
 */

public class HttpInputStream {
    private Map<String, String> headerInformation;
    private String body;


    private String readUntilDelimiter(InputStream httpStream, String delimiter) throws IOException {
        String result = "";
        while (!result.contains(delimiter)) {
            byte[] buf = new byte[1];
            httpStream.read(buf, 0, 1);
            result += new String(buf);
        }
        result = result.substring(0, result.length()-2);

        return result;
    }

    private String socketRead(InputStream httpStream, int size) throws IOException {
        String result = "";
        while (size > 0) {
            byte[] buf = new byte[size];
            int recvdBytes = httpStream.read(buf, 0, size);
            if (recvdBytes <= 0) {
                throw new IOException("Socket timed out");
            }
            size -= recvdBytes;
            result += new String(buf, 0, recvdBytes);
            //Log.i("TODO", new String(buf, 0, recvdBytes));
        }

        return result;
    }

    public HttpInputStream(InputStream httpStream) throws IOException, HttpHeaderException {
        headerInformation = new HashMap<>();

        String response = readUntilDelimiter(httpStream, "\r\n");
        if (!response.equals("HTTP/1.1 200 OK")) {
            throw new HttpHeaderException();
        }

        String line = readUntilDelimiter(httpStream, "\r\n");
        while (!line.equals("")) {
            int headerSplit = line.indexOf(':');
            headerInformation.put(line.substring(0, headerSplit), line.substring(headerSplit+2));
            line = readUntilDelimiter(httpStream, "\r\n");
        }

        int contentLength = Integer.parseInt(this.getHeaderOption("Content-Length"));

        this.body = socketRead(httpStream, contentLength);
    }

    public String getHeaderOption(String option) {
        return headerInformation.get(option);
    }

    public String getBody() {
        return body;
    }
}
