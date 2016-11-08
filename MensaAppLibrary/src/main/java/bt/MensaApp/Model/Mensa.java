package bt.MensaApp.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bened on 11/5/2016.
 */

public abstract class Mensa implements Serializable, IDataProvider {
    private String name;
    private String webpage;


    public Mensa(String name, String webpage) {
        this.name = name;
        this.webpage = webpage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract List<IDataProvider> getMenus();

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

}
