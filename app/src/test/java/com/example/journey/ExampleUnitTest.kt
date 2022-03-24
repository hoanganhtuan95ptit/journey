package com.example.journey

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        getClass(A::class.java, arrayListOf())
    }

    fun getClass(clazz: Class<*>, list: ArrayList<Class<*>>){
        val superClass = clazz.javaClass.superclass

        println(superClass.name)
    }

    open class A()

    open class  B:A()

    class C:B()
}