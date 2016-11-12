package bt.MensaApp.Model.JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import bt.MensaApp.Model.IDataProvider;
import bt.MensaApp.Model.Mensa;
import bt.MensaApp.Model.Menu;
import bt.MensaApp.Model.NavigationHeader;
import bt.MensaApp.Model.Rwth.Uncompressed.RwthMensa;
import bt.MensaApp.Model.Rwth.Uncompressed.RwthUniversity;
import bt.MensaApp.Model.University;

/**
 * Static class providing a parser with all subtypes registered for polymorphic json.
 * Accessed from all JSON related classes.
 */

public class JsonParser {
    public static Gson getParser() {
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
}
