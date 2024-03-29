package bt.MensaApp.lib.Model.JSON;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

import bt.MensaApp.lib.Model.Format;
import bt.MensaApp.lib.Model.IDataProvider;
import bt.MensaApp.lib.Model.University;
import bt.MensaApp.lib.Net.HttpClient;

/**
 * Concrete implementation of the abstract university class. This class uses the JSON
 * format to communicate with a HTTP proxy. The proxy sends the data compressed in JSON format.
 */

public class JSONUniversity extends University {
    /**
     * Constructor for the university.
     * @param name Name of the university
     */
    public JSONUniversity(String name) {
        super(name);
    }

    /**
     * Constructor for the university given another university. Copies all relevant fields.
     * @param other The other university
     */
    public JSONUniversity(University other) {
        super(other);
    }

    /**
     * Concrete abstract implementation for the university class. Retrieves a list of canteens.
     * @return The list of canteens containing additional header information for grouping.
     */
    @Override
    public List<IDataProvider> getMensaList() {
        HttpClient client = new HttpClient(JSONServerConfig.HOST, JSONServerConfig.PORT);
        String json;

        try {
            //Retrieve json from server
            client.connect();
            json = client.requestData(String.format(JSONServerConfig.MENSA_API_HOST, URLEncoder.encode(this.getName(), "UTF-8")));

            System.out.println(json);
            //Convert JSON back to university and header objects
            Type listType = new TypeToken<List<IDataProvider>>(){}.getType();

            return Format.generateTypeFactory().fromJson(json, listType);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
