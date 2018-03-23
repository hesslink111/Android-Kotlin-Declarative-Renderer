package com.willpease.declarativerenderer.scout

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v4.widget.Space
import android.support.v4.widget.TextViewCompat
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import com.willpease.declarativerenderer.R
import com.willpease.declarativerenderer.dimensionutils.dpToPx
import com.willpease.declarativerenderer.renderer.*

/**
 * @author Will Pease
 * @date 3/12/18
 */

class Header(context: Context) : Component(context) {

    private val weatherButtonStyle: Drawable = context
            .obtainStyledAttributes(intArrayOf(android.R.attr.selectableItemBackground))
            .getDrawable(0)
    private val menuButtonStyle: Drawable = context
            .obtainStyledAttributes(intArrayOf(android.R.attr.selectableItemBackground))
            .getDrawable(0)

    override fun render(): Element<View> {

        return E(::RelativeLayout, arrayOf(
                p(RelativeLayout::setLayoutParams, FrameLayout.LayoutParams(MATCH_PARENT, 56.dpToPx)),
                p(RelativeLayout::setBackgroundResource, R.color.colorPrimary),
                p(RelativeLayout::setPadding, 5.dpToPx, 5.dpToPx, 5.dpToPx, 5.dpToPx)
        ), arrayOf(
                // Title
                E(::TextView, arrayOf(
                        p(TextView::setText, "Scout"),
                        p(TextViewCompat::setTextAppearance, R.style.TextAppearance_AppCompat_Widget_ActionBar_Title_Inverse),
                        p(TextView::setPadding, 10.dpToPx, 10.dpToPx, 10.dpToPx, 10.dpToPx),
                        p(TextView::setLayoutParams, relativeLayoutParams(WRAP_CONTENT, WRAP_CONTENT, {
                            addRule(RelativeLayout.ALIGN_PARENT_TOP)
                            addRule(RelativeLayout.CENTER_VERTICAL)
                        }))
                )),

                // Right side buttons
                E(::LinearLayout, arrayOf(
                        p(LinearLayout::setLayoutParams, relativeLayoutParams(WRAP_CONTENT, WRAP_CONTENT, {
                            addRule(RelativeLayout.ALIGN_PARENT_TOP)
                            addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
                            addRule(RelativeLayout.CENTER_VERTICAL)
                        }))
                ), arrayOf(
                        // Weather icon
                        E(::ImageButton, arrayOf(
                                p(ImageButton::setImageResource, R.drawable.ic_wb_cloudy_black_24dp),
                                p(ImageButton::setBackground, weatherButtonStyle),
                                p(ImageButton::setColorFilter, Color.WHITE),
                                p(ImageButton::setPadding, 10.dpToPx, 10.dpToPx, 10.dpToPx, 10.dpToPx)
                        )),

                        E(::Space, arrayOf(
                                p(Space::setLayoutParams, LinearLayout.LayoutParams(5.dpToPx, LayoutParams.MATCH_PARENT))
                        )),

                        // Menu icon
                        E(::ImageButton, arrayOf(
                                p(ImageButton::setImageResource, R.drawable.ic_more_vert_black_24dp),
                                p(ImageButton::setBackground, menuButtonStyle),
                                p(ImageButton::setColorFilter, Color.WHITE),
                                p(ImageButton::setPadding, 5.dpToPx, 10.dpToPx, 5.dpToPx, 10.dpToPx)
                        ))
                ))
        ))
    }

}
