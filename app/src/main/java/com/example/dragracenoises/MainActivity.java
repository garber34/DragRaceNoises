package com.example.dragracenoises;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

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
        satan.setOnTouchListener(MainActivity.this);
        showGirl.setOnTouchListener(MainActivity.this);
        successfully.setOnTouchListener(MainActivity.this);
        attacked.setOnTouchListener(MainActivity.this);
        bestFriend.setOnTouchListener(this);
        choices.setOnTouchListener(this);
        dontDoThat.setOnTouchListener(this);
        makeUp.setOnTouchListener(this);
        tone.setOnTouchListener(this);
        shade.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ImageView imageView = (ImageView) view;
        switch (motionEvent.getAction()){
        case MotionEvent.ACTION_DOWN:
               imageView.setImageAlpha(128);
            return true;
            case MotionEvent.ACTION_UP:
                imageView.setImageAlpha(255);
                MediaPlayer mp;
                int soundresid= getResources().getIdentifier((String)view.getTag(),"raw", getPackageName());

                //plays sound clip using the resource id assigned above
                mp = MediaPlayer.create(MainActivity.this, soundresid);
                mp.start();
                return true;
    }
        return false;
    }
}
