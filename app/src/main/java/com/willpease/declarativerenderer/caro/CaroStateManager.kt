package com.willpease.declarativerenderer.caro

/**
 * @author Will Pease
 * @date 3/10/18
 */

class CaroStateManager {

    companion object {
        var state: CaroState = CaroState()
        val observers: MutableList<(CaroState)->Unit> = mutableListOf()

        fun updateState(newState: CaroState = state) {
            state = newState
            for(observer in observers) {
                observer(state)
            }
        }
    }
}
