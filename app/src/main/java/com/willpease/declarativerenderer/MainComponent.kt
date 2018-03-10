package com.willpease.declarativerenderer

import android.app.Activity
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.util.*

/**
 * Created by willpease on 3/10/18.
 */

class MainComponent(val activity: Activity) : Component(activity) {

    override fun componentWillMount() {
        // Do nothing
    }

    override fun componentDidMount() {
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                activity.runOnUiThread {
                    setEverything(true, "heyooo", 140f, Gravity.START)
                }
            }
        }, 5000)

        timer.schedule(object : TimerTask() {
            override fun run() {
                activity.runOnUiThread {
                    setEverything(false, "hey there bub", 11f, Gravity.BOTTOM)
                }
            }
        }, 8000)
    }

    override fun componentWillUnmount() {
        // Do nothing
    }

    var isButton = false
    var text = "hello"
    var size = 10f
    var gravity = Gravity.CENTER

    fun setEverything(isButton: Boolean, text: String, size: Float, gravity: Int) {
        this.isButton = isButton
        this.text = text
        this.size = size
        this.gravity = gravity
        update()
    }

    override fun render(): Element {

        // Unfortunate Kotlinism - overloaded methods can only be implicitly referenced
        val setTextButton: ((Button, CharSequence) -> Unit) = Button::setText
        val setTextSizeButton: ((Button, Float) -> Unit) = Button::setTextSize

        val setText: ((TextView, CharSequence) -> Unit) = TextView::setText
        val setTextSize: ((TextView, Float) -> Unit) = TextView::setTextSize

        return Element(::LinearLayout, arrayOf(
                PropertySetting().set(LinearLayout::setOrientation, LinearLayout.VERTICAL),
                PropertySetting().set(LinearLayout::setGravity, gravity)
        ), arrayOf(
                if(isButton) {
                    Element(::Button, arrayOf(
                            PropertySetting().set(setTextButton, text),
                            PropertySetting().set(setTextSizeButton, size)
                    ))
                } else {
                    Element(::TextView, arrayOf(
                            PropertySetting().set(setText, text),
                            PropertySetting().set(setTextSize, size)
                    ))
                }
        ))
    }

}