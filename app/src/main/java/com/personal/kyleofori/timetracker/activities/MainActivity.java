package com.personal.kyleofori.timetracker.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.personal.kyleofori.timetracker.R;
import com.personal.kyleofori.timetracker.TrackableItem;
import com.personal.kyleofori.timetracker.TrackableItemArrayAdapter;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ListActivity {

    public static final int ADD_ITEM = 1;
    public static final String VALUES = "values";
    private Button addNewButton;
    private TrackableItemArrayAdapter arrayAdapter;
    private TextView totalHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null) {
            ArrayList<TrackableItem> items = savedInstanceState.getParcelableArrayList(VALUES);
            arrayAdapter = new TrackableItemArrayAdapter(this, R.layout.rowlayout, items);
        } else {
            TrackableItem firstItem = new TrackableItem("Roger", 1, 6, "We went to the park and back");
            TrackableItem secondItem = new TrackableItem("Rager", 1, 3, "We went to the zoo");
            TrackableItem thirdItem = new TrackableItem("Ruger", 2, 8, "We went to the moon and back");

            TrackableItem[] values = new TrackableItem[] { firstItem, secondItem, thirdItem };

            ArrayList<TrackableItem> valuesList = new ArrayList<>(Arrays.asList(values));

            arrayAdapter = new TrackableItemArrayAdapter(this,
                    R.layout.rowlayout, valuesList);
        }
        setListAdapter(arrayAdapter);

        totalHours = (TextView) findViewById(R.id.total_hours_txt);
        updateTotalHours();

        addNewButton = (Button) findViewById(R.id.addNewItemBtn);
        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddItemActivity.class);
                startActivityForResult(i, ADD_ITEM);
            }
        });
    }

    private void updateTotalHours() {
        Resources res = getResources();
        double level1Hours = 0;
        double level2Hours = 0;
        double level3Hours = 0;
        for(int i=0 ; i < arrayAdapter.getCount() ; i++){
            TrackableItem obj = arrayAdapter.getItem(i);
            switch (obj.getLevel()) {
                case 1:
                    level1Hours += obj.getHours();
                    break;
                case 2:
                    level2Hours += obj.getHours();
                    break;
                case 3:
                    level3Hours += obj.getHours();
                    break;
                default:
                    break;
            }
        }
        String text = String.format(res.getString(R.string.hours_summary), level1Hours, level2Hours, level3Hours);
        totalHours.setText(text);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == ADD_ITEM) {
            TrackableItem newItem = new TrackableItem(
                    data.getStringExtra(AddItemActivity.NAME),
                    data.getIntExtra(AddItemActivity.LEVEL, 1),
                    data.getDoubleExtra(AddItemActivity.HOURS, 0),
                    data.getStringExtra(AddItemActivity.DESCRIPTION));
            if (data.getStringExtra(AddItemActivity.IMAGE_BMP_FILENAME) != null) {
                String filename = data.getStringExtra(AddItemActivity.IMAGE_BMP_FILENAME);
                Bitmap bmp;
                try {
                    FileInputStream is = this.openFileInput(filename);
                    bmp = BitmapFactory.decodeStream(is);
                    newItem.setBitmap(bmp);
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            arrayAdapter.add(newItem);
            arrayAdapter.notifyDataSetChanged();
            updateTotalHours();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        TrackableItem item = (TrackableItem) getListAdapter().getItem(position);
        String toastText = item.getDescription();
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<TrackableItem> values = new ArrayList<>();
        for (int i = 0; i < arrayAdapter.getCount(); i++) {
            values.add(arrayAdapter.getItem(i));
        }
        outState.putParcelableArrayList(VALUES, values);
    }
}
