package com.willpease.declarativerenderer.renderer

/**
 * @author Will Pease
 * @date 3/10/18
 */

class PropertySetting {

    var method: Any? = null

    private var value: Array<Any>? = null

    fun<T, A> set(method: (T, A) -> Unit, a: A): PropertySetting {
        this.method = method
        this.value = arrayOf(a as Any)
        return this
    }

    fun<T, A, B> set(method: (T, A, B) -> Unit, a: A, b: B): PropertySetting {
        this.method = method
        this.value = arrayOf(a as Any, b as Any)
        return this
    }

    fun<T, A, B, C> set(method: (T, A, B, C) -> Unit, a: A, b: B, c: C): PropertySetting {
        this.method = method
        this.value = arrayOf(a as Any, b as Any, c as Any)
        return this
    }

    fun applyTo(instance: Any) {
        value?.let {value ->
            when(value.size) {
                1 -> method?.let{method -> (method as (Any, Any) -> Unit)(instance, value[0])}
                2 -> method?.let{method -> (method as (Any, Any, Any) -> Unit)(instance, value[0], value[1])}
                3 -> method?.let{method -> (method as (Any, Any, Any, Any) -> Unit)(instance, value[0], value[1], value[2])}
                else -> {}
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is PropertySetting
                && method == other.method
                && (other.value?.let{otherVal->value?.contentEquals<Any>(otherVal)} ?: false)
    }
}