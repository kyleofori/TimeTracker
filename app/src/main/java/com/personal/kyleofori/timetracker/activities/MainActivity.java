package com.personal.kyleofori.timetracker.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.personal.kyleofori.timetracker.R;
import com.personal.kyleofori.timetracker.TrackableItem;
import com.personal.kyleofori.timetracker.TrackableItemArrayAdapter;

public class MainActivity extends ListActivity {

    private Button addNewButton;

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

        addNewButton = (Button) findViewById(R.id.addNewItemBtn);
        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddItemActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        TrackableItem item = (TrackableItem) getListAdapter().getItem(position);
        String toastText = item.getDescription();
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }
}
