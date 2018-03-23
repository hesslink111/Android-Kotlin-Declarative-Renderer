package com.willpease.declarativerenderer.scout

import android.content.Context
import android.graphics.Color.WHITE
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.willpease.declarativerenderer.R
import com.willpease.declarativerenderer.dimensionutils.dpToPx
import com.willpease.declarativerenderer.renderer.Component
import com.willpease.declarativerenderer.renderer.E
import com.willpease.declarativerenderer.renderer.Element
import com.willpease.declarativerenderer.renderer.p

/**
 * @author Will Pease
 * @date 3/13/18
 */

class FooterButton(context: Context) : Component(context) {

    var iconResource = R.drawable.abc_vector_test
        set(resource) {
            field = resource
            update()
        }

    var buttonText = ""
        set(text) {
            field = text
            update()
        }

    var color = WHITE
        set(c) {
            field = c
            update()
        }

    var onClick = {}
        set(handler) {
            field = handler
            update()
        }

    private val rippleButtonBackground = context
            .obtainStyledAttributes(intArrayOf(android.R.attr.selectableItemBackground))
            .getDrawable(0)

    private val onClickListener = View.OnClickListener {
        onClick()
    }

    override fun render(): Element<View> {
        return E(::LinearLayout, arrayOf(
                p(LinearLayout::setOrientation, LinearLayout.VERTICAL),
                p(LinearLayout::setGravity, Gravity.CENTER),
                p(LinearLayout::setPadding, 10.dpToPx, 10.dpToPx, 10.dpToPx, 10.dpToPx),
                p(LinearLayout::setClickable, true),
                p(LinearLayout::setOnClickListener, onClickListener),
                p(LinearLayout::setBackground, rippleButtonBackground)
        ), arrayOf(
                E(::ImageView, arrayOf(
                        p(ImageView::setImageResource, iconResource),
                        p(ImageView::setColorFilter, color)
                )),
                E(::TextView, arrayOf(
                        p(TextView::setText, buttonText),
                        p(TextView::setLayoutParams, LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)),
                        p(TextView::setTextColor, color)
                ))

        ))
    }

}