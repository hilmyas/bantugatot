package com.astudio.bantugatot.models

import android.content.Context
import android.graphics.BitmapFactory
import com.astudio.bantugatot.R

/**
 * Created by lenovo on 13/08/2017.
 */
class Boom(context: Context): BaseModel() {

    //constructor
    init {
        //getting boom image from drawable resource
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.boom)

        //setting the coordinate outside the screen
        //so that it won't shown up in the screen
        //it will be only visible for a fraction of second
        //after collission
        x = -250
        y = -250
    }
}