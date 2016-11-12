package bt.MensaApp.Model.JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

import bt.MensaApp.Model.IDataProvider;
import bt.MensaApp.Model.Mensa;
import bt.MensaApp.Model.Menu;
import bt.MensaApp.Model.NavigationHeader;
import bt.MensaApp.Model.Rwth.Uncompressed.RwthMensa;
import bt.MensaApp.Model.Rwth.Uncompressed.RwthUniversity;
import bt.MensaApp.Model.University;
import bt.MensaApp.Net.HttpClient;

/**
 * Created by bened on 11/8/2016.
 */

public class JSONUniversity extends University {
    private final transient String HOST = "134.61.185.81";
    private final transient String MENSA_API_HOST = "/getMensa?uni=%s";

    public JSONUniversity(String name, String adapter) {
        super(name, adapter);
    }

    public JSONUniversity(University other) {
        super(other);
    }

    @Override
    public List<IDataProvider> getMensaList() {
        HttpClient client = new HttpClient(HOST, 9000);
        String json;

        try {
            client.connect();

            json = client.requestData(String.format(MENSA_API_HOST, URLEncoder.encode(this.getName(), "UTF-8")));

            Type listType = new TypeToken<List<IDataProvider>>(){}.getType();
            return JsonParser.getParser().fromJson(json, listType);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
