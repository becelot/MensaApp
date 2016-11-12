package bt.MensaApp.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bened on 11/5/2016.
 */

public class Menu implements Serializable, IDataProvider {
    private String name;
    private String price;
    private String category;

    public Menu(String category, String name, String price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public List<IDataProvider> getData() {
        return null;
    }
}
