package bt.MensaApp.Model.Formats;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

import bt.MensaApp.Model.Format;
import bt.MensaApp.Model.IDataProvider;
import bt.MensaApp.Model.JSON.JsonParser;
import bt.MensaApp.Net.HttpClient;

/**
 * Created by bened on 11/8/2016.
 */

public class JSONFormat extends Format {
    private final String HOST = "134.61.185.81";
    private final String UNIVERSITY_API_KEY = "/getUni";

    public JSONFormat(String adapter) {
        super(adapter);
    }

    @Override
    public List<IDataProvider> getUniversities() {
        HttpClient client = new HttpClient(HOST, 9000);
        String json;

        try {
            client.connect();

            json = client.requestData(String.format(UNIVERSITY_API_KEY));

            Type listType = new TypeToken<List<IDataProvider>>(){}.getType();
            return JsonParser.getParser().fromJson(json, listType);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
