package bt.MensaApp.Model.JSON;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

import bt.MensaApp.Model.IDataProvider;
import bt.MensaApp.Model.University;
import bt.MensaApp.Net.HttpClient;

/**
 * Created by bened on 11/8/2016.
 */

public class JSONUniversity extends University {

    public JSONUniversity(String name) {
        super(name);
    }

    public JSONUniversity(University other) {
        super(other);
    }

    @Override
    public List<IDataProvider> getMensaList() {
        HttpClient client = new HttpClient(JSONServerConfig.HOST, JSONServerConfig.PORT);
        String json;

        try {
            client.connect();

            json = client.requestData(String.format(JSONServerConfig.MENSA_API_HOST, URLEncoder.encode(this.getName(), "UTF-8")));

            Type listType = new TypeToken<List<IDataProvider>>(){}.getType();
            return JsonParser.getParser().fromJson(json, listType);

        } catch (Exception e) {
            return null;
        }
    }
}
