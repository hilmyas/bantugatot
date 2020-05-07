package com.assodikyhilmy.bantugatot.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.assodikyhilmy.bantugatot.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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