package com.willpease.declarativerenderer.dimensionutils

import android.content.res.Resources

/**
 * Created by willpease on 3/11/18.
 */

val Int.pxToDp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()