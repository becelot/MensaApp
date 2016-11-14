package bt.MensaApp.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Representation of a menu containing the category, name and price of the menu
 */

public class Menu implements Serializable, IDataProvider {
    /**
     * Name of the menu
     */
    private String name;

    /**
     * Price of the menu
     */
    private String price;

    /**
     * Category of the menu
     */
    private String category;

    /**
     * Constructor for a new menu
     * @param category Category of the menu
     * @param name Name of the menu
     * @param price Price of the menu
     */
    public Menu(String category, String name, String price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    /**
     * Retrieve the name of the menu
     * @return Menu name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of this menu
     * @param name Name of the menu
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the price of the menu
     * @return Price of the menu
     */
    public String getPrice() {
        return price;
    }

    /**
     * Set the price of the menu
     * @param price Price of the menu
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Get the menu category
     * @return The category of the menu
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set the menu category
     * @param category The category for this menu
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Concrete implementation for IDataProvider
     * @return null - This element is not interactable
     */
    @Override
    public List<IDataProvider> getData() {
        return null;
    }

    /**
     * Concrete implementation for IDataProvider
     * @return A flag indicating if content is expected
     */
    @Override
    public boolean hasNext() {
        return false;
    }
}
