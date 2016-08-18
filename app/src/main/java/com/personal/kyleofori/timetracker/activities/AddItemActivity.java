package com.personal.kyleofori.timetracker.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.personal.kyleofori.timetracker.DatePickerFragment;
import com.personal.kyleofori.timetracker.R;

import java.io.FileOutputStream;

public class AddItemActivity extends Activity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    public static final String NAME = "Name";
    public static final String LEVEL = "Level";
    public static final String HOURS = "Hours";
    public static final String DESCRIPTION = "Description";
    public static final String IMAGE_BMP_FILENAME = "Image Bitmap Filename";
    public static final String DATE_PICKER = "DatePicker";
    public static final int REQUEST_IMAGE_CAPTURE = 1;

    private TextView dateTxt;
    private EditText nameEdt, hoursEdt, descriptionEdt;
    private Button dateBtn;
    private Spinner levelSpinner;
    private ImageButton takePictureBtn;
    private Button addToListBtn, cancelBtn;
    private ImageView imageView;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        dateTxt = (TextView) findViewById(R.id.date_txt);
        nameEdt = (EditText) findViewById(R.id.name_edt);
        hoursEdt = (EditText) findViewById(R.id.hours_edt);
        descriptionEdt = (EditText) findViewById(R.id.description_edt);
        levelSpinner = (Spinner) findViewById(R.id.level_spinner);
        imageView = (ImageView) findViewById(R.id.image_view);

        dateBtn = (Button) findViewById(R.id.date_btn);
        dateBtn.setOnClickListener(this);
        takePictureBtn = (ImageButton) findViewById(R.id.take_picture_btn);
        takePictureBtn.setOnClickListener(this);
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
            case R.id.date_btn:
                showDatePickerDialog();
                break;
            case R.id.take_picture_btn:
                dispatchTakePictureIntent();
                break;
            case R.id.cancel_btn:
                setResult(Activity.RESULT_CANCELED, new Intent());
                finish();
                break;
            case R.id.add_to_list_btn:
                if (allFieldsFilled()) {
                    Intent returnIntent = assembleIntentData();
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

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setOnDateSetListener(this);
        newFragment.show(getFragmentManager(), DATE_PICKER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

    private Intent assembleIntentData() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(NAME, nameEdt.getText().toString());
        returnIntent.putExtra(LEVEL, Integer.parseInt(levelSpinner.getSelectedItem().toString()));
        returnIntent.putExtra(HOURS, Double.parseDouble(hoursEdt.getText().toString()));
        returnIntent.putExtra(DESCRIPTION, descriptionEdt.getText().toString());
        if (imageBitmap != null) {
            try {
                String filename = "bitmap.png";
                FileOutputStream stream = this.openFileOutput(filename, Context.MODE_PRIVATE);
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                stream.close();
                imageBitmap.recycle();

                returnIntent.putExtra(IMAGE_BMP_FILENAME, filename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnIntent;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
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

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String yearString = String.valueOf(year);
        String monthString = String.valueOf(month + 1);
        String dayString = String.valueOf(day);
        String dateString = monthString + "/" + dayString + "/" + yearString;
        dateTxt.setText(dateString);
        dateTxt.setVisibility(View.VISIBLE);
        dateBtn.setText(getString(R.string.reselect_date));
    }
}
