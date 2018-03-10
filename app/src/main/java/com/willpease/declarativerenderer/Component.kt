package com.willpease.declarativerenderer

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import kotlin.math.max

/**
 * Created by willpease on 3/8/18.
 */

abstract class Component(context: Context) : FrameLayout(context) {

    // Null if not mounted
    var element: Element? = null

    var isTransactionStarted = false

    fun update() {
        if(!isTransactionStarted) {
            val newElement = this.render()
            mount(this, 0, newElement, element)
            element = newElement
        }
    }

    fun startTransaction() {
        isTransactionStarted = true
    }

    fun endTransaction() {
        isTransactionStarted = false
    }

    abstract fun render(): Element
    abstract fun componentWillMount()
    abstract fun componentDidMount()
    abstract fun componentWillUnmount()

    /**
     * Replace an instance based on the element oldElement with an instance based on the element
     * newElement in the instance parentInstance at the index indexInParent.
     */
    fun mount(parentInstance: ViewGroup, indexInParent: Int, newElement: Element?, oldElement: Element?) {
        val oldInstance = parentInstance.getChildAt(indexInParent)
        var newInstance: View?
        var changedProps: List<PropertySetting>?

        // Find newInstance
        if(newElement == null) {
            newInstance = null
            changedProps = emptyList()
        } else if(oldInstance == null || oldElement == null || newElement.ctor != oldElement.ctor) {
            newInstance = newElement.ctor(context)
            // use all props
            changedProps = newElement.props.values.toList()
        } else {
            newInstance = oldInstance
            // use changed props
            changedProps = newElement.props
                    .filter{entry -> !oldElement.props.containsKey(entry.key) || oldElement.props[entry.key] != entry.value}
                    .values.toList()
        }

        // apply changed props
        if(newInstance != null) {
            if(newInstance is Component) {
                newInstance.startTransaction()
            }

            for(prop in changedProps) {
                prop.applyTo(newInstance)
            }

            if(newInstance is Component) {
                newInstance.endTransaction()
            }
        }

        // apply children
        // do something else if newinstance is a component
        if(newInstance != null && newInstance is ViewGroup && newInstance !is Component) {
            val numNewChildren = newElement?.children?.size ?: 0
            val numOldChildren = oldElement?.children?.size ?: 0
            for(i in (0 until max(numNewChildren, numOldChildren)).reversed()) {
                val newChildElement = newElement?.children?.getOrNull(i)
                val oldChildElement = oldElement?.children?.getOrNull(i)
                mount(newInstance, i, newChildElement, oldChildElement)
            }
        }

        // Add and remove when changed
        if(newInstance !== oldInstance) {
            // remove old if necessary
            if(oldInstance !== null) {
                // call lifecycle method if necessary
                if(oldInstance is Component) {
                    oldInstance.componentWillUnmount()
                }

                parentInstance.removeViewAt(indexInParent)
            }

            // push to parent if necessary
            if(newInstance != null) {
                // call lifecycle method if necessary
                if(newInstance is Component) {
                    newInstance.componentWillMount()
                    newInstance.update()
                }

                parentInstance.addView(newInstance, indexInParent)

                // call lifecycle method if necessary
                if(newInstance is Component) {
                    newInstance.componentDidMount()
                }
            }
        }
    }
}
