package bt.MensaApp.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bt.MensaApp.Model.Formats.HtmlFormat;
import bt.MensaApp.Model.Formats.JSONFormat;

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
}
