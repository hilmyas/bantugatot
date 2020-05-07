package com.assodikyhilmy.bantugatot.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.assodikyhilmy.bantugatot.R
import kotlinx.android.synthetic.main.activity_top_score.*

/**
 * Created by lenovo on 13/08/2017.
 */
class TopScoreActivity : AppCompatActivity() {
    val sharedPreferences: SharedPreferences by lazy { getSharedPreferences("TOP SCORES", Context.MODE_PRIVATE) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_score)

        //setting the values to the textViews
        textView!!.text = "Juara 1. " + sharedPreferences.getInt("score1", 0)
        textView2!!.text = "Juara 2. " + sharedPreferences.getInt("score2", 0)
        textView3!!.text = "Juara 3. " + sharedPreferences.getInt("score3", 0)
        textView4!!.text = "Juara 4. " + sharedPreferences.getInt("score4", 0)
    }
}