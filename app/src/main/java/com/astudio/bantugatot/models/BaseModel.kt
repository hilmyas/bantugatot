package com.astudio.bantugatot.models

import android.graphics.Bitmap

abstract class BaseModel {
    lateinit var bitmap: Bitmap
    //adding a setter to x coordinate so that we can change it after collision
    //x and y coordinates
    var x: Int = 0
    var y: Int = 0
}