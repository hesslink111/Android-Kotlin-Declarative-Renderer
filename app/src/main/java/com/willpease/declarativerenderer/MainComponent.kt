package com.willpease.declarativerenderer

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import com.willpease.declarativerenderer.caro.CaroBoard
import com.willpease.declarativerenderer.renderer.Component
import com.willpease.declarativerenderer.renderer.E
import com.willpease.declarativerenderer.renderer.Element
import com.willpease.declarativerenderer.renderer.P

/**
 * @author Will Pease
 * @date 3/10/18
 */

class MainComponent(context: Context) : Component(context) {

    /**
     * Render a LinearLayout with a CaroBoard inside.
     */
    override fun render(): Element =
            E(::LinearLayout, arrayOf(
                    P().set(LinearLayout::setOrientation, LinearLayout.VERTICAL),
                    P().set(LinearLayout::setGravity, Gravity.CENTER)
            ), arrayOf(
                    E(::CaroBoard)
            ))

}