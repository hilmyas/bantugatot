package com.assodikyhilmy.bantugatot.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
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

        //setting the values to the textViews
        binding.textView.text = "Juara 1. " + sharedPreferences.getInt("score1", 0)
        binding.textView2.text = "Juara 2. " + sharedPreferences.getInt("score2", 0)
        binding.textView3.text = "Juara 3. " + sharedPreferences.getInt("score3", 0)
        binding.textView4.text = "Juara 4. " + sharedPreferences.getInt("score4", 0)
    }
}