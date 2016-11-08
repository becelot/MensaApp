package bt.MensaApp.ModelView;

import java.util.List;

import bt.MensaApp.Model.IDataProvider;
import bt.MensaApp.Model.Mensa;
import bt.MensaApp.Model.University;

/**
 * Created by bened on 11/7/2016.
 */

public class NavigationTransitionHelper {
    public static List<IDataProvider> doTransition(Object obj) {
        if (obj instanceof University) {
            return ((University)obj).getMensaList();
        } else if (obj instanceof Mensa) {
            return ((Mensa)obj).getMenus();
        }

        return null;
    }
}
