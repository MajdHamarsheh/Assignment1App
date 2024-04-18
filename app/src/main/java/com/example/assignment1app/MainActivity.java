package com.example.assignment1app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listview);

        List<String> list = new ArrayList<>();
        list.add("Cube");
        list.add("Cuboid");
        list.add("Cylinder");
        list.add("Sphere");
        list.add("Hemisphere");
        list.add("Cone");
        list.add("Prism");
        list.add("Pyramid");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                Class<?> destinationActivity;
                switch (selectedItem) {
                    case "Cube":
                        destinationActivity = CubeAct.class;
                        break;
                    case "Cuboid":
                        destinationActivity = CuboidAct.class;
                        break;
                    case "Cylinder":
                        destinationActivity = CylinderAct.class;
                        break;
                    case "Sphere":
                        destinationActivity = SphereAct.class;
                        break;
                    case "Hemisphere":
                        destinationActivity = HemisphereAct.class;
                        break;
                    case "Cone":
                        destinationActivity = ConeAct.class;
                        break;
                    case "Prism":
                        destinationActivity = PrismAct.class;
                        break;
                    case "Pyramid":
                        destinationActivity = PyramidAct.class;
                        break;

                    default:
                        destinationActivity = MainActivity.class; // Default to current activity
                        break;
                }
                startActivity(new Intent(MainActivity.this, destinationActivity));
            }
        });
    }
}
