package bt.MensaApp.Model;

import java.io.Serializable;
import java.util.List;

/**
 * NavigationHeader class. Used to group content into sub clusters of content. NavigationHeader
 * includes a title to name the group.
 */

public class NavigationHeader implements Serializable, IDataProvider {
    /**
     * Title of the header
     */
    private String title;

    /**
     * Constructor for a navigation header
     * @param title The title displayed to the user
     */
    public NavigationHeader(String title) {
        this.title = title;
    }

    /**
     * Getter for title
     * @return The title of this header
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the current header
     * @param title The title to be set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Concrete implementation for IDataProvider
     * @return null - This element is not interactable
     */
    @Override
    public List<IDataProvider> getData() {
        return null;
    }
}
