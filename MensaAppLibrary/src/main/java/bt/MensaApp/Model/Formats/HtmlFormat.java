package bt.MensaApp.Model.Formats;

import java.util.ArrayList;
import java.util.List;

import bt.MensaApp.Model.Format;
import bt.MensaApp.Model.IDataProvider;
import bt.MensaApp.Model.NavigationHeader;
import bt.MensaApp.Model.Rwth.Uncompressed.RwthUniversity;

/**
 * Concrete implementation of the HTML uncompressed format.
 */

public class HtmlFormat extends Format {
    /**
     * Constructor for the HtmlFormat
     * @param adapter Html
     */
    public HtmlFormat(String adapter) {
        super(adapter);
    }

    /**
     * Retrieves a list of universities currently supported by the application.
     * @return A list of universities and headers used for grouping
     */
    @Override
    public List<IDataProvider> getUniversities() {
        List<IDataProvider> universityList = new ArrayList<>();
        universityList.add(new NavigationHeader("Universität"));
        universityList.add(new RwthUniversity("RWTH Aachen"));
        return universityList;
    }
}
