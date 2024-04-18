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

public class ConeAct extends AppCompatActivity {

     EditText editTextcone0;
     EditText editTextcone1;
    private  TextView resultTextView;
    private Spinner spinner0;
    private  Spinner spinner1;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cone);

        editTextcone0 = findViewById(R.id.editTextcone0);
        editTextcone1= findViewById(R.id.editTextcone1);
        Button calculateButton = findViewById(R.id.button);
        Button back=findViewById(R.id.backBtn);
        resultTextView = findViewById(R.id.hiddenTextView);
        spinner0=findViewById(R.id.spinner0);
        spinner1=findViewById(R.id.spinner1);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);


        editTextcone0.setText(sharedPreferences.getString("editTextcone0Value", ""));
        editTextcone1.setText(sharedPreferences.getString("editTextcone1Value", ""));

        ArrayList <String> spn0=new ArrayList<>();
        spn0.add("mm");
        spn0.add("cm");
        spn0.add("m");
        ArrayAdapter<String> arrayAdapter0 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spn0);
        arrayAdapter0.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner0.setAdapter(arrayAdapter0);

        ArrayList <String> spn1=new ArrayList<>();
        spn1.add("mm");
        spn1.add("cm");
        spn1.add("m");
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spn1);
        arrayAdapter1.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner1.setAdapter(arrayAdapter1);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str0 = editTextcone0.getText().toString();
                String str1 = editTextcone1.getText().toString();

                if (!str0.isEmpty() && !str1.isEmpty()) {
                    // Convert the string to double
                    double r = Double.parseDouble(str0);
                    double h = Double.parseDouble(str1);

                    // Calculate the volume of the cone
                    double volume = Math.pow(r, 2) * (3.14) * h * ((double) 1 /3);
                    String V = String.format("%.3f", volume);

                    String  unit0=spinner0.getSelectedItem().toString();
 String unit1=spinner1.getSelectedItem().toString();
 if(unit0.equals(unit1)){
     // Display the result in the TextView
     resultTextView.setText("Volume = " + V +" "+unit0+"³");
     resultTextView.setVisibility(View.VISIBLE);
 }
 else{
     resultTextView.setText("Please choose similar units");
     resultTextView.setVisibility(View.VISIBLE);
 }

                }
                else if (!str0.isEmpty() && str1.isEmpty()){

                    resultTextView.setText("Please enter the value of h");
                    resultTextView.setVisibility(View.VISIBLE);
                }
                else if (str0.isEmpty() && !str1.isEmpty()){

                    resultTextView.setText("Please enter the value of r");
                    resultTextView.setVisibility(View.VISIBLE);
                }
                else {
                    // If EditText is empty, show a message
                    resultTextView.setText("Please enter the values of r and h");
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
        editor.putString("editTextcone0Value", editTextcone0.getText().toString());
        editor.putString("editTextcone1Value", editTextcone1.getText().toString());
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveDataToSharedPreferences();
    }
}