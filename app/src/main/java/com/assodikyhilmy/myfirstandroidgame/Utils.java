package com.assodikyhilmy.myfirstandroidgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region;
import android.graphics.drawable.shapes.Shape;
import android.hardware.Camera;

/**
 * Created by lenovo on 14/08/2017.
 */

public class Utils {
    public Utils(){}

    public boolean isIntersect(Region regA, Region regB){
        return regA.op(regB, Region.Op.INTERSECT);
    }
}
