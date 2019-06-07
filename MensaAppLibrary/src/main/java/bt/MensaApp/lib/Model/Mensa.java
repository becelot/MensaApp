package bt.MensaApp.lib.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Representation of a mensa. Contains the name, the university the mensa belongs to and
 * the API webpage the mensa is requesting
 */

public abstract class Mensa implements Serializable, IDataProvider {
    /**
     * Name of the mensa
     */
    private String name;

    /**
     * API webpage of the mensa
     */
    private String webpage;

    /**
     * University the mensa belongs to
     */
    private University university;


    /**
     * Constructor for a mensa
     * @param name The name of the mensa
     * @param webpage The webpage that is queried in the menu call
     * @param university The university this mensa belongs to
     */
    public Mensa(String name, String webpage, University university) {
        this.name = name;
        this.webpage = webpage;
        this.university = university;
    }

    /**
     * Constructor for a mensa given another mensa. All fields are copied over
     * @param mensa The other mensa
     */
    public Mensa(Mensa mensa) {
        this.name = mensa.getName();
        this.webpage = mensa.getWebpage();
        this.university = mensa.getUniversity();
    }

    /**
     * Get the name of the mensa
     * @return Name of the mensa
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the mensa
     * @param name The name for the mensa
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieve a list of menus for this mensa
     * @return List of menus and optional headers
     */
    public abstract List<IDataProvider> getMenus();

    /**
     * Concrete implementation of IDataProvider
     * @return List of menus and optional headers
     */
    @Override
    public List<IDataProvider> getData() {
        return getMenus();
    }

    /**
     * Get the API webpage for this mensa
     * @return
     */
    public String getWebpage() {
        return webpage;
    }

    /**
     * Set the API webpage for this mensa
     * @param webpage
     */
    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    /**
     * Get the university this mensa belongs to
     * @return The university
     */
    public University getUniversity() {
        return university;
    }

    /**
     * Concrete implementation for IDataProvider
     * @return A flag indicating if content is expected
     */
    @Override
    public boolean hasNext() {
        return true;
    }
}
