package com.willpease.declarativerenderer

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * @author Will Pease
 * @date 3/22/18
 */

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
    }
}