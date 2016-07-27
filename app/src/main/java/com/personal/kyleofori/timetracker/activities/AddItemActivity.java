package com.personal.kyleofori.timetracker.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.personal.kyleofori.timetracker.R;

public class AddItemActivity extends Activity implements View.OnClickListener {

    public static final String NAME = "Name";
    public static final String LEVEL = "Level";
    public static final String HOURS = "Hours";
    public static final String DESCRIPTION = "Description";

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
        addToListBtn.setOnClickListener(this);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        cancelBtn.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.levels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_btn:
                setResult(Activity.RESULT_CANCELED, new Intent());
                finish();
                break;
            case R.id.add_to_list_btn:
                if (allFieldsFilled()) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra(NAME, nameEdt.getText().toString());
                    returnIntent.putExtra(LEVEL, Integer.parseInt(levelSpinner.getSelectedItem().toString()));
                    returnIntent.putExtra(HOURS, Integer.parseInt(hoursEdt.getText().toString()));
                    returnIntent.putExtra(DESCRIPTION, descriptionEdt.getText().toString());
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    Toast.makeText(this, "Not all of the data has been filled in.", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private boolean allFieldsFilled() {
        return !TextUtils.isEmpty(getTextAsStringAndTrim(nameEdt)) &&
                !TextUtils.isEmpty(getTextAsStringAndTrim(hoursEdt)) &&
                !TextUtils.isEmpty(getTextAsStringAndTrim(descriptionEdt));
    }

    private String getTextAsStringAndTrim(EditText edt) {
        return edt.getText().toString().trim();
    }
}
