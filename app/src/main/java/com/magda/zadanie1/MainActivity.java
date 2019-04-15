package com.magda.zadanie1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int CONTACT_REQUEST = 1;
    public static final int SOUND_REQUEST = 2;

    private int contact_id = 0;
    private ArrayList contacts = new ArrayList<ArrayList<Integer>>();//lista z kontaktami
    public static final int BUTTON_REQUEST = 1;
    private String[] all_names;
    private MediaPlayer player;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        all_names = getResources().getStringArray(R.array.people);  //Tablica ze wszystkimi nazwiskami
        final TypedArray all_sounds = getResources().obtainTypedArray(R.array.sounds);  //Tablica z odnośnikami do id plików dźwiękowych
        TypedArray all_images = getResources().obtainTypedArray(R.array.avatars);  //Tablica z odnośnikami do id obrazków

        for(int i=0; i<all_names.length;i++){
            ArrayList<Integer> new_contact = new ArrayList<Integer>();

            int random_sound_number = new Random().nextInt(all_sounds.length());
            int random_image_number = new Random().nextInt(all_images.length());

            new_contact.add(i); //id użytkownika w tablicy all_names
            new_contact.add(all_images.getResourceId(random_image_number, R.raw.avatar_1)); //id obrazka (np. R.raw.avatar1)
            new_contact.add(all_sounds.getResourceId(random_sound_number, R.raw.mario));  //id dźwięku (np. R.raw.ring)

            contacts.add(new_contact);
        }

        TextView t =(TextView)findViewById(R.id.textView);
        t.setText(all_names[0]);

        ImageView iv = (ImageView)findViewById(R.id.imageView);
        iv.setImageResource(R.raw.avatar_1);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player!=null){
                    player.release();
                    player=null;

                }else{
                    //player=MediaPlayer.create(this,sound.index);
                    player.start();
                    System.out.println("Zaczynam odtwarzanie dzwieku");
                }
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        Button changesound = (Button) findViewById(R.id.button2);
        changesound.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                 Intent i = new Intent(MainActivity.this, Main2Activity.class);
                 Integer current_sound = ((ArrayList<Integer>) contacts.get(contact_id)).get(2); //aktualny id dźwięku
                 Integer sound_id = 0;
                 for (int j=0; j<=all_sounds.length(); j++) {
                     if (current_sound.intValue() == ((Integer) all_sounds.getResourceId(j,-1)).intValue()) {
                         sound_id = j;
                         break;
                     }
                 }
                 i.putExtra("sound_id", sound_id);//przeslanie dzwieku
                 startActivityForResult(i, SOUND_REQUEST);
             }
         });

         Button changecontact = (Button) findViewById(R.id.button);
         changecontact.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(MainActivity.this, Main3Activity.class);
                 i.putExtra("contact_id", contact_id);
                 startActivityForResult(i, CONTACT_REQUEST);

             }
         });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TypedArray all_sounds = getResources().obtainTypedArray(R.array.sounds);

        switch(requestCode) {
            case (CONTACT_REQUEST) : {
                if (resultCode == Activity.RESULT_OK) {
                    String nameValue = data.getStringExtra("name");
                    for (int i=0; i<all_names.length; i++) {
                        if (all_names[i].equals(nameValue)) {
                            contact_id = i;
                            break;
                        }
                    }
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(nameValue);
                    ImageView imageView = (ImageView) findViewById(R.id.imageView);
                    imageView.setImageResource(((ArrayList<Integer>) contacts.get(contact_id)).get(1));
                }
                break;
            }
            case (SOUND_REQUEST): {
                if (resultCode == Activity.RESULT_OK) {
                    int sound_index = data.getIntExtra("sound", 0);

                    ArrayList<Integer> updatedContact = new ArrayList<Integer>();
                    updatedContact.add(contact_id); //id użytkownika w tablicy all_names
                    updatedContact.add(((ArrayList<Integer>) contacts.get(contact_id)).get(1)); //id obrazka (np. R.raw.avatar1)
                    updatedContact.add(all_sounds.getResourceId(sound_index, R.raw.mario));  //id dźwięku (np. R.raw.ring)
                    contacts.set(contact_id, updatedContact);

                }
                break;
            }
        }
    }
}
