package com.assodikyhilmy.myfirstandroidgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

/**
 * Created by lenovo on 12/08/2017.
 */

public class Cloud {
    private int x;
    private int y;
    private int speed;
    private Bitmap displayedBitmap;

    private int maxX;
    private int maxY;
    private int minX;
    private int minY;
    private int distance;


    public Cloud(Context context, int screenX, int screenY) {
        //getting bitmap from drawable resource
        Bitmap bitmap;
        Bitmap temp;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cloud1);

        maxX = screenX;
        maxY = screenY;
        minX = 0 - bitmap.getWidth();
        minY = 0 + screenY*3/5;
        Random generator = new Random();
        speed = generator.nextInt(10);
        distance = generator.nextInt(2);

        //generating a random coordinate
        //but keeping the coordinate inside the screen size
        x = generator.nextInt(maxX);
        y = generator.nextInt(maxY-minY)+minY;

        if(distance==0){
            temp = Bitmap.createBitmap(Bitmap.createScaledBitmap(bitmap,bitmap.getWidth()*1,bitmap.getHeight()*1,false));
        }
        else{
            temp = Bitmap.createBitmap(Bitmap.createScaledBitmap(bitmap,bitmap.getWidth()*3/5,bitmap.getHeight()*3/5,false));
        }
        displayedBitmap = temp;
    }

    public void update(int playerSpeed) {
        //animating the star horizontally left side
        //by decreasing x coordinate with player speed
        x -= playerSpeed;
        x -= speed;
        //if the star reached the left edge of the screen
        if (x < minX) {
            //again starting the star from right edge
            //this will give a infinite scrolling background effect
            x = maxX;
            Random generator = new Random();
            y = generator.nextInt(maxY-minY)+minY;
            speed = generator.nextInt(15);
        }
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

}
