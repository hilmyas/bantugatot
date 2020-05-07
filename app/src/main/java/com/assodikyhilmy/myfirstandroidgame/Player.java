package com.assodikyhilmy.myfirstandroidgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;

/**
 * Created by lenovo on 12/08/2017.
 */

public class Player {
    private Bitmap bitmap;
    private Bitmap bitmapLose;
    private Bitmap displayedBitmap;
    private Bitmap rotatedBitmap;
    private int x;
    private int y;
    private int speed = 0;

    //boolean variable to track the ship is boosting or not
    private boolean boosting;

    //Gravity Value to add gravity effect on the ship
    private final int GRAVITY = -10;

    //Controlling Y coordinate so that ship won't go outside the screen
    private int maxY;
    private int minY;

    //Limit the bounds of the ship's speed
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    private Rect detectCollision;

    public Player(Context context, int screenX, int screenY) {
        x = 75;
        y = 50;
        speed = 1;
        Matrix m = new Matrix();
        m.postRotate(-15);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        bitmapLose = BitmapFactory.decodeResource(context.getResources(), R.drawable.playerlose);
        rotatedBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),m,false);
        displayedBitmap = bitmap;

        //calculating maxY
        maxY = screenY - bitmap.getHeight();

        //top edge's y point is 0 so min y will always be zero
        minY = 0;

        //setting the boosting value to false initially
        boosting = false;

        //initializing rect object
        detectCollision =  new Rect(x + bitmap.getWidth() / 4, y + bitmap.getHeight() / 4,
                x+bitmap.getWidth() / 2, y+bitmap.getHeight() / 2);
    }


    //setting boosting true
    public void setBoosting() {
        boosting = true;
    }

    //setting boosting false
    public void stopBoosting() {
        boosting = false;
    }

    public void update() {
        //if the ship is boosting
        if (boosting) {
            //speeding up the ship
            displayedBitmap = rotatedBitmap;
            speed += 2;
        } else {
            //slowing down if not boosting
            displayedBitmap = bitmap;
            speed -= 5;
        }
        //controlling the top speed
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        //if the speed is less than min speed
        //controlling it so that it won't stop completely
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }

        //moving the ship down
        y -= speed + GRAVITY;

        //but controlling it also so that it won't go off the screen
        if (y < minY) {
            y = minY;
        }
        if (y > maxY) {
            y = maxY;
        }

        //adding top, left, bottom and right to the rect object
        detectCollision.left = x + displayedBitmap.getWidth() / 4;
        detectCollision.top = y + displayedBitmap.getHeight() / 4;
        detectCollision.right = x + displayedBitmap.getWidth() / 2;
        detectCollision.bottom = y + displayedBitmap.getHeight() / 2;
    }

    //one more getter for getting the rect object
    public Rect getDetectCollision() {
        return detectCollision;
    }

    public Bitmap getBitmap() {
        return displayedBitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void playerLose(){
        displayedBitmap = bitmapLose;
    }
}
