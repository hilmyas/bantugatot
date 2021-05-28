package com.assodikyhilmy.bantugatot.helpers

import android.graphics.Region

/**
 * Created by lenovo on 14/08/2017.
 */
class Utils {
    fun isIntersect(regA: Region, regB: Region): Boolean {
        return regA.op(regB, Region.Op.INTERSECT)
    }


}