package com.assodikyhilmy.bantugatot.models

import android.graphics.Bitmap
import android.graphics.Rect

abstract class BasePhysic: BaseModel() {
    //one more getter for getting the rect object
    lateinit var detectCollision: Rect

    var speed: Int = 0

    var maxX: Int = 0
    var maxY: Int = 0
    var minX: Int = 0
    var minY: Int = 0
}