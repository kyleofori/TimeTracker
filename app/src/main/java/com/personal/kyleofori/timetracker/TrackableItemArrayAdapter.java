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

        TrackableItem item = items.get(position);
        name.setText(item.getName());
        level.setText(Integer.toString(item.getLevel()));
        hours.setText(Double.toString(item.getHours()));
        description.setText(item.getDescription());
        if(item.getBitmap() != null) {
            imageView.setImageBitmap(item.getBitmap());
        }

        return rowView;
    }
}
