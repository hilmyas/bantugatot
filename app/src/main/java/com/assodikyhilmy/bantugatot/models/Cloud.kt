package com.assodikyhilmy.bantugatot.models

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.assodikyhilmy.bantugatot.R
import java.util.*

/**
 * Created by lenovo on 12/08/2017.
 */
class Cloud(context: Context, screenX: Int, screenY: Int): BasePhysic() {
    private val distance: Int
    fun update(playerSpeed: Int) {
        //animating the star horizontally left side
        //by decreasing x coordinate with player speed
        x -= playerSpeed
        x -= speed
        //if the star reached the left edge of the screen
        if (x < minX) {
            //again starting the star from right edge
            //this will give a infinite scrolling background effect
            x = maxX
            val generator = Random()
            y = generator.nextInt(maxY - minY) + minY
            speed = generator.nextInt(15)
        }
    }

    init {
        //getting bitmap from drawable resource
        val bitmap: Bitmap
        val temp: Bitmap
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.cloud1)
        maxX = screenX
        maxY = screenY
        minX = 0 - bitmap.width
        minY = 0 + screenY * 3 / 5
        val generator = Random()
        speed = generator.nextInt(10)
        distance = generator.nextInt(2)

        //generating a random coordinate
        //but keeping the coordinate inside the screen size
        x = generator.nextInt(maxX)
        y = generator.nextInt(maxY - minY) + minY
        temp = if (distance == 0) {
            Bitmap.createBitmap(Bitmap.createScaledBitmap(bitmap, bitmap.width * 1, bitmap.height * 1, false))
        } else {
            Bitmap.createBitmap(Bitmap.createScaledBitmap(bitmap, bitmap.width * 3 / 5, bitmap.height * 3 / 5, false))
        }
        this.bitmap = temp
    }
}