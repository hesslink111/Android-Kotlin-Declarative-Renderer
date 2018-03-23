package com.willpease.declarativerenderer.renderer

import android.content.Context
import android.view.View
import android.widget.RelativeLayout

/**
 * @author Will Pease
 * @date 3/20/18
 */

inline fun relativeLayoutParams(w: Int, h: Int, init: RelativeLayout.LayoutParams.() -> Unit = {}): RelativeLayout.LayoutParams {
    val lp = RelativeLayoutParams(w, h)
    lp.init()
    return lp
}

class RelativeLayoutParams(w: Int, h: Int): RelativeLayout.LayoutParams(w, h) {
    override fun equals(other: Any?): Boolean {
        return other is RelativeLayoutParams && rules.contentEquals(other.rules)
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

//inline infix fun<T: View> ((Context)->T).elem(init: E<T>.() -> Unit): E<T> {
//    val e = Element(this)
//    e.init()
//    return e
//}
//
//inline fun<T: View> E<T>.props(init: Properties<T>.() -> Unit) {
//    val properties = Properties<T>()
//    properties.init()
//    this.props = properties.propsMap
//}
//
//class Properties<T> {
//
//    val propsMap: MutableMap<Any, Prop> = mutableMapOf()
//
//    inline infix fun<A> ((t: T, a: A)->Unit).toVal(a: A) {
//        propsMap[this] = p(this, a)
//    }
//
//    inline infix fun<A, B> ((t: T, a: A, b: B)->Unit).toVal(value: Tuple2<A, B>) {
//        propsMap[this] = p(this, value._1, value._2)
//    }
//
//    inline infix fun<A, B, C> ((t: T, a: A, b: B, c: C)->Unit).toVal(value: Tuple3<A, B, C>) {
//        propsMap[this] = p(this, value._1, value._2, value._3)
//    }
//
//    inline infix fun<A, B, C, D> ((t: T, a: A, b: B, c: C, d: D)->Unit).toVal(value: Tuple4<A, B, C, D>) {
//        propsMap[this] = p(this, value._1, value._2, value._3, value._4)
//    }
//}
