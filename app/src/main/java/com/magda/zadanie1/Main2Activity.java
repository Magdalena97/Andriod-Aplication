package com.magda.zadanie1;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound);

        int sound_id = getIntent().getIntExtra("sound_id",0);

        final TypedArray all_sounds = getResources().obtainTypedArray(R.array.sounds);
        final RadioGroup radioGroup = findViewById(R.id.radioGroup);

        ((RadioButton) radioGroup.getChildAt(sound_id)).setChecked(true);

        Button okbutton = findViewById(R.id.button3);
        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(radioButtonID);
                int idx = radioGroup.indexOfChild(radioButton);  //numer wybranego buttona z radiogroup

               // System.out.println(idx);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("sound", idx);//wysywanie sound do main activity
                setResult(Activity.RESULT_OK,resultIntent);
                finish();
            }
        });

        Button cancelButton = findViewById(R.id.button4);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });

    }
}
