package bt.MensaApp.lib.Net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpsClient extends HttpClient {
    public HttpsClient(String host) {
        super(host);
    }

    @Override
    public void connect() {

    }

    @Override
    public void connect(String host) {
        this.host = host;
    }

    @Override
    public String requestData(String ressource) throws IOException, HttpHeaderException {
        String result = null;

        URL url = new URL(this.host + ressource);
        HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();

        httpsConn.setAllowUserInteraction(false);
        httpsConn.setInstanceFollowRedirects(true);
        httpsConn.setRequestMethod("GET");
        httpsConn.connect();

        int resCode = httpsConn.getResponseCode();
        if (resCode != HttpURLConnection.HTTP_OK) {
            throw new HttpHeaderException();
        }

        InputStream in = httpsConn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                in, "utf-8"), 8);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        in.close();
        result = sb.toString();

        return result;
    }
}
