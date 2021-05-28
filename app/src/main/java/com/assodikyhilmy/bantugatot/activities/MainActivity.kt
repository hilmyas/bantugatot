package com.assodikyhilmy.bantugatot.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.assodikyhilmy.bantugatot.R
import com.assodikyhilmy.bantugatot.databinding.ActivityMainBinding
import com.assodikyhilmy.bantugatot.helpers.MultiMediaUtils

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)


        //adding a click listener
        binding.buttonPlay.setOnClickListener(this)
        //setting the on click listener to high score button
        binding.buttonScore.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        MultiMediaUtils.playGetScore(this)
        if (v === binding.buttonPlay) {
            //the transition from MainActivity to GameActivity
            startActivity(Intent(this@MainActivity, GameActivity::class.java))
        } else if (v === binding.buttonScore) {
            //the transition from MainActivity to HighScore activity
            startActivity(Intent(this@MainActivity, TopScoreActivity::class.java))
        }
    }
}