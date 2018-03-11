package com.willpease.declarativerenderer

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import com.willpease.declarativerenderer.caro.CaroBoard

/**
 * Created by willpease on 3/10/18.
 */

class MainComponent(context: Context) : Component(context) {

    override fun render(): Element =
            E(::LinearLayout, arrayOf(
                    P().set(LinearLayout::setOrientation, LinearLayout.VERTICAL),
                    P().set(LinearLayout::setGravity, Gravity.CENTER)
            ), arrayOf(
                    E(::CaroBoard)
            ))

}