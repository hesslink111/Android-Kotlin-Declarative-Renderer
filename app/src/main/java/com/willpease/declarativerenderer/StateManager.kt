package com.willpease.declarativerenderer

/**
 * Created by willpease on 3/10/18.
 */

class StateManager<T>(initialState: T) {
    var state: T = initialState
    val stateChangeObservers: MutableList<(T)->Unit> = mutableListOf()

    fun updateState(newState: T = state) {
        this.state = newState
        for(observer in stateChangeObservers) {
            observer(this.state)
        }
    }
}
