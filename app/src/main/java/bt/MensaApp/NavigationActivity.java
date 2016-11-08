package bt.MensaApp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import bt.MensaApp.Model.IDataProvider;

/**
 * Created by bened on 11/7/2016.
 */

public class NavigationActivity extends Activity {
    public static final String EXTRA_NAVIGATION_ITEM_KEY = "EXTRA_NAVIGATION_ITEM_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FragmentNavigation navigationFragment = new FragmentNavigation();
        navigationFragment.setNavigationElements((ArrayList<IDataProvider>) intent.getSerializableExtra(EXTRA_NAVIGATION_ITEM_KEY));

        fragmentTransaction.replace(android.R.id.content, navigationFragment);

        fragmentTransaction.commit();
    }
}
