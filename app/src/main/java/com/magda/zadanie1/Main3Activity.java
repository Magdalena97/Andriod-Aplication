package com.magda.zadanie1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kontakt);
        Intent received_intent = getIntent();

        int contact_id = received_intent.getIntExtra("contact_id",0);

        final Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.people,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button okbutton = findViewById(R.id.button5);
        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = spinner.getSelectedItem().toString();//tu jest imie i nazwisko
                Intent resultIntent = new Intent();
                resultIntent.putExtra("name",name);//wysywanie imienia do main activity
                setResult(Activity.RESULT_OK,resultIntent);
                finish();
            }
        });

        Button cancelbutton = findViewById(R.id.button6);
        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });

    }
}

