package bt.MensaApp;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bt.MensaApp.Model.IDataProvider;
import bt.MensaApp.ModelView.NavigationItemArrayAdapter;


/**
 * Fragment used for navigation using the list view.
 */
public class FragmentNavigation extends Fragment {
    /**
     * Serializable key used for saving context information in case of app lifecycle halt
     */
    private final String SAVE_INSTANCE_NAVIGATION_ITEM = "SAVE_NAVIGATION_ITEM";

    /**
     * List of items that are rendered to the user
     */
    private ArrayList<IDataProvider> navigationItems;


    public FragmentNavigation() {
        // Required empty public constructor
    }

    /**
     * Called when fragment is visualized
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Retrieve a list of navigation items if it was already saved
        if (savedInstanceState != null && savedInstanceState.containsKey(SAVE_INSTANCE_NAVIGATION_ITEM)) {
            navigationItems = (ArrayList<IDataProvider>) savedInstanceState.getSerializable(SAVE_INSTANCE_NAVIGATION_ITEM);
        }

        //Inflate fragment
        View rootView = inflater.inflate(R.layout.fragment_navigator, container, false);

        //Register navigation adapter used to inflate navigation items
        ListView lv = (ListView)  rootView.findViewById(R.id.navigatorList);
        NavigationItemArrayAdapter adapter = new NavigationItemArrayAdapter(getActivity().getBaseContext(), navigationItems);
        lv.setAdapter(adapter);

        //If item is clicked
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Run task async since it requires socket interaction
                new AsyncTask<IDataProvider, String, Integer>() {
                    ProgressDialog progressDialog;

                    /**
                     * Actual task that is executed in the background. Retrieves a list of
                     * new navigation items and starts a new activity containing thos items
                     * @param navigationList Selected element in the list
                     * @return
                     */
                    @Override
                    protected Integer doInBackground(IDataProvider... navigationList) {
                        //retrieve requested data
                        List<IDataProvider> nextNavigationItems = navigationList[0].getData();
                        if (nextNavigationItems == null) {
                            return -1;
                        }
                        ArrayList<Object> menuOrdered = new ArrayList<Object>(nextNavigationItems);

                        //Start new activity with new navigation items
                        Intent intent = new Intent(getActivity(), NavigationActivity.class);
                        intent.putExtra(NavigationActivity.EXTRA_NAVIGATION_ITEM_KEY, menuOrdered);
                        startActivity(intent);
                        return 0;
                    }

                    /**
                     * Called if the background task finished
                     * @param result Result of background task
                     */
                    @Override
                    protected void onPostExecute(Integer result) {
                        // Dismiss progress bar after background task completes
                        progressDialog.dismiss();

                        //If error occurred, toast a message
                        if (result == -1) {
                            Toast.makeText(getActivity().getBaseContext(), "Connection failed.\n Check your internet connection or try again later.", Toast.LENGTH_LONG).show();
                        }
                    }


                    /**
                     * Called before background task is executed. Shows a progress dialog.
                     */
                    @Override
                    protected void onPreExecute() {
                        //Show progress bar while task is executed
                        progressDialog = ProgressDialog.show(getActivity(),
                                "Fetching data",
                                "Requesting data...");
                    }


                    @Override
                    protected void onProgressUpdate(String... text) {
                    }
                }.execute((IDataProvider) adapterView.getItemAtPosition(position));
            }
        });

        return rootView;
    }

    /**
     * Save the navigation items if the fragment is recreated
     * @param outState The instance where objects are saved
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVE_INSTANCE_NAVIGATION_ITEM, navigationItems);
    }

    /**
     * Set navigation items. Used for bootstrapping.
     * @param navigationItems List of navigation items
     */
    public void setNavigationElements(ArrayList<IDataProvider> navigationItems) {

        this.navigationItems = navigationItems;
    }
}
