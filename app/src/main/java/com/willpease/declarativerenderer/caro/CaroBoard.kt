package com.willpease.declarativerenderer.caro

import android.content.Context
import android.graphics.Color.BLACK
import android.widget.Button
import android.widget.LinearLayout
import com.willpease.declarativerenderer.renderer.Component
import com.willpease.declarativerenderer.renderer.E
import com.willpease.declarativerenderer.renderer.Element
import com.willpease.declarativerenderer.renderer.P
import com.willpease.declarativerenderer.dimensionutils.dpToPx

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

    override fun render(): Element {

        // Create rows
        val caroRows = CaroStateManager.state.colors.mapIndexed{ rowIndex, sRow ->
            E(::LinearLayout, arrayOf(
                    P().set(LinearLayout::setOrientation, LinearLayout.HORIZONTAL)
            ), sRow.mapIndexed { columnIndex, sSquareValue ->
                E(::Button, arrayOf(
                        P().set(Button::setBackgroundColor, sSquareValue),
                        P().set(Button::setLayoutParams, LinearLayout.LayoutParams(10.dpToPx, 10.dpToPx)),
                        P().set(Button::setOnClickListener, OnClickListener { buttonClicked(rowIndex, columnIndex) })
                ))
            }.toTypedArray())
        }.toTypedArray()

        // Put rows in linear layout
        return E(::LinearLayout, arrayOf(
                P().set(LinearLayout::setOrientation, LinearLayout.VERTICAL)
        ), caroRows)
    }

}
