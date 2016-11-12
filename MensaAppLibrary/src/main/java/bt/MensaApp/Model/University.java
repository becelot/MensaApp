package bt.MensaApp.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bened on 11/5/2016.
 */

public abstract class University implements Serializable, IDataProvider {
    private String name;
    private String adapter;


    public University(String name, String adapter) {
        this.name = name;
        this.adapter = adapter;
    }

    public University(University other) {
        this.name = other.name;
        this.adapter = other.adapter;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdapter() {
        return adapter;
    }

    public void setAdapter(String adapter) {
        this.adapter = adapter;
    }

    public abstract List<IDataProvider> getMensaList();

    @Override
    public List<IDataProvider> getData() {
        return getMensaList();
    }
}
