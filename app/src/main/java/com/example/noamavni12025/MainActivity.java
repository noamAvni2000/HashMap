package com.example.noamavni12025;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    HashMap<String, String> dictionary = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etKey, etValue;
        Button btnAdd, btnClear, btnRemove;
        LinearLayout llButtons;


        etKey = findViewById(R.id.etKey);
        etValue = findViewById(R.id.etValue);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        btnRemove = findViewById(R.id.btnRemove);
        llButtons = findViewById(R.id.llButtons);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = etKey.getText().toString();
                String value = etValue.getText().toString();
                dictionary.put(key, value);
                addButtonToLayout(key);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llButtons.removeAllViews();
                addButtonsForDictionary(dictionary);
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyToRemove = etKey.getText().toString();
                if (dictionary.containsKey(keyToRemove)) {
                    dictionary.remove(keyToRemove);
                    llButtons.removeAllViews();
                    addButtonsForDictionary(dictionary);
                    Toast.makeText(MainActivity.this, "Removed: " + keyToRemove, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Key not found!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addButtonsForDictionary(HashMap<String, String> dictionary) {
        ArrayList<String> keys = new ArrayList<>(dictionary.keySet());
        keys.sort(String::compareTo);
        for (String key : keys) {
            addButtonToLayout(key);
        }
    }

    private void addButtonToLayout(String key) {
        Button btnNew = new Button(MainActivity.this);
        btnNew.setText(key);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, dictionary.get(btnNew.getText().toString()), Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayout llButtons = findViewById(R.id.llButtons);
        llButtons.addView(btnNew);
    }
}
