package com.personal.kyleofori.timetracker;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TrackableItem firstItem = new TrackableItem("Roger", 1, 6, "We went to the park and back");
        TrackableItem secondItem = new TrackableItem("Rager", 1, 3, "We went to the zoo");
        TrackableItem thirdItem = new TrackableItem("Ruger", 2, 8, "We went to the moon and back");

        TrackableItem[] values = new TrackableItem[] { firstItem, secondItem, thirdItem };
        TrackableItemArrayAdapter arrayAdapter = new TrackableItemArrayAdapter(this,
                R.layout.rowlayout, values);
        setListAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        TrackableItem item = (TrackableItem) getListAdapter().getItem(position);
        String toastText = item.getDescription();
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }
}
