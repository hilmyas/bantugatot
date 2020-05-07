package com.assodikyhilmy.bantugatot.activities

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.assodikyhilmy.bantugatot.R

class MainActivity : AppCompatActivity(), View.OnClickListener {
    //image button
    private var buttonPlay: ImageButton? = null

    //high score button
    private var buttonScore: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setting the orientation to landscape
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        //getting the button
        buttonPlay = findViewById<View>(R.id.buttonPlay) as ImageButton
        //initializing the highscore button
        buttonScore = findViewById<View>(R.id.buttonScore) as ImageButton

        //adding a click listener
        buttonPlay!!.setOnClickListener(this)
        //setting the on click listener to high score button
        buttonScore!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v === buttonPlay) {
            //the transition from MainActivity to GameActivity
            startActivity(Intent(this@MainActivity, GameActivity::class.java))
        } else if (v === buttonScore) {
            //the transition from MainActivity to HighScore activity
            startActivity(Intent(this@MainActivity, TopScoreActivity::class.java))
        }
    }
}