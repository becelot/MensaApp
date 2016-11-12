package bt.MensaApp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;

import bt.MensaApp.Model.Format;
import bt.MensaApp.Model.IDataProvider;

/**
 * Startup activity. Showing the splash screen for 3 seconds while loading the data.
 */
public class MainActivity extends Activity {

    /**
     * Called whenever the application is started. Bootstraps the navigation utility.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncTask<Object, String, Integer>() {
            @Override
            protected Integer doInBackground(Object... navigationList) {
                //Show splash screen
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //Start new activity containing the format selection
                Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                intent.putExtra(NavigationActivity.EXTRA_NAVIGATION_ITEM_KEY, new ArrayList<IDataProvider>(Format.getFormats()));
                startActivity(intent);

                return 0;
            }
        }.execute();
    }
}
