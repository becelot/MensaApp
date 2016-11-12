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
 *
 */
public class FragmentNavigation extends Fragment {

    private final String SAVE_INSTANCE_NAVIGATION_ITEM = "SAVE_NAVIGATION_ITEM";
    private ArrayList<IDataProvider> navigationItems;


    public FragmentNavigation() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(SAVE_INSTANCE_NAVIGATION_ITEM)) {
            navigationItems = (ArrayList<IDataProvider>) savedInstanceState.getSerializable(SAVE_INSTANCE_NAVIGATION_ITEM);
        }
        View rootView = inflater.inflate(R.layout.fragment_navigator, container, false);

        ListView lv = (ListView)  rootView.findViewById(R.id.navigatorList);
        NavigationItemArrayAdapter adapter = new NavigationItemArrayAdapter(getActivity().getBaseContext(), navigationItems);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                new AsyncTask<IDataProvider, String, Integer>() {
                    ProgressDialog progressDialog;

                    @Override
                    protected Integer doInBackground(IDataProvider... navigationList) {
                        List<IDataProvider> nextNavigationItems = navigationList[0].getData();
                        if (nextNavigationItems == null) {
                            return -1;
                        }
                        ArrayList<Object> menuOrdered = new ArrayList<Object>(nextNavigationItems);
                        Intent intent = new Intent(getActivity(), NavigationActivity.class);
                        intent.putExtra(NavigationActivity.EXTRA_NAVIGATION_ITEM_KEY, menuOrdered);
                        startActivity(intent);
                        return 0;
                    }

                    @Override
                    protected void onPostExecute(Integer result) {
                        // execution of result of Long time consuming operation
                        progressDialog.dismiss();
                        if (result == -1) {
                            Toast.makeText(getActivity().getBaseContext(), "Connection failed.\n Check your internet connection or try again later.", Toast.LENGTH_LONG).show();
                        }
                    }



                    @Override
                    protected void onPreExecute() {
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVE_INSTANCE_NAVIGATION_ITEM, navigationItems);
    }

    public void setNavigationElements(ArrayList<IDataProvider> navigationItems) {

        this.navigationItems = navigationItems;
    }
}
