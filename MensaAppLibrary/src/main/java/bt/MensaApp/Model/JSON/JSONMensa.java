package bt.MensaApp.Model.JSON;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

import bt.MensaApp.Model.IDataProvider;
import bt.MensaApp.Model.Mensa;
import bt.MensaApp.Model.University;
import bt.MensaApp.Net.HttpClient;

/**
 * Created by bened on 11/8/2016.
 */

public class JSONMensa extends Mensa {



    public JSONMensa(String name, String webpage, University university) {
        super(name, webpage, university);
    }

    public JSONMensa(Mensa mensa) {
        super(mensa);
    }

    @Override
    public List<IDataProvider> getMenus() {
        HttpClient client = new HttpClient(JSONServerConfig.HOST, JSONServerConfig.PORT);
        String json;

        try {
            client.connect();

            json = client.requestData(String.format(JSONServerConfig.MENU_API_HOST, URLEncoder.encode(this.getUniversity().getName(), "UTF-8"), URLEncoder.encode(this.getName(), "UTF-8")));

            Type listType = new TypeToken<List<IDataProvider>>(){}.getType();
            return JsonParser.getParser().fromJson(json, listType);

        } catch (Exception e) {
            return null;
        }
    }
}
