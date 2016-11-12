package bt.MensaApp.Data;

import java.util.Arrays;
import java.util.List;

import bt.MensaApp.Model.IDataProvider;
import bt.MensaApp.Model.NavigationHeader;
import bt.MensaApp.Model.Rwth.Uncompressed.RwthUniversity;

/**
 * Created by bened on 11/5/2016.
 */

public class DataContext {
    public static List<IDataProvider> getUniversityList() {
        return Arrays.asList((IDataProvider) new NavigationHeader("Alle Universitäten"), new RwthUniversity("RWTH Aachen"), new RwthUniversity("RWTH Aachen"));
    }
}
