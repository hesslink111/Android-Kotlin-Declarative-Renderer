package com.willpease.declarativerenderer.caro

import android.content.Context
import android.graphics.Color.BLACK
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.willpease.declarativerenderer.dimensionutils.dpToPx
import com.willpease.declarativerenderer.renderer.*

/**
 * @author Will Pease
 * @date 3/10/18
 */

class CaroBoard(context: Context) : Component(context) {

    override fun componentWillMount() {
        CaroStateManager.observers.add(::stateChanged)
    }

    override fun componentWillUnmount() {
        CaroStateManager.observers.remove(::stateChanged)
    }

    fun stateChanged(newState: CaroState) {
        update()
    }

    fun buttonClicked(rowIndex: Int, columnIndex: Int) {
        CaroStateManager.state.colors[rowIndex][columnIndex] = BLACK
        CaroStateManager.updateState()
    }

    override fun render(): Element<View> {

        // Create rows
        val caroRows = CaroStateManager.state.colors.mapIndexed{ rowIndex, sRow ->
            E<View>(::LinearLayout, arrayOf(
                    p(LinearLayout::setOrientation, LinearLayout.HORIZONTAL)
            ), sRow.mapIndexed { columnIndex, sSquareValue ->
                E(::Button, arrayOf(
                        p(Button::setBackgroundColor, sSquareValue),
//                        p(Button::setLayoutParams, LinearLayout.LayoutParams(10.dpToPx, 10.dpToPx)),
                        p(Button::setOnClickListener, OnClickListener { buttonClicked(rowIndex, columnIndex) })
                ))
            }.toTypedArray())
        }.toTypedArray()

        // Put rows in linear layout
        return E<View>(::LinearLayout, arrayOf(
                p(LinearLayout::setOrientation, LinearLayout.VERTICAL)
        ), caroRows)
    }

}
