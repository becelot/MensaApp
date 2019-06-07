package bt.MensaApp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import bt.MensaApp.lib.Model.IDataProvider;

/**
 * Navigation activity that contains a list view for navigation purpose.
 */

public class NavigationActivity extends Activity {
    /**
     * Intent parameter from previous activity
     */
    public static final String EXTRA_NAVIGATION_ITEM_KEY = "EXTRA_NAVIGATION_ITEM_KEY";

    /**
     * Called when acitivity is started. Sets the current view to the FragmentNavigation
     * @param savedInstanceState The saved instance from the app lifecycle state
     */
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
