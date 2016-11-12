package bt.MensaApp.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Abstract University class. Concrete implementation of an university have to provide getMensaList
 * functionality.
 */

public abstract class University implements Serializable, IDataProvider {
    /**
     * Name of the university
     */
    private String name;

    /**
     * University constructor given its name
     * @param name The name of the university
     */
    public University(String name) {
        this.name = name;
    }

    /**
     * University contructor given another university
     * @param other The other university the values should be copied from
     */
    public University(University other) {
        this.name = other.name;
    }


    /**
     * Getter for name
     * @return the name of the university
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name Set the name for this universtiy to name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieve the list of available canteens for this university
     * @return A list of canteens and optional header information
     */
    public abstract List<IDataProvider> getMensaList();

    /**
     * Concrete implementation for IDataProvider
     * @return A list of canteens and optional header information
     */
    @Override
    public List<IDataProvider> getData() {
        return getMensaList();
    }
}
