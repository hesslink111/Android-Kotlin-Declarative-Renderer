package com.willpease.declarativerenderer.renderer

import android.content.Context
import android.view.View

/**
 * @author Will Pease
 * @date 3/10/18
 */

typealias E<T> = Element<T>

class Element<out T: View>(
        val ctor: (Context) -> T, props: Array<Prop> = emptyArray(),
        var children: Array<Element<View>> = emptyArray(),
        var ref: (e: Any)->Unit = {}) {

    var props: Map<Any, Prop> = mapOf( *props.map { it.method to it }.toTypedArray())

}