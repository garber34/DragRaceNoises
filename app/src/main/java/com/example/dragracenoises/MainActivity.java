package com.example.dragracenoises;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener {

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

  Button bttnPlay;
  ImageView clipToSet;
  String index;

    int[] playListResIDs = new int[5];
    private MediaPlayer playlistPlayer=null;
    int currentTrack=0;


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


        bttnPlay=findViewById(R.id.playPlaylist);
       clipToSet = null;
       index= "";

    }

public void selectSlot(View view){
    if(clipToSet==null){
        clipToSet = (ImageView) view;
        clipToSet.setBackgroundColor(Color.parseColor("#FFE503"));
        clipToSet.setImageAlpha(128);
        index = clipToSet.getTag().toString();
        Toast.makeText(MainActivity.this, clipToSet.getTag().toString() + "", Toast.LENGTH_SHORT).show();
    } else if(index.equals(view.getTag().toString())){
        clipToSet.setBackgroundColor(Color.parseColor("#FFFFFF"));
        clipToSet.setImageAlpha(256);
        clipToSet=null;
        index = "";
    } else {
        clipToSet.setBackgroundColor(Color.parseColor("#FFFFFF"));
        clipToSet.setImageAlpha(256);
         clipToSet = (ImageView) view;
        clipToSet.setBackgroundColor(Color.parseColor("#FFE503"));
        clipToSet.setImageAlpha(128);
        index = clipToSet.getTag().toString();
        Toast.makeText(MainActivity.this, clipToSet.getTag().toString() + "", Toast.LENGTH_SHORT).show();
    }
}

   public void PlaySounds(View view) {
           currentTrack=0;
           playlistPlayer = MediaPlayer.create(MainActivity.this, playListResIDs[currentTrack]);
           playlistPlayer.setOnCompletionListener(this);
           playlistPlayer.start();
   }

    public void onCompletion(MediaPlayer arg0) {
        arg0.release();
        if (currentTrack < playListResIDs.length-1) {
            currentTrack++;
            arg0 = MediaPlayer.create(getApplicationContext(), playListResIDs[currentTrack]);
            arg0.setOnCompletionListener(this);
            arg0.start();
        }
    }

    @Override
    public void onClick(View view) {
                switch(index) {
           case "":
               final ImageView imageView = (ImageView) view;
               imageView.setImageAlpha(128);
           final MediaPlayer mp;
           int soundresid = getResources().getIdentifier((String) view.getTag(), "raw", getPackageName());
           mp = MediaPlayer.create(MainActivity.this, soundresid);
           mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
               @Override
               public void onCompletion(MediaPlayer mediaPlayer) {
                   imageView.setImageAlpha(255);
                   mp.stop();
                   mp.release();
               }
           });
           mp.start();
           break;
           case "playList1":
             setPlayListSlot(view, 0);
               break;
           case "playList2":
               setPlayListSlot(view, 1);
               break;
           case "playList3":
               setPlayListSlot(view, 2);
               break;
           case "playList4":
               setPlayListSlot(view, 3);
               break;
           case "playList5":
               setPlayListSlot(view, 4);
               break;
       }
       }

       public void setPlayListSlot(View view, int slot){
           final ImageView imageView = (ImageView) view;
        //set visual of playlist slot
           clipToSet.setImageAlpha(255);
           clipToSet.setBackgroundColor(Color.parseColor("#008000"));
           clipToSet.setImageDrawable(imageView.getDrawable());

           //set datasource for appropriate media player
           playListResIDs[slot]=getResources().getIdentifier((String) view.getTag(), "raw", getPackageName());
           Toast.makeText(MainActivity.this, "Sound set", Toast.LENGTH_SHORT).show();
           //deselect playlist slot
           clipToSet=null;
           index="";
       }
    }

