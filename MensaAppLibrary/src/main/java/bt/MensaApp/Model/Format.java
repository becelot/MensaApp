package bt.MensaApp.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bt.MensaApp.Model.Formats.HtmlFormat;
import bt.MensaApp.Model.Formats.JSONFormat;

/**
 * Created by bened on 11/8/2016.
 */

public abstract class Format implements IDataProvider, Serializable {
    private String adapter;

    public static List<IDataProvider> getFormats() {
        List<IDataProvider> formats = new ArrayList<>();
        formats.add(new NavigationHeader("Select format"));
        formats.add(new HtmlFormat("HTML"));
        formats.add(new JSONFormat("JSON"));
        return formats;
    }

    public Format(String adapter) {
        this.adapter = adapter;
    }

    public String getAdapter() {
        return adapter;
    }

    public void setAdapter(String adapter) {
        this.adapter = adapter;
    }

    public abstract List<IDataProvider> getUniversities();
}
