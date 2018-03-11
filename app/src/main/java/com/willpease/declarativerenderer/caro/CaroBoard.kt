package com.willpease.declarativerenderer.caro

import android.content.Context
import android.content.res.Resources
import android.graphics.Color.BLACK
import android.widget.Button
import android.widget.LinearLayout
import com.willpease.declarativerenderer.Component
import com.willpease.declarativerenderer.E
import com.willpease.declarativerenderer.Element
import com.willpease.declarativerenderer.P
import com.willpease.declarativerenderer.dimensionutils.dpToPx

/**
 * Created by willpease on 3/10/18.
 */

class CaroBoard(context: Context) : Component(context) {

    override fun componentWillMount() {
        CaroState.CaroStateManager.stateChangeObservers.add(::stateChanged)
    }

    override fun componentWillUnmount() {
        CaroState.CaroStateManager.stateChangeObservers.remove(::stateChanged)
    }

    fun stateChanged(newState: CaroState) {
        update()
    }

    fun onButtonClick(rowIndex: Int, columnIndex: Int) {
        CaroState.CaroStateManager.state.colors[rowIndex][columnIndex] = BLACK
        CaroState.CaroStateManager.updateState()
    }

    override fun render(): Element {

        // Create rows
        val caroRows = CaroState.CaroStateManager.state.colors.mapIndexed{ rowIndex, sRow ->
            E(::LinearLayout, arrayOf(
                    P().set(LinearLayout::setOrientation, LinearLayout.HORIZONTAL)
            ), sRow.mapIndexed { columnIndex, sSquareValue ->
                E(::Button, arrayOf(
                        P().set(Button::setBackgroundColor, sSquareValue),
                        P().set(Button::setLayoutParams, LinearLayout.LayoutParams(10.dpToPx, 10.dpToPx)),
                        P().set(Button::setOnClickListener, OnClickListener { onButtonClick(rowIndex, columnIndex) })
                ))
            }.toTypedArray())
        }.toTypedArray()

        // Put rows in linear layout
        return E(::LinearLayout, arrayOf(
                P().set(LinearLayout::setOrientation, LinearLayout.VERTICAL)
        ), caroRows)
    }

}
