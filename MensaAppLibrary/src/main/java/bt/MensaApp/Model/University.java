package bt.MensaApp.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bened on 11/5/2016.
 */

public abstract class University implements Serializable, IDataProvider {
    private String name;


    public University(String name) {
        this.name = name;
    }

    public University(University other) {
        this.name = other.name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract List<IDataProvider> getMensaList();

    @Override
    public List<IDataProvider> getData() {
        return getMensaList();
    }
}
