package com.willpease.declarativerenderer.renderer

/**
 * @author Will Pease
 * @date 3/10/18
 */

fun<T> p(method: (T) -> Unit): Prop {
    return Prop0(method)
}

fun<T, A> p(method: (T, A) -> Unit, a: A): Prop {
    return Prop1(method, a)
}

fun<T, A, B> p(method: (T, A, B) -> Unit, a: A, b: B): Prop {
    return Prop2(method, a, b)
}

fun<T, A, B, C> p(method: (T, A, B, C) -> Unit, a: A, b: B, c: C): Prop {
    return Prop3(method, a, b, c)
}

fun<T, A, B, C, D> p(method: (T, A, B, C, D) -> Unit, a: A, b: B, c: C, d: D): Prop {
    return Prop4(method, a, b, c, d)
}

interface Prop {
    val method: Any
    fun applyTo(instance: Any)
}

class Prop0<in T>(override val method: (T) -> Unit) : Prop {
    override fun applyTo(instance: Any) {
        method(instance as T)
    }
    override fun equals(other: Any?): Boolean {
        return other is Prop0<*> && method == other.method
    }
}

class Prop1<in T, A>(override val method: (T, A) -> Unit, val a: A) : Prop {
    override fun applyTo(instance: Any) {
        method(instance as T, a)
    }
    override fun equals(other: Any?): Boolean {
        return other is Prop1<*, *> && method == other.method && a == other.a
    }
}

class Prop2<in T, A, B>(override val method: (T, A, B) -> Unit, val a: A, val b: B) : Prop {
    override fun applyTo(instance: Any) {
        method(instance as T, a, b)
    }
    override fun equals(other: Any?): Boolean {
        return other is Prop2<*, *, *> && method == other.method && a == other.a && b == other.b
    }
}

class Prop3<in T, A, B, C>(override val method: (T, A, B, C) -> Unit, val a: A, val b: B, val c: C) : Prop {
    override fun applyTo(instance: Any) {
        method(instance as T, a, b, c)
    }
    override fun equals(other: Any?): Boolean {
        return other is Prop3<*, *, *, *> && method == other.method
                && a == other.a && b == other.b && c == other.c
    }
}

class Prop4<in T, A, B, C, D>(override val method: (T, A, B, C, D) -> Unit, val a: A, val b: B, val c: C, val d: D) : Prop {
    override fun applyTo(instance: Any) {
        method(instance as T, a, b, c, d)
    }
    override fun equals(other: Any?): Boolean {
        return other is Prop4<*, *, *, *, *> && method == other.method
                && a == other.a && b == other.b && c == other.c && d == other.d
    }
}
