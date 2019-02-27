package bt.MensaApp.Model.Rwth.Uncompressed;

import java.util.Arrays;
import java.util.List;

import bt.MensaApp.Model.IDataProvider;
import bt.MensaApp.Model.NavigationHeader;
import bt.MensaApp.Model.University;

/**
 * Concrete implementation of the RWTH Aachen university class
 */

public class RwthUniversity extends University {

    /**
     * Constructor for the universiy
     * @param name Name of the university
     */
    public RwthUniversity(String name) {
        super(name);
    }

    /**
     * Constructor for the university given another university. Copies all relevant fields.
     * @param other The other university
     */
    public RwthUniversity(University other) {
        super(other);
    }

    /**
     * Concrete abstract implementation that retrieves the list of available canteens
     * @return The list of canteens with optional header information fields
     */
    @Override
    public List<IDataProvider> getMensaList() {
        return Arrays.asList((IDataProvider) new NavigationHeader("RWTH Aachen"),
                new RwthMensa("Mensa Academica", "/speiseplaene/academica-w.html", this),
                new RwthMensa("Mensa Ahornstraße", "/speiseplaene/ahornstrasse-w.html", this),
                new RwthMensa("Bistro", "/speiseplaene/templergraben-w.html", this),
                new RwthMensa("Mensa Vita", "/speiseplaene/vita-w.html", this),
                new RwthMensa("Forum Cafete", "/speiseplaene/forum-w.html", this),
                new NavigationHeader("Fachhochschule Aachen"),
                new RwthMensa("Gastro Goethe", "/speiseplaene/goethestrasse-w.html", this),
                new RwthMensa("Mensa Bayernallee", "/speiseplaene/bayernallee-w.html", this),
                new RwthMensa("Mensa Eupener Straße", "/speiseplaene/eupenerstrasse-w.html", this),
                new RwthMensa("Mensa Jülich", "/speiseplaene/juelich-w.html", this)
        );
    }
}
