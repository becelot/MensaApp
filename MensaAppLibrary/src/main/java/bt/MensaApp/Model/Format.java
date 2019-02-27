package bt.MensaApp.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bt.MensaApp.Model.Formats.HtmlFormat;
import bt.MensaApp.Model.Formats.JSONFormat;
import bt.MensaApp.Model.JSON.JSONMensa;
import bt.MensaApp.Model.JSON.JSONUniversity;
import bt.MensaApp.Model.Rwth.Uncompressed.RwthMensa;
import bt.MensaApp.Model.Rwth.Uncompressed.RwthUniversity;

/**
 * Represenation of the format. Two formats can be chosen:
 *   - HTML format: This format is entirely uncompressed
 *   - JSON format: Directly communicates with HTTP proxy to retrieve content compressed in JSON format
 */

public abstract class Format implements IDataProvider, Serializable {
    /**
     * Format adapter name
     */
    private String adapter;

    /**
     * Get a list of supported formats
     * @return
     */
    public static List<IDataProvider> getFormats() {
        List<IDataProvider> formats = new ArrayList<>();
        formats.add(new NavigationHeader("Select format"));
        formats.add(new HtmlFormat("HTML"));
        formats.add(new JSONFormat("JSON"));
        return formats;
    }

    /**
     * Constructor for a format
     * @param adapter Format adapter name
     */
    public Format(String adapter) {
        this.adapter = adapter;
    }

    /**
     * Get the format adapter name
     * @return The format adapter name
     */
    public String getAdapter() {
        return adapter;
    }

    /**
     * Sets the format adapter name
     * @param adapter The format adapter name
     */
    public void setAdapter(String adapter) {
        this.adapter = adapter;
    }

    /**
     * Retrieves a list of supported universities
     * @return The list of universities containing optional NavigationHeaders
     */
    public abstract List<IDataProvider> getUniversities();

    /**
     * Concrete implementation of IDataProvider
     * @return The list of universities containing optional NavigationHeaders
     */
    public List<IDataProvider> getData() {
        return getUniversities();
    }

    /**
     * Concrete implementation for IDataProvider
     * @return A flag indicating if content is expected
     */
    @Override
    public boolean hasNext() {
        return true;
    }

    public static Gson generateTypeFactory() {
        RuntimeTypeAdapterFactory<IDataProvider> dataFactory =
                RuntimeTypeAdapterFactory.of(IDataProvider.class, "type")
                        .registerSubtype(RwthUniversity.class, "rwthUni")
                        .registerSubtype(JSONUniversity.class, "jsonUni")
                        .registerSubtype(JSONMensa.class, "jsonMensa")
                        .registerSubtype(RwthMensa.class, "rwthMensa")
                        .registerSubtype(Menu.class, "menu")
                        .registerSubtype(NavigationHeader.class, "header");

        RuntimeTypeAdapterFactory<University> universityAdapterFactory =
                RuntimeTypeAdapterFactory.of(University.class, "type")
                        .registerSubtype(RwthUniversity.class, "rwthUni")
                        .registerSubtype(JSONUniversity.class, "jsonUni");

        RuntimeTypeAdapterFactory<Mensa> mensaFactory =
                RuntimeTypeAdapterFactory.of(Mensa.class, "type")
                        .registerSubtype(JSONMensa.class, "jsonMensa")
                        .registerSubtype(RwthMensa.class, "rwthMensa");

        return new GsonBuilder()
                .registerTypeAdapterFactory(dataFactory)
                .registerTypeAdapterFactory(universityAdapterFactory)
                .registerTypeAdapterFactory(mensaFactory)
                .create();
    }
}
