package com.willpease.declarativerenderer

import android.content.Context
import android.view.View
import android.view.ViewGroup.LayoutParams.*
import android.widget.*
import com.willpease.declarativerenderer.renderer.*
import com.willpease.declarativerenderer.scout.Footer
import com.willpease.declarativerenderer.scout.Header
import com.willpease.declarativerenderer.scout.State
import com.willpease.declarativerenderer.scout.StateManager

/**
 * @author Will Pease
 * @date 3/10/18
 */

class MainComponent(context: Context) : Component(context) {

    override fun componentDidMount() {
        StateManager.observers.add(::stateDidUpdate)
    }

    override fun componentWillUnmount() {
        StateManager.observers.remove(::stateDidUpdate)
    }

    private fun stateDidUpdate(state: State) {
        update()
    }

    /**
     * Render a RelativeLayout with a Header, Content, and Footer inside.
     */
    override fun render(): Element<View> {

        val mainScreen = if(StateManager.state.screen == "messages") {
            E(::View, arrayOf(
                    p(View::setId, 2),
                    p(View::setBackgroundResource, R.color.transparent),
                    p(View::setLayoutParams, relativeLayoutParams(WRAP_CONTENT, WRAP_CONTENT, {
                        addRule(RelativeLayout.BELOW, 1)
                        addRule(RelativeLayout.ABOVE, 3)
                    }))
            ))
        } else if(StateManager.state.screen == "map") {
            E(::MapView, arrayOf(
                    p(MapView::setId, 2),
                    p(MapView::setLayoutParams, relativeLayoutParams(WRAP_CONTENT, WRAP_CONTENT, {
                        addRule(RelativeLayout.BELOW, 1)
                        addRule(RelativeLayout.ABOVE, 3)
                    }))
            ))
        } else {
            E(::View, arrayOf(
                    p(View::setId, 2),
                    p(View::setBackgroundResource, R.color.transparent),
                    p(View::setLayoutParams, relativeLayoutParams(WRAP_CONTENT, WRAP_CONTENT, {
                        addRule(RelativeLayout.BELOW, 1)
                        addRule(RelativeLayout.ABOVE, 3)
                    }))
            ))
        }

        return E<View>(::RelativeLayout, arrayOf(
                p(RelativeLayout::setLayoutParams, FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT))
        ), arrayOf(

                // Toolbar
                E(::Header, arrayOf(
                        p(Header::setId, 1),
                        p(Header::setLayoutParams, relativeLayoutParams(MATCH_PARENT, WRAP_CONTENT, {
                            addRule(RelativeLayout.ALIGN_PARENT_TOP)
                            addRule(RelativeLayout.ALIGN_PARENT_LEFT)
                            addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
                        }))
                )),

                // Messages/MapView/Waypoints
                mainScreen,

                // Footer
                E(::Footer, arrayOf(
                        p(Footer::setId, 3),
                        p(Footer::setLayoutParams, relativeLayoutParams(MATCH_PARENT, WRAP_CONTENT, {
                            addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                        }))
                ))

        ))
    }

}
