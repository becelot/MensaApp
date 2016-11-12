package bt.MensaApp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;

import bt.MensaApp.Model.Format;
import bt.MensaApp.Model.IDataProvider;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncTask<Object, String, Integer>() {
            @Override
            protected Integer doInBackground(Object... navigationList) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                intent.putExtra(NavigationActivity.EXTRA_NAVIGATION_ITEM_KEY, new ArrayList<IDataProvider>(Format.getFormats()));
                startActivity(intent);
                return 0;
            }
        }.execute();
    }
}
