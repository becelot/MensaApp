package bt.MensaApp.Model.Rwth.Uncompressed;

import java.util.Arrays;
import java.util.List;

import bt.MensaApp.Model.IDataProvider;
import bt.MensaApp.Model.NavigationHeader;
import bt.MensaApp.Model.University;

/**
 * Created by bened on 11/5/2016.
 */

public class RwthUniversity extends University {

    public RwthUniversity(String name, String adapter) {
        super(name, adapter);
    }

    @Override
    public List<IDataProvider> getMensaList() {
        return Arrays.asList((IDataProvider) new NavigationHeader("RWTH Aachen"),
                new RwthMensa("Mensa Academica", "/speiseplaene/academica-w.html"),
                new RwthMensa("Mensa Ahornstraße", "/speiseplaene/ahornstrasse-w.html"),
                new RwthMensa("Bistro", "/speiseplaene/templergraben-w.html"),
                new RwthMensa("Mensa Vita", "/speiseplaene/vita-w.html"),
                new RwthMensa("Forum Cafete", "/speiseplaene/forum-w.html"),
                new NavigationHeader("Fachhochschule Aachen"),
                new RwthMensa("Gastro Goethe", "/speiseplaene/goethestrasse-w.html"),
                new RwthMensa("Mensa Bayernallee", "/speiseplaene/bayernallee-w.html"),
                new RwthMensa("Mensa Eupener Straße", "/speiseplaene/eupenerstrasse-w.html"),
                new RwthMensa("Mensa Jülich", "/speiseplaene/juelich-w.html")
        );
    }
}
