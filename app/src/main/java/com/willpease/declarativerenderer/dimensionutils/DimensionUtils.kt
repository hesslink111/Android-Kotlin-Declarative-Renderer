package com.willpease.declarativerenderer.dimensionutils

import android.content.res.Resources

/**
 * @author Will Pease
 * @date 3/10/18
 */

val Int.pxToDp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()