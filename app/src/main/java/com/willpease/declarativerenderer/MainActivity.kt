package com.willpease.declarativerenderer

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    var mainComponent: MainComponent? = null

    fun getMainComponent(activity: Activity): MainComponent {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainComponent = getMainComponent(this)
        mainComponent.componentWillMount()
        mainComponent.update()
        setContentView(mainComponent)
        mainComponent.componentDidMount()
    }

    override fun onDestroy() {
        val mainComponent = getMainComponent(this)
        mainComponent.componentWillUnmount()

        super.onDestroy()
    }
}
