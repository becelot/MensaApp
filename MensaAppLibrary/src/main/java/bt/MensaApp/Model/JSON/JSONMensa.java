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

public class JSONMensa extends Mensa {
    private final String HOST = "192.168.0.11";
    private final String MENU_API_HOST = "/getMenus?uni=RWTH%20Aachen&mensa=%s";

    private static Gson getParser() {
        RuntimeTypeAdapterFactory<IDataProvider> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(IDataProvider.class, "type")
                .registerSubtype(NavigationHeader.class, "header")
                .registerSubtype(University.class, "uni")
                .registerSubtype(RwthUniversity.class, "rwthUni")
                .registerSubtype(RwthMensa.class, "rwthMensa")
                .registerSubtype(JSONMensa.class, "jsonMensa")
                .registerSubtype(JSONUniversity.class, "jsonUni")
                .registerSubtype(Mensa.class, "mensa")
                .registerSubtype(Menu.class, "menu");
        return new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
    }

    public JSONMensa(String name, String webpage) {
        super(name, webpage);
    }

    @Override
    public List<IDataProvider> getMenus() {
        HttpClient client = new HttpClient(HOST, 9000);
        String json;

        try {
            client.connect();

            json = client.requestData(String.format(MENU_API_HOST, URLEncoder.encode(this.getName(), "UTF-8")));

            Type listType = new TypeToken<List<IDataProvider>>(){}.getType();
            return getParser().fromJson(json, listType);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
