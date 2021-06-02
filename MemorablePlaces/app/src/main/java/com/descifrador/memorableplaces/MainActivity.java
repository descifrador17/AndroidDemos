package com.descifrador.memorableplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> places = new ArrayList<>();
    static ArrayList<LatLng> loca = new ArrayList<>();

    ListView memorableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memorableList = findViewById(R.id.memorableList);

        if(places.isEmpty()){
            places.add("Add memorable place");
            loca.add(new LatLng(0.0,0.0));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,places);
        memorableList.setAdapter(arrayAdapter);

        memorableList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    intent.putExtra("position",position);
                    startActivity(intent);
                }
                else
                {
                    intent.putExtra("position",position);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, places.get(position)+" "+loca.get(position).toString(),Toast.LENGTH_SHORT).show();
                    //Toast.makeText(MainActivity.this, places.get(position), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
