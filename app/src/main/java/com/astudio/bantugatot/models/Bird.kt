package com.astudio.bantugatot.models

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Rect
import com.astudio.bantugatot.R
import com.astudio.bantugatot.helpers.MultiMediaUtils
import java.util.*

/**
 * Created by lenovo on 12/08/2017.
 */
class Bird(context: Context, screenX: Int, screenY: Int): MultiFormPhysic() {

    companion object {
        const val WINGS_UP = "wings_flap_up"
        const val WINGS_DOWN = "wings_flap_down"
    }

    //getters
    private var upState: Boolean
    private var delay: Int
    private val maxDelay = 7 //frames

    fun update(playerSpeed: Int) {
        if (upState) {
            delay++
            if (delay == maxDelay) {
                delay = 0
                upState = false
                displayedBitmap = formMap.get(WINGS_DOWN)?: bitmap
            }
        } else {
            delay++
            if (delay == maxDelay) {
                delay = 0
                upState = true
                displayedBitmap = bitmap
            }
        }

        //decreasing x coordinate so that enemy will move right to left
        x -= playerSpeed
        x -= speed
        //if the enemy reaches the left edge
        if (x < minX) {
            //adding the enemy again to the right edge
            val generator = Random()
            speed = generator.nextInt(10) + 10
            x = maxX
            y = generator.nextInt(maxY)
        }

        //Adding the top, left, bottom and right to the rect object
        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + bitmap.getWidth()
        detectCollision.bottom = y + bitmap.getHeight()
    }

    fun saved(context: Context) {
        MultiMediaUtils.playGetScore(context)
    }

    init {
        //getting bitmap from drawable resource
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.bird1)
        formMap.put(WINGS_DOWN, BitmapFactory.decodeResource(context.resources, R.drawable.bird2))
        displayedBitmap = bitmap

        //initializing min and max coordinates
        maxX = screenX
        maxY = screenY - bitmap.getHeight()
        minX = 0 - bitmap.getWidth()
        minY = 0

        //generating a random coordinate to add enemy
        val generator = Random()
        speed = generator.nextInt(6) + 10
        x = screenX
        y = generator.nextInt(maxY)

        //initializing rect object
        detectCollision = Rect(x, y, x + bitmap.getWidth(), y + bitmap.getHeight())
        upState = true
        delay = 0
    }
}