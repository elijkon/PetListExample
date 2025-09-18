package com.example.listviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import module.Pet;

public class MainActivity extends AppCompatActivity {
    ListView petListWidget;
    ArrayList<Pet> pets;

    AdapterView.OnItemClickListener selectedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Pet selected = (Pet) adapterView.getItemAtPosition(i);
            Toast.makeText(getApplicationContext(), "hi hi" + selected.getName(), Toast.LENGTH_LONG).show();
        }


    };

    AdapterView.OnItemClickListener selection3Listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            HashMap<String, String> selected = (HashMap<String, String>) adapterView.getItemAtPosition(i);
            Toast.makeText(getApplicationContext(), "hi hi " + selected.get("name"), Toast.LENGTH_LONG).show();
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pets = new ArrayList<>();
        pets.add(new Pet("Kenny", "bulldog", 5));
        pets.add(new Pet("elijah", "corgi", 8));
        pets.add(new Pet("John", "bulldog", 12));
        pets.add(new Pet("Jack", "corgi", 10));

        petListWidget = findViewById(R.id.listViewWidget);
        int selection = 3;
        populateListView(selection);

    }

    private void populateListView(int selection) {
        switch (selection) {
            case 1:
                useStringResource();
                break;
            case 2:
                usingDataArray();
                petListWidget.setOnItemClickListener(selectedListener);
                break;
            case 3:
                usingTwoLines();
                petListWidget.setOnItemClickListener(selection3Listener);
                break;
        }
    }

    /**
     * This method populates the list view using a string resource containing
     * the content of a strings array resource
     */
    private void useStringResource() {
        String [] contents = getResources().getStringArray(R.array.breeds);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,contents);
        petListWidget.setAdapter(adapter);

    }

    /**
     * This method populates the list view using a string resource containing the list of a data array
     */
    private void usingDataArray() {
        ArrayAdapter<Pet> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pets);
        petListWidget.setAdapter(adapter);


    }

    private void usingTwoLines() {
        List<HashMap<String, String>> data = new ArrayList<>();

        for(Pet p: pets) {
            HashMap<String, String> current = new HashMap<>();
            current.put("name", p.getName());
            current.put("breed", p.getBreed());
            data.add(current);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                android.R.layout.simple_list_item_2,
                new String[]{"name","breed"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        petListWidget.setAdapter(adapter);

    }
}