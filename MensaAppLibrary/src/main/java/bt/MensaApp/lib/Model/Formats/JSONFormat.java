package bt.MensaApp.lib.Model.Formats;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import bt.MensaApp.lib.Model.Format;
import bt.MensaApp.lib.Model.IDataProvider;
import bt.MensaApp.lib.Model.JSON.JSONServerConfig;
import bt.MensaApp.lib.Net.HttpClient;

/**
 * Concrete implementation of the JSON compressed format. Contains information on how to communicate
 * with the server to retrieve a list of supported universities.
 */

public class JSONFormat extends Format {

    /**
     * Constructor for the JSON format
     * @param adapter JSON format adapter
     */
    public JSONFormat(String adapter) {
        super(adapter);
    }

    /**
     * Retrieve a list of supported universities by the HTTP proxy server
     * @return A list of universities containing addition header information for grouping
     */
    @Override
    public List<IDataProvider> getUniversities() {
        HttpClient client = new HttpClient(JSONServerConfig.HOST, JSONServerConfig.PORT);
        String json;

        try {
            //Request json data
            client.connect();
            json = client.requestData(String.format(JSONServerConfig.UNIVERSITY_API_KEY));

            //Convert JSON back to objects
            Type listType = new TypeToken<List<IDataProvider>>(){}.getType();
            return Format.generateTypeFactory().fromJson(json, listType);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
