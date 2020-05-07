package com.assodikyhilmy.myfirstandroidgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //image button
    private ImageButton buttonPlay;
    //high score button
    private ImageButton buttonScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting the orientation to landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //getting the button
        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);
        //initializing the highscore button
        buttonScore = (ImageButton) findViewById(R.id.buttonScore);

        //adding a click listener
        buttonPlay.setOnClickListener(this);
        //setting the on click listener to high score button
        buttonScore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == buttonPlay) {
            //the transition from MainActivity to GameActivity
            startActivity(new Intent(MainActivity.this, GameActivity.class));
        }
        else if (v == buttonScore) {
            //the transition from MainActivity to HighScore activity
            startActivity(new Intent(MainActivity.this, TopScoreActivity.class));
        }
    }
}