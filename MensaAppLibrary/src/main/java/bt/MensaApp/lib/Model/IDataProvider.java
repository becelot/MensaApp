package bt.MensaApp.lib.Model;

import java.util.List;

/**
 * Interface used to abstract navigation in the UI
 */
public interface IDataProvider {
    List<IDataProvider> getData();
    boolean hasNext();
}
