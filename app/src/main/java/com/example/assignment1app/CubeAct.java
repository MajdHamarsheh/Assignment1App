package com.example.assignment1app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CubeAct extends AppCompatActivity {

    private EditText editText;
    private TextView resultTextView;
    private Spinner spinner0;
    private SharedPreferences sharedPreferences;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cube);

        editText = findViewById(R.id.editText);
        Button calculateButton = findViewById(R.id.button);
        Button back=findViewById(R.id.backBtn);
        resultTextView = findViewById(R.id.hiddenTextView);
spinner0=findViewById(R.id.spinner0);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);


        editText.setText(sharedPreferences.getString("editTextValue", ""));

        ArrayList<String> spn0=new ArrayList<>();
        spn0.add("mm");
        spn0.add("cm");
        spn0.add("m");
        ArrayAdapter<String> arrayAdapter0 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spn0);
        arrayAdapter0.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner0.setAdapter(arrayAdapter0);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sideLengthStr = editText.getText().toString();

                if (!sideLengthStr.isEmpty()) {

                    double sideLength = Double.parseDouble(sideLengthStr);


                    double volume = Math.pow(sideLength, 3);
                    String V = String.format("%.3f", volume);
                    String  unit0=spinner0.getSelectedItem().toString();

                    resultTextView.setText("Volume = " + V+" "+unit0+"³");
                    resultTextView.setVisibility(View.VISIBLE);
                } else {
                    // If EditText is empty, show a message
                    resultTextView.setText("Please enter a value");
                    resultTextView.setVisibility(View.VISIBLE);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToSharedPreferences();
                finish();
            }
        });
    }
    private void saveDataToSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("editTextValue", editText.getText().toString());
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveDataToSharedPreferences();
    }
}
