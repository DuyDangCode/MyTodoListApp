package com.example.myto_dolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lv;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        lv.setAdapter(itemsAdapter);
        setUpLVListener();

    }

    private void setUpLVListener(){
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    Context context = getApplicationContext();
                    Toast.makeText(context, "Item remove", Toast.LENGTH_SHORT).show();
                    items.remove(i);
                    itemsAdapter.notifyDataSetChanged();
                    return true;
                }
                catch (Exception exception){
                    return false;
                }
            }
        });
    }
    private void addItem(View view){
        EditText et = findViewById(R.id.ET);
        String itemInput = et.getText().toString();

        if(itemInput != "")
        {
            itemsAdapter.add(itemInput);
            et.setText("");
        }
        else{
            Toast.makeText(this, "Please enter text...", Toast.LENGTH_SHORT).show();
        }
    }
}