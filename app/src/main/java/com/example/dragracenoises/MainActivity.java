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

  //instantiate playlist UI and track elements
  Button bttnPlay;
  ImageView clipToSet;
  String index;
    int[] playListResIDs = {0,0,0,0,0};
    private MediaPlayer playlistPlayer=null;
    int currentTrack=0;
    ImageView currentPlaying;


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

    //allows user to select playlist track and assign a clip
public void selectSlot(View view){
        // checks whether the user is in selection mode, if not then fade the tapped ImageView and initialize that slot to be assigned
        if(clipToSet==null){
        clipToSet = (ImageView) view;
        clipToSet.setBackgroundColor(Color.parseColor("#FFE503"));
        clipToSet.setImageAlpha(128);
        index = clipToSet.getTag().toString();
        //Toast.makeText(MainActivity.this, clipToSet.getTag().toString() + "", Toast.LENGTH_SHORT).show();
    }
        //if the user clicks the slot they are already trying to assign, exit selection mode and return to playing clips one-off
        else if(index.equals(view.getTag().toString())){
        clipToSet.setBackgroundColor(Color.parseColor("#FFFFFF"));
        clipToSet.setImageAlpha(256);
        clipToSet=null;
        index = "";
    }
        //if the user is topped on a different slot to assign, reset UI on original ImageView and switch to new ImageView
        else {
        clipToSet.setBackgroundColor(Color.parseColor("#FFFFFF"));
        clipToSet.setImageAlpha(256);
         clipToSet = (ImageView) view;
        clipToSet.setBackgroundColor(Color.parseColor("#FFE503"));
        clipToSet.setImageAlpha(128);
        index = clipToSet.getTag().toString();
       // Toast.makeText(MainActivity.this, clipToSet.getTag().toString() + "", Toast.LENGTH_SHORT).show();
    }
}
//play the playlist sequentially
   public void PlaySounds(View view) {
        //check that all 5 playlist slots are initialized, if not display error message
       if( playListResIDs[0]==0 || playListResIDs[1]==0 ||playListResIDs[2]==0 ||playListResIDs[3]==0 ||playListResIDs[4]==0){
           Toast.makeText(this, "Playlist not complete", Toast.LENGTH_SHORT).show();
       }
       //start playing sounds at first clip, recursively move to next clip on completion
       else {
           currentTrack = 0;
           playlistPlayer = MediaPlayer.create(MainActivity.this, playListResIDs[currentTrack]);
           playlistPlayer.setOnCompletionListener(this);
           playlistPlayer.start();

           //fade ImageView of the first clip when playlist starts
           currentPlaying = findViewById(R.id.playList1);
           currentPlaying.setImageAlpha(128);
       }
   }

   //recursive function. when track completes, reset ImageView alpha, move to next track, fade that alpha, play new track
    public void onCompletion(MediaPlayer arg0) {
        arg0.release();
        currentPlaying.setImageAlpha(255);
        if (currentTrack < playListResIDs.length-1) {
            currentTrack++;
            switch (currentTrack){
                case 1:
                    currentPlaying=findViewById(R.id.playList2);
                    currentPlaying.setImageAlpha(128);
                    break;
                case 2:
                    currentPlaying=findViewById(R.id.playList3);
                    currentPlaying.setImageAlpha(128);
                    break;
                case 3:
                    currentPlaying=findViewById(R.id.playList4);
                    currentPlaying.setImageAlpha(128);
                    break;
                case 4:
                    currentPlaying=findViewById(R.id.playList5);
                    currentPlaying.setImageAlpha(128);
                    break;
            }
            arg0 = MediaPlayer.create(getApplicationContext(), playListResIDs[currentTrack]);
            arg0.setOnCompletionListener(this);
            arg0.start();
        }
    }

    //if in selection mode, assign clicked quote to selected slot, otherwise play it one time, fade image while playing
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

       //handles the changing the  UI of the passed slot and overwriting the resource ID using the passed quote
       public void setPlayListSlot(View view, int slot){
           final ImageView imageView = (ImageView) view;
        //reset visual of playlist slot
           clipToSet.setImageAlpha(255);
           clipToSet.setBackgroundColor(Color.parseColor("#008000"));
           clipToSet.setImageDrawable(imageView.getDrawable());

           //set datasource for appropriate media player
           playListResIDs[slot]=getResources().getIdentifier((String) view.getTag(), "raw", getPackageName());

           /*
           //deselect playlist slot
           clipToSet=null;
           index="";
            */

           //automatically select next slot, if at end of playlist exit selection mode
           switch (index){
               case "playList1":
                   clipToSet=findViewById(R.id.playList2);
                   selectSlot((clipToSet));
                   break;
                   case "playList2":
                   clipToSet=findViewById(R.id.playList3);
                   selectSlot((clipToSet));
                   break;
               case "playList3":
                   clipToSet=findViewById(R.id.playList4);
                   selectSlot((clipToSet));
                   break;
               case "playList4":
                   clipToSet=findViewById(R.id.playList5);
      selectSlot((clipToSet));
                   break;
               case "playList5":
                   clipToSet=null;
                   index="";
                   break;


           }
       }
    }

