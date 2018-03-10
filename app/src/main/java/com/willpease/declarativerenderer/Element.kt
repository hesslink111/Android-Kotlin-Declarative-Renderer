package com.willpease.declarativerenderer

import android.content.Context
import android.view.View

/**
 * Created by willpease on 3/8/18.
 */

class Element {

    val ctor: (Context) -> View
    val props: Map<Any, PropertySetting>
    val children: Array<Element>

    constructor(ctor: (Context) -> View, props: Array<PropertySetting>, children: Array<Element>) {
        this.ctor = ctor
        this.props = mapOf( *props.map { it.method as Any to it }.toTypedArray())
        this.children = children
    }

    constructor(ctor: (Context) -> View, props: Array<PropertySetting>) {
        this.ctor = ctor
        this.props = mapOf( *props.map { it.method as Any to it }.toTypedArray())
        this.children = emptyArray()
    }
}