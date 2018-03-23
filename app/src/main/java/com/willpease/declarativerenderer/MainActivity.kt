package com.willpease.declarativerenderer

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.ContentFrameLayout
import android.view.ViewGroup

/**
 * @author Will Pease
 * @date 3/10/18
 */

class MainActivity : AppCompatActivity() {

    // Component instance -- must be created while activity is alive.
    private var mainComponent: MainComponent? = null

    /**
     * Get the main component in a safe way. Likely overkill.
     */
    private fun getMainComponent(activity: Activity): MainComponent {
        return synchronized(this, {
            val component = mainComponent
            if(component != null) {
                component
            } else {
                val created = MainComponent(activity)
                mainComponent = created
                created
            }
        })
    }

    /**
     * Set the content view to the main component and call the relevant lifecycle methods.
     * Not called on rotation.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainComponent = getMainComponent(this)
        mainComponent.componentWillMount()
        mainComponent.update()
        setContentView(mainComponent)
        mainComponent.componentDidMount()
    }

    /**
     * Call the closing lifecycle methods on the main component.
     */
    override fun onDestroy() {
        val mainComponent = getMainComponent(this)
        mainComponent.componentWillUnmount()

        super.onDestroy()
    }
}
