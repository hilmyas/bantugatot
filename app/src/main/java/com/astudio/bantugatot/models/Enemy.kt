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
class Enemy(context: Context, screenX: Int, screenY: Int): BasePhysic() {

    fun update(playerSpeed: Int) {
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
        detectCollision.right = x + bitmap.width
        detectCollision.bottom = y + bitmap.height
    }

    fun claps(context: Context) {
        MultiMediaUtils.playThunderClap(context)
    }

    init {
        //getting bitmap from drawable resource
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.cloud3)

        //initializing min and max coordinates
        maxX = screenX
        maxY = screenY - bitmap.height
        minX = 0 - bitmap.width
        minY = 0

        //generating a random coordinate to add enemy
        val generator = Random()
        speed = generator.nextInt(6) + 10
        x = screenX
        y = generator.nextInt(maxY)

        //initializing rect object
        detectCollision = Rect(x, y, x + bitmap.width, y + bitmap.height)
    }
}