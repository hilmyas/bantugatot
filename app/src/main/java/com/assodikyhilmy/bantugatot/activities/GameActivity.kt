package com.assodikyhilmy.bantugatot.activities

import android.content.pm.ActivityInfo
import android.graphics.Point
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by lenovo on 12/08/2017.
 */
class GameActivity : AppCompatActivity() {
    //declaring gameview
    private var gameView: GameView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setting the orientation to landscape
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        //Getting display object
        val display = windowManager.defaultDisplay

        //Getting the screen resolution into point object
        val size = Point()
        display.getSize(size)

        //Initializing game view object
        //this time we are also passing the screen size to the GameView constructor
        gameView = GameView(this, size.x, size.y)
        gameView!!.keepScreenOn = true

        //adding it to contentview
        setContentView(gameView)
    }

    //pausing the game when activity is paused
    override fun onPause() {
        super.onPause()
        gameView!!.pause()
    }

    //running the game when activity is resumed
    override fun onResume() {
        super.onResume()
        gameView!!.resume()
    }
}