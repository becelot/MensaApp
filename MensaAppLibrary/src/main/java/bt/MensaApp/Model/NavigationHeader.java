package bt.MensaApp.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bened on 11/5/2016.
 */

public class NavigationHeader implements Serializable, IDataProvider {
    private String title;

    public NavigationHeader(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public List<IDataProvider> getData() {
        return null;
    }
}
