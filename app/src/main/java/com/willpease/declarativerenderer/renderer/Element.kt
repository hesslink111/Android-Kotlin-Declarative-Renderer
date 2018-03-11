package com.willpease.declarativerenderer.renderer

import android.content.Context
import android.view.View

/**
 * @author Will Pease
 * @date 3/10/18
 */

class Element(val ctor: (Context) -> View, props: Array<PropertySetting> = emptyArray(), val children: Array<Element> = emptyArray()) {

    val props: Map<Any, PropertySetting> = mapOf( *props.map { it.method as Any to it }.toTypedArray())

}