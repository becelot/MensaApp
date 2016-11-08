package bt.MensaApp.Model.Formats;

import java.util.ArrayList;
import java.util.List;

import bt.MensaApp.Model.Format;
import bt.MensaApp.Model.IDataProvider;
import bt.MensaApp.Model.NavigationHeader;
import bt.MensaApp.Model.Rwth.Uncompressed.RwthUniversity;

/**
 * Created by bened on 11/8/2016.
 */

public class HtmlFormat extends Format {
    public HtmlFormat(String adapter) {
        super(adapter);
    }

    @Override
    public List<IDataProvider> getUniversities() {
        List<IDataProvider> universityList = new ArrayList<>();
        universityList.add(new NavigationHeader("Universität"));
        universityList.add(new RwthUniversity("RWTH Aachen", "HTML"));
        return universityList;
    }
}
