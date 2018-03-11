package com.willpease.declarativerenderer

import android.content.Context
import android.view.View

/**
 * Created by willpease on 3/8/18.
 */

class Element(val ctor: (Context) -> View, props: Array<PropertySetting> = emptyArray(), val children: Array<Element> = emptyArray()) {

    val props: Map<Any, PropertySetting> = mapOf( *props.map { it.method as Any to it }.toTypedArray())

}