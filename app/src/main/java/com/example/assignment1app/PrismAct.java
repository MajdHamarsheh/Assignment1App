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

public class PrismAct extends AppCompatActivity {

     EditText editTextpr0;
     EditText editTextpr1;
    private TextView resultTextView;
    private Spinner spinner0;
    private   Spinner spinner1;
    private SharedPreferences sharedPreferences;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prism);

        editTextpr0 = findViewById(R.id.editTextpr0);
        editTextpr1= findViewById(R.id.editTextpr1);
        Button calculateButton = findViewById(R.id.button);
        Button back=findViewById(R.id.backBtn);
        resultTextView = findViewById(R.id.hiddenTextView);
spinner0=findViewById(R.id.spinner0);
        spinner1=findViewById(R.id.spinner1);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);


        editTextpr0.setText(sharedPreferences.getString("editTextpr0Value", ""));
        editTextpr1.setText(sharedPreferences.getString("editTextpr1Value", ""));

        ArrayList<String> spn0=new ArrayList<>();
        spn0.add("mm");
        spn0.add("cm");
        spn0.add("m");
        ArrayAdapter<String> arrayAdapter0 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spn0);
        arrayAdapter0.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner0.setAdapter(arrayAdapter0);


        ArrayList<String> spn1=new ArrayList<>();
        spn1.add("mm");
        spn1.add("cm");
        spn1.add("m");
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spn1);
        arrayAdapter1.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner1.setAdapter(arrayAdapter1);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str0 = editTextpr0.getText().toString();
                String str1 = editTextpr1.getText().toString();

                if (!str0.isEmpty() && !str1.isEmpty()) {

                    double a = Double.parseDouble(str0);
                    double h = Double.parseDouble(str1);


                    double volume = a * h;
                    String V = String.format("%.3f", volume);
                    String  unit0=spinner0.getSelectedItem().toString();
                    String  unit1=spinner1.getSelectedItem().toString();
                    if(unit1.equals(unit0)){

                        resultTextView.setText("Volume = " + V+" "+unit1+"³");
                        resultTextView.setVisibility(View.VISIBLE);
                    }
                    else{
                        resultTextView.setText("Please choose similar units");
                        resultTextView.setVisibility(View.VISIBLE);
                    }
                }
                else if (!str0.isEmpty() && str1.isEmpty()){

                    resultTextView.setText("Please enter the value of A");
                    resultTextView.setVisibility(View.VISIBLE);
                }
                else if (str0.isEmpty() && !str1.isEmpty()){

                    resultTextView.setText("Please enter the value of h");
                    resultTextView.setVisibility(View.VISIBLE);
                }
                else {

                    resultTextView.setText("Please enter the values of A and h");
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
        editor.putString("editTextpr0Value", editTextpr0.getText().toString());
        editor.putString("editTextpr1Value", editTextpr1.getText().toString());
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveDataToSharedPreferences();
    }
}
