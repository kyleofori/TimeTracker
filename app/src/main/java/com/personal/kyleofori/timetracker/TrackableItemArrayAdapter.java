package com.personal.kyleofori.timetracker;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TrackableItemArrayAdapter extends ArrayAdapter<TrackableItem> {
    private Context context;
    private ArrayList<TrackableItem> items;

    public TrackableItemArrayAdapter(Context context, @LayoutRes int resource, ArrayList<TrackableItem> items) {
        super(context, resource, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView level = (TextView) rowView.findViewById(R.id.level);
        TextView hours = (TextView) rowView.findViewById(R.id.hours);
        TextView description = (TextView) rowView.findViewById(R.id.description);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        name.setText(items.get(position).getName());
        level.setText(Integer.toString(items.get(position).getLevel()));
        hours.setText(Integer.toString(items.get(position).getHours()));
        description.setText(items.get(position).getDescription());
        if(items.get(position).getIcon() != null) {
            imageView.setImageDrawable(items.get(position).getIcon().getDrawable());
        }

        return rowView;
    }
}
