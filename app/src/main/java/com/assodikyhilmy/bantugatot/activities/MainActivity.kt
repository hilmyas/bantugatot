package com.assodikyhilmy.bantugatot.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.assodikyhilmy.bantugatot.R
import com.assodikyhilmy.bantugatot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //adding a click listener
        binding.buttonPlay.setOnClickListener(this)
        //setting the on click listener to high score button
        binding.buttonScore.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v === binding.buttonPlay) {
            //the transition from MainActivity to GameActivity
            startActivity(Intent(this@MainActivity, GameActivity::class.java))
        } else if (v === binding.buttonScore) {
            //the transition from MainActivity to HighScore activity
            startActivity(Intent(this@MainActivity, TopScoreActivity::class.java))
        }
    }
}