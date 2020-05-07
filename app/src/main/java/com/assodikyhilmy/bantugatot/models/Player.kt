package com.assodikyhilmy.bantugatot.models

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.Rect
import com.assodikyhilmy.bantugatot.R

/**
 * Created by lenovo on 12/08/2017.
 */
class Player(context: Context, screenX: Int, screenY: Int) {
    private val bitmap: Bitmap
    private val bitmapLose: Bitmap
    var displayedBitmap: Bitmap
    private val rotatedBitmap: Bitmap
    val x = 75
    var y = 50
        private set
    var speed = 0
        private set

    //boolean variable to track the ship is boosting or not
    private var boosting: Boolean

    //Gravity Value to add gravity effect on the ship
    private val GRAVITY = -10

    //Controlling Y coordinate so that ship won't go outside the screen
    private val maxY: Int
    private val minY: Int

    //Limit the bounds of the ship's speed
    private val MIN_SPEED = 1
    private val MAX_SPEED = 20

    //one more getter for getting the rect object
    val detectCollision: Rect

    //setting boosting true
    fun setBoosting() {
        boosting = true
    }

    //setting boosting false
    fun stopBoosting() {
        boosting = false
    }

    fun update() {
        //if the ship is boosting
        if (boosting) {
            //speeding up the ship
            displayedBitmap = rotatedBitmap
            speed += 2
        } else {
            //slowing down if not boosting
            displayedBitmap = bitmap
            speed -= 5
        }
        //controlling the top speed
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED
        }
        //if the speed is less than min speed
        //controlling it so that it won't stop completely
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED
        }

        //moving the ship down
        y -= speed + GRAVITY

        //but controlling it also so that it won't go off the screen
        if (y < minY) {
            y = minY
        }
        if (y > maxY) {
            y = maxY
        }

        //adding top, left, bottom and right to the rect object
        detectCollision.left = x + bitmap.getWidth() / 4
        detectCollision.top = y + bitmap.getHeight() / 4
        detectCollision.right = x + bitmap.getWidth() / 2
        detectCollision.bottom = y + bitmap.getHeight() / 2
    }

    fun playerLose() {
        displayedBitmap = bitmapLose
    }

    init {
        speed = 1
        val m = Matrix()
        m.postRotate(-15f)
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player)
        bitmapLose = BitmapFactory.decodeResource(context.resources, R.drawable.playerlose)
        rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, false)
        displayedBitmap = bitmap

        //calculating maxY
        maxY = screenY - bitmap.getHeight()

        //top edge's y point is 0 so min y will always be zero
        minY = 0

        //setting the boosting value to false initially
        boosting = false

        //initializing rect object
        detectCollision = Rect(x + bitmap.getWidth() / 4, y + bitmap.getHeight() / 4,
                x + bitmap.getWidth() / 2, y + bitmap.getHeight() / 2)
    }
}