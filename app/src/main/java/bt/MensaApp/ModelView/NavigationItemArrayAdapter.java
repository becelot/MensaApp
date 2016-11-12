package bt.MensaApp.ModelView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import bt.MensaApp.Model.Format;
import bt.MensaApp.Model.IDataProvider;
import bt.MensaApp.Model.Mensa;
import bt.MensaApp.Model.Menu;
import bt.MensaApp.Model.NavigationHeader;
import bt.MensaApp.Model.University;
import bt.MensaApp.R;

/**
 * Used to render specific list view items.
 */

public class NavigationItemArrayAdapter extends ArrayAdapter<IDataProvider> {

    /**
     * Constructor of NavigationItemArrayAdapter
     * @param context Activity context used for inflation
     * @param objects Navigation items used for rendering
     */
    public NavigationItemArrayAdapter(Context context, List<IDataProvider> objects) {
        super(context, R.layout.university_view_item, objects);
    }

    /**
     * Renders a specific element in the view
     * @param position Position of the element
     * @param convertView View of the element
     * @param parent Parent container
     * @return New view representing the element at position
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Get item that is rendered
        Object navItem = getItem(position);

        //If the element is a navigation header
        if (navItem instanceof NavigationHeader) {
            //Inflate the header
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.header_view_item, null);

            //And set required properties for header items
            NavigationHeader header = (NavigationHeader) navItem;
            ((TextView)convertView.findViewById(R.id.headerTitle)).setText(header.getTitle());

            return convertView;
        } else if (navItem instanceof University) { //if the element is an university
            //Inflate the university
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.university_view_item, null);

            //Set required properties for university list items
            University uni = (University) getItem(position);
            if (uni != null) {
                TextView uniName = (TextView) convertView.findViewById(R.id.universityName);
                uniName.setText(uni.getName());
            }
        } else if (navItem instanceof Mensa) {  //if the element is a mensa
            //Inflate the mensa
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.mensa_view_item, null);

            //Set required properties for mensa list items
            Mensa mensa = (Mensa) navItem;
            if (mensa != null) {
                ((TextView) convertView.findViewById(R.id.mensaName)).setText(mensa.getName());
            }
        } else if (navItem instanceof Menu) { //if the element is a menu
            //Inflate the menu
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.menu_view_item, null);

            //Set the required properties for menu list items
            Menu menu = (Menu) navItem;
            if (menu != null) {
                ((TextView) convertView.findViewById(R.id.menuCategory)).setText(menu.getCategory());
                ((TextView) convertView.findViewById(R.id.menuName)).setText(menu.getName());
                ((TextView) convertView.findViewById(R.id.menuPrice)).setText(menu.getPrice());
            }
        } else if (navItem instanceof Format) { //if the element is a format object
            //inflate the format (same style as mensa item is used)
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.mensa_view_item, null);

            //Set all the required properties for format
            Format format = (Format) navItem;
            if (format != null) {
                ((TextView) convertView.findViewById(R.id.mensaName)).setText(format.getAdapter());
            }
        }

        return convertView;
    }
}
