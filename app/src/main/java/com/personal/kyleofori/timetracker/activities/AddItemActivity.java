package com.personal.kyleofori.timetracker.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.personal.kyleofori.timetracker.R;

public class AddItemActivity extends Activity {
    private EditText nameEdt, levelEdt, hoursEdt, descriptionEdt;
    private ImageButton takePictureBtn;
    private Button addToListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        nameEdt = (EditText) findViewById(R.id.name_edt);
        levelEdt = (EditText) findViewById(R.id.level_edt);
        hoursEdt = (EditText) findViewById(R.id.hours_edt);
        descriptionEdt = (EditText) findViewById(R.id.description_edt);

        takePictureBtn = (ImageButton) findViewById(R.id.take_picture_btn);
        addToListBtn = (Button) findViewById(R.id.add_to_list_btn);
    }
}
