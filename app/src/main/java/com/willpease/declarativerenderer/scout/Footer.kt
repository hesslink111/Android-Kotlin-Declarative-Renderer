package com.willpease.declarativerenderer.scout

import android.content.Context
import android.graphics.Color.WHITE
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.LinearLayout
import com.willpease.declarativerenderer.R
import com.willpease.declarativerenderer.renderer.Component
import com.willpease.declarativerenderer.renderer.E
import com.willpease.declarativerenderer.renderer.Element
import com.willpease.declarativerenderer.renderer.p

/**
 * @author Will Pease
 * @date 3/13/18
 */

class Footer(context: Context) : Component(context) {

    val colorActive = ContextCompat.getColor(context, R.color.colorAccent)
    val colorInactive = WHITE

    override fun componentWillMount() {
        StateManager.observers.add(::stateDidUpdate)
    }

    private fun stateDidUpdate(state: State) {
        update()
    }

    override fun componentWillUnmount() {
        StateManager.observers.add(::stateDidUpdate)
    }

    private val onClickMessages = {
        StateManager.updateState(State(screen="messages"))
    }

    private val onClickMap = {
        StateManager.updateState(State(screen="map"))
    }

    private val onClickWaypoints = {
        StateManager.updateState(State(screen="waypoints"))
    }

    override fun render(): Element<View> {
        return E<View>(::LinearLayout, arrayOf(
                p(LinearLayout::setBackgroundResource, R.color.material_blue_grey_800)
        ), arrayOf(
                E(::FooterButton, arrayOf(
                        p(FooterButton::setLayoutParams, LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f)),
                        p(FooterButton::iconResource::set, R.drawable.ic_message_black_24dp),
                        p(FooterButton::buttonText::set, "Messages"),
                        p(FooterButton::color::set, if(StateManager.state.screen == "messages") colorActive else colorInactive),
                        p(FooterButton::onClick::set, onClickMessages)
                )),
                E(::FooterButton, arrayOf(
                        p(FooterButton::setLayoutParams, LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f)),
                        p(FooterButton::iconResource::set, R.drawable.ic_map_black_24dp),
                        p(FooterButton::buttonText::set, "Map"),
                        p(FooterButton::color::set, if(StateManager.state.screen == "map") colorActive else colorInactive),
                        p(FooterButton::onClick::set, onClickMap)
                )),
                E(::FooterButton, arrayOf(
                        p(FooterButton::setLayoutParams, LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f)),
                        p(FooterButton::iconResource::set, R.drawable.ic_flag_black_24dp),
                        p(FooterButton::buttonText::set, "Waypoints"),
                        p(FooterButton::color::set, if(StateManager.state.screen == "waypoints") colorActive else colorInactive),
                        p(FooterButton::onClick::set, onClickWaypoints)
                ))
        ))
    }

}