package com.example.dragracenoises;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //instantiate views
  ImageView satan;
  ImageView showGirl;
  ImageView successfully;
  ImageView attacked;
  ImageView bestFriend;
  ImageView choices;
  ImageView dontDoThat;
  ImageView makeUp;
  ImageView tone;
  ImageView shade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize imageviews and set listeners
        satan=findViewById(R.id.satan);
        showGirl=findViewById(R.id.showgirl);
        successfully=findViewById(R.id.successfully);
        attacked=findViewById(R.id.attacked);
        bestFriend=findViewById(R.id.bestfriend);
        choices=findViewById(R.id.choices);
        dontDoThat=findViewById(R.id.dontdothat);
        makeUp=findViewById(R.id.makeup);
        tone=findViewById(R.id.tone);
        shade=findViewById(R.id.shade);
        satan.setOnClickListener(MainActivity.this);
        showGirl.setOnClickListener(MainActivity.this);
        successfully.setOnClickListener(MainActivity.this);
        attacked.setOnClickListener(MainActivity.this);
        bestFriend.setOnClickListener(this);
        choices.setOnClickListener(this);
        dontDoThat.setOnClickListener(this);
        makeUp.setOnClickListener(this);
        tone.setOnClickListener(this);
        shade.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        //uses tag of the source imageview to identify resource id of the associated sound clip
        MediaPlayer mp;
        int soundresid= getResources().getIdentifier((String)v.getTag(),"raw", getPackageName());

        //plays sound clip using the resource id assigned above
        mp = MediaPlayer.create(MainActivity.this, soundresid);
       mp.start();

    }
}
