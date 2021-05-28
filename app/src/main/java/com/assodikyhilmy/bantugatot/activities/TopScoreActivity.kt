package com.assodikyhilmy.bantugatot.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.assodikyhilmy.bantugatot.databinding.ActivityTopScoreBinding

/**
 * Created by lenovo on 13/08/2017.
 */
class TopScoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTopScoreBinding
    val sharedPreferences: SharedPreferences by lazy { getSharedPreferences("TOP SCORES", Context.MODE_PRIVATE) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopScoreBinding.inflate(layoutInflater)
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

        //setting the values to the textViews
        binding.textView.text = "Juara 1. " + sharedPreferences.getInt("score1", 0) + " "
        binding.textView2.text = "Juara 2. " + sharedPreferences.getInt("score2", 0) + " "
        binding.textView3.text = "Juara 3. " + sharedPreferences.getInt("score3", 0) + " "
        binding.textView4.text = "Juara 4. " + sharedPreferences.getInt("score4", 0) + " "
    }
}