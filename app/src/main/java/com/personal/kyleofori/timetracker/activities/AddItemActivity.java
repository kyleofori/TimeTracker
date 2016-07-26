package com.personal.kyleofori.timetracker.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.personal.kyleofori.timetracker.R;

public class AddItemActivity extends Activity {
    private EditText nameEdt, hoursEdt, descriptionEdt;
    private Spinner levelSpinner;
    private ImageButton takePictureBtn;
    private Button addToListBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        nameEdt = (EditText) findViewById(R.id.name_edt);
        hoursEdt = (EditText) findViewById(R.id.hours_edt);
        descriptionEdt = (EditText) findViewById(R.id.description_edt);

        levelSpinner = (Spinner) findViewById(R.id.level_spinner);

        takePictureBtn = (ImageButton) findViewById(R.id.take_picture_btn);
        addToListBtn = (Button) findViewById(R.id.add_to_list_btn);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.levels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(adapter);
    }
}
