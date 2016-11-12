package bt.MensaApp.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bened on 11/5/2016.
 */

public abstract class Mensa implements Serializable, IDataProvider {
    private String name;
    private String webpage;
    private University university;


    public Mensa(String name, String webpage, University university) {
        this.name = name;
        this.webpage = webpage;
        this.university = university;
    }

    public Mensa(Mensa mensa) {
        this.name = mensa.getName();
        this.webpage = mensa.getWebpage();
        this.university = mensa.getUniversity();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract List<IDataProvider> getMenus();

    @Override
    public List<IDataProvider> getData() {
        return getMenus();
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    public University getUniversity() {
        return university;
    }
}
