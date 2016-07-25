package com.personal.kyleofori.timetracker;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TrackableItemArrayAdapter extends ArrayAdapter<TrackableItem> {
    private Context context;
    private TrackableItem[] items;

    public TrackableItemArrayAdapter(Context context, @LayoutRes int resource, TrackableItem[] items) {
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

        name.setText(items[position].getName());
        level.setText(Integer.toString(items[position].getLevel()));
        hours.setText(Integer.toString(items[position].getHours()));
        description.setText(items[position].getDescription());
        imageView.setImageResource(R.mipmap.ic_launcher);

        return rowView;
    }
}
