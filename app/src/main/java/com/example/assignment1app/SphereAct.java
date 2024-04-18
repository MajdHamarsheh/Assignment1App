package com.example.assignment1app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class SphereAct extends AppCompatActivity {

    private EditText editText0;
    private TextView resultTextView;
    private Spinner spinner0;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sphere);

        editText0 = findViewById(R.id.editText0);
        resultTextView = findViewById(R.id.hiddenTextView);
        spinner0 = findViewById(R.id.spinner0);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        editText0.setText(sharedPreferences.getString("editText0Value", ""));

        ArrayList<String> spn0 = new ArrayList<>();
        spn0.add("mm");
        spn0.add("cm");
        spn0.add("m");
        ArrayAdapter<String> arrayAdapter0 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spn0);
        arrayAdapter0.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner0.setAdapter(arrayAdapter0);

        Button calculateButton = findViewById(R.id.button);
        Button backButton = findViewById(R.id.backBtn);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateVolume();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToSharedPreferences();
                finish();
            }
        });
    }
    private void saveDataToSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("editText0Value", editText0.getText().toString());
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveDataToSharedPreferences();
    }

    private void calculateVolume() {
        String str0 = editText0.getText().toString();

        if (!str0.isEmpty()) {
            double r = Double.parseDouble(str0);
            double volume = Math.pow(r, 3) * ((double) 4 /3) * (3.14);
            String V = String.format("%.3f", volume);
            String unit0 = spinner0.getSelectedItem().toString();
            resultTextView.setText("Volume = " + V + " " + unit0 + "³");
            resultTextView.setVisibility(View.VISIBLE);
        } else {
            resultTextView.setText("Please enter the value of r");
            resultTextView.setVisibility(View.VISIBLE);
        }
    }


}
