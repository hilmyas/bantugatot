package com.assodikyhilmy.bantugatot.models

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import com.assodikyhilmy.bantugatot.R
import java.util.*

/**
 * Created by lenovo on 12/08/2017.
 */
class Bird(context: Context, screenX: Int, screenY: Int) {
    //getters
    //bitmap for the enemy
    //we have already pasted the bitmap in the drawable folder
    private var displayedBitmap: Bitmap
    val bitmap: Bitmap
    private val bitmap2: Bitmap
    private var upState: Boolean
    private var delay: Int
    private val maxDelay = 7 //frames

    //adding a setter to x coordinate so that we can change it after collision
    //x and y coordinates
    var x: Int
    var y: Int
        private set

    //enemy speed
    var speed = 1
        private set

    //min and max coordinates to keep the enemy inside the screen
    private val maxX: Int
    private val minX: Int
    private val maxY: Int
    private val minY: Int

    //one more getter for getting the rect object
    //creating a rect object
    val detectCollision: Rect
    fun update(playerSpeed: Int) {
        if (upState) {
            delay++
            if (delay == maxDelay) {
                delay = 0
                upState = false
                displayedBitmap = bitmap2
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

    init {
        //getting bitmap from drawable resource
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.bird1)
        bitmap2 = BitmapFactory.decodeResource(context.resources, R.drawable.bird2)
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