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
class Player(context: Context, screenX: Int, screenY: Int): MultiFormPhysic() {

    companion object {
        const val CHAR_NORMAL = "char_normal"
        const val CHAR_LOSE = "char_lose"
        const val CHAR_UP = "char_up"
    }

    //boolean variable to track the ship is boosting or not
    private var boosting: Boolean

    //Gravity Value to add gravity effect on the ship
    private val GRAVITY = -10

    //Limit the bounds of the ship's speed
    val MIN_SPEED = 3
    val MAX_SPEED = 20

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
            displayedBitmap = formMap.get(CHAR_UP)?: bitmap
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
        displayedBitmap = formMap.get(CHAR_LOSE)?: bitmap
    }

    init {
        speed = 3
        val m = Matrix()
        m.postRotate(-15f)
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player)
        formMap.put(CHAR_LOSE, BitmapFactory.decodeResource(context.resources, R.drawable.playerlose))
        formMap.put(CHAR_UP, Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, false))
        displayedBitmap = bitmap

        x = 75
        y = 50

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