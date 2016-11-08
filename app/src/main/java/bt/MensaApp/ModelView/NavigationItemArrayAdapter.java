package bt.MensaApp.ModelView;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
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
 * Created by bened on 11/5/2016.
 */

public class NavigationItemArrayAdapter extends ArrayAdapter<IDataProvider> {

    public NavigationItemArrayAdapter(Context context, List<IDataProvider> objects) {
        super(context, R.layout.university_view_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Object navItem = getItem(position);
        if (navItem instanceof NavigationHeader) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.header_view_item, null);


            NavigationHeader header = (NavigationHeader) navItem;

            ((TextView)convertView.findViewById(R.id.headerTitle)).setText(header.getTitle());

            return convertView;
        } else if (navItem instanceof University) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.university_view_item, null);

            University uni = (University) getItem(position);

            if (uni != null) {
                TextView uniName = (TextView) convertView.findViewById(R.id.universityName);
                uniName.setText(uni.getName());

                ((TextView) convertView.findViewById(R.id.universityAdapter)).setText(uni.getAdapter());
            }
        } else if (navItem instanceof Mensa) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.mensa_view_item, null);

            Mensa mensa = (Mensa) navItem;

            if (mensa != null) {
                ((TextView) convertView.findViewById(R.id.mensaName)).setText(mensa.getName());
            }
        } else if (navItem instanceof Menu) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.menu_view_item, null);


            Menu menu = (Menu) navItem;

            if (menu != null) {
                ((TextView) convertView.findViewById(R.id.menuCategory)).setText(menu.getCategory());
                ((TextView) convertView.findViewById(R.id.menuName)).setText(menu.getName());
                ((TextView) convertView.findViewById(R.id.menuPrice)).setText(menu.getPrice());
            }
        } else if (navItem instanceof Format) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.mensa_view_item, null);


            Format format = (Format) navItem;

            if (format != null) {
                ((TextView) convertView.findViewById(R.id.mensaName)).setText(format.getAdapter());
            }
        }

        return convertView;
    }
}
