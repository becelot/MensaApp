package bt.MensaApp.Model.JSON;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

import bt.MensaApp.Model.Format;
import bt.MensaApp.Model.IDataProvider;
import bt.MensaApp.Model.Mensa;
import bt.MensaApp.Model.University;
import bt.MensaApp.Net.HttpClient;

/**
 * Concrete implementation of the abstract mensa class. This class uses the JSON
 * format to communicate with a HTTP proxy. The proxy sends the data compressed in JSON format.
 */

public class JSONMensa extends Mensa {

    /**
     * Constructor for the mensa.
     * @param name Name of the mensa
     * @param webpage Webpage of the mensa
     * @param university University the mensa belongs to
     */
    public JSONMensa(String name, String webpage, University university) {
        super(name, webpage, university);
    }

    /**
     * Constructor for the mensa given another mensa. Copies all relevant fields.
     * @param mensa The other mensa
     */
    public JSONMensa(Mensa mensa) {
        super(mensa);
    }

    /**
     * Concrete abstract implementation for the mensa class. Retrieves a list of menus.
     * @return The list of menus ordered by data containing additional header information for grouping.
     */
    @Override
    public List<IDataProvider> getMenus() {
        HttpClient client = new HttpClient(JSONServerConfig.HOST, JSONServerConfig.PORT);
        String json;

        try {
            //Retrieve JSON from server
            client.connect();
            json = client.requestData(String.format(JSONServerConfig.MENU_API_HOST, URLEncoder.encode(this.getUniversity().getName(), "UTF-8"), URLEncoder.encode(this.getName(), "UTF-8")));



            //Convert JSON back to actual objects
            Type listType = new TypeToken<List<IDataProvider>>(){}.getType();
            return Format.generateTypeFactory().fromJson(json, listType);

        } catch (Exception e) {
            //Notify the container that an connection or parsing error occurred.
            return null;
        }
    }
}
