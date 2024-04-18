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

public class CuboidAct extends AppCompatActivity {

     EditText editTextcub0;
      EditText editTextcub1;

      EditText editTextcub2;
    private  TextView resultTextView;
    private Spinner spinner0;
    private Spinner spinner1;
    private Spinner spinner2;
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuboid);

        editTextcub0 = findViewById(R.id.editTextcub0);
        editTextcub1= findViewById(R.id.editTextcub1);
        editTextcub2= findViewById(R.id.editTextcub2);
        Button calculateButton = findViewById(R.id.button);
        Button back=findViewById(R.id.backBtn);
        resultTextView = findViewById(R.id.hiddenTextView);
        spinner0=findViewById(R.id.spinner0);
        spinner1=findViewById(R.id.spinner1);
        spinner2=findViewById(R.id.spinner2);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);


        editTextcub0.setText(sharedPreferences.getString("editTextcub0Value", ""));
        editTextcub1.setText(sharedPreferences.getString("editTextcub1Value", ""));
        editTextcub2.setText(sharedPreferences.getString("editTextcub2Value", ""));

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

        ArrayList<String> spn2=new ArrayList<>();
        spn2.add("mm");
        spn2.add("cm");
        spn2.add("m");
        ArrayAdapter<String> arrayAdapter2= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spn2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner2.setAdapter(arrayAdapter2);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str0 = editTextcub0.getText().toString();
                String str1 = editTextcub1.getText().toString();
                String str2 = editTextcub2.getText().toString();

                if (!str0.isEmpty() && !str1.isEmpty() && !str2.isEmpty()) {

                    double b = Double.parseDouble(str0);
                    double l = Double.parseDouble(str1);
                    double h = Double.parseDouble(str2);


                    double volume = b * l * h;
                    String V = String.format("%.3f", volume);
                    String  unit0=spinner0.getSelectedItem().toString();
                    String  unit1=spinner1.getSelectedItem().toString();
                    String  unit2=spinner2.getSelectedItem().toString();
                    if(unit0.equals(unit1) && unit0.equals(unit2)){

                        resultTextView.setText("Volume = " + V+" "+unit0+"³");
                        resultTextView.setVisibility(View.VISIBLE);
                    }
                    else {
                        resultTextView.setText("Please choose similar units");
                        resultTextView.setVisibility(View.VISIBLE);
                    }

                }
                else if (str0.isEmpty() && !str1.isEmpty() && !str2.isEmpty() ){

                    resultTextView.setText("Please enter the value of b");
                    resultTextView.setVisibility(View.VISIBLE);
                }
                else if (str1.isEmpty() && !str0.isEmpty() && !str2.isEmpty() ){

                    resultTextView.setText("Please enter the value of l");
                    resultTextView.setVisibility(View.VISIBLE);
                }
                else if (str2.isEmpty() && !str0.isEmpty() && !str1.isEmpty()){

                    resultTextView.setText("Please enter the value of h");
                    resultTextView.setVisibility(View.VISIBLE);
                }
                else if (str0.isEmpty() && str1.isEmpty() && !str2.isEmpty()){

                    resultTextView.setText("Please enter the value of b and l");
                    resultTextView.setVisibility(View.VISIBLE);
                }
                else if (str0.isEmpty() && str2.isEmpty() && !str1.isEmpty()){

                    resultTextView.setText("Please enter the value of b and h");
                    resultTextView.setVisibility(View.VISIBLE);
                }
                else if (str1.isEmpty() && str2.isEmpty() && !str0.isEmpty()){

                    resultTextView.setText("Please enter the value of l and h");
                    resultTextView.setVisibility(View.VISIBLE);
                }

                else {

                    resultTextView.setText("Please enter the values of b , l and h");
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
        editor.putString("editTextcub0Value", editTextcub0.getText().toString());
        editor.putString("editTextcub1Value", editTextcub1.getText().toString());
        editor.putString("editTextcub2Value", editTextcub2.getText().toString());
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveDataToSharedPreferences();
    }
}
