package com.astudio.bantugatot.activities

import android.graphics.Point
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.astudio.bantugatot.views.GameView

/**
 * Created by lenovo on 12/08/2017.
 */
class GameActivity : AppCompatActivity() {
    //declaring gameview
    private var gameView: GameView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        gameView!!.pause()
        super.onPause()
    }

    //running the game when activity is resumed
    override fun onResume() {
        super.onResume()
        gameView!!.resume()

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}