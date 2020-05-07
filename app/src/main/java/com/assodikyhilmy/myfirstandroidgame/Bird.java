package com.assodikyhilmy.myfirstandroidgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by lenovo on 12/08/2017.
 */

public class Bird {
    //bitmap for the enemy
    //we have already pasted the bitmap in the drawable folder
    private Bitmap displayedBitmap;
    private Bitmap bitmap;
    private Bitmap bitmap2;
    private boolean upState;
    private int delay;
    private int maxDelay=7; //frames

    //x and y coordinates
    private int x;
    private int y;

    //enemy speed
    private int speed = 1;

    //min and max coordinates to keep the enemy inside the screen
    private int maxX;
    private int minX;

    private int maxY;
    private int minY;

    //creating a rect object
    private Rect detectCollision;

    public Bird(Context context, int screenX, int screenY) {
        //getting bitmap from drawable resource
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird1);
        bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird2);
        displayedBitmap = bitmap;

        //initializing min and max coordinates
        maxX = screenX;
        maxY = screenY - bitmap.getHeight();
        minX = 0 - bitmap.getWidth();
        minY = 0;

        //generating a random coordinate to add enemy
        Random generator = new Random();
        speed = generator.nextInt(6) + 10;
        x = screenX;
        y = generator.nextInt(maxY);

        //initializing rect object
        detectCollision = new Rect(x, y, x+bitmap.getWidth(), y+bitmap.getHeight());

        upState=true;
        delay= 0;
    }

    public void update(int playerSpeed) {
        if(upState){
            delay++;
            if(delay==maxDelay) {
                delay=0;
                upState = false;
                displayedBitmap = bitmap2;
            }
        }
        else{
            delay++;
            if(delay==maxDelay) {
                delay = 0;
                upState = true;
                displayedBitmap = bitmap;
            }
        }

        //decreasing x coordinate so that enemy will move right to left
        x -= playerSpeed;
        x -= speed;
        //if the enemy reaches the left edge
        if (x < minX) {
            //adding the enemy again to the right edge
            Random generator = new Random();
            speed = generator.nextInt(10) + 10;
            x = maxX;
            y = generator.nextInt(maxY);
        }

        //Adding the top, left, bottom and right to the rect object
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + displayedBitmap.getWidth();
        detectCollision.bottom = y + displayedBitmap.getHeight();
    }

    //adding a setter to x coordinate so that we can change it after collision
    public void setX(int x){
        this.x = x;
    }

    //one more getter for getting the rect object
    public Rect getDetectCollision() {
        return detectCollision;
    }

    //getters
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

}
