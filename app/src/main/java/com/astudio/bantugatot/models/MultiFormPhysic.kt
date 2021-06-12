package com.astudio.bantugatot.models

import android.graphics.Bitmap

abstract class MultiFormPhysic: BasePhysic() {
    val formMap: HashMap<String, Bitmap>
    lateinit var displayedBitmap: Bitmap

    init {
        formMap = hashMapOf()
    }
}