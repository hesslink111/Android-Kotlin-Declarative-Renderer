package com.willpease.declarativerenderer.scout

/**
 * @author Will Pease
 * @date 3/10/18
 */

class StateManager {

    companion object {
        var state: State = State(screen="map")
        val observers: MutableList<(State)->Unit> = mutableListOf()

        fun updateState(newState: State = state) {
            state = newState
            for(observer in observers) {
                observer(state)
            }
        }
    }
}

data class State(var screen: String)
