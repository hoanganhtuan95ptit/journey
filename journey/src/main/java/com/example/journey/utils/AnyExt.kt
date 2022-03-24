package com.example.journey.utils

import java.lang.reflect.ParameterizedType


fun Any.allGenericClass() = arrayListOf<Class<*>>().apply {
    allGenericClass(this@allGenericClass.javaClass, this)
}

fun Any.allSuperClass() = arrayListOf<Class<*>>().apply {
    allSuperClass(this@allSuperClass.javaClass, this)
}

fun allGenericClass(clazz: Class<*>, list: ArrayList<Class<*>>) {
    val superClass = clazz.genericSuperclass

    if (superClass !is ParameterizedType){
        return
    }

    superClass.actualTypeArguments.forEach {
        if (it is Class<*> && it != Object::class.java) {
            list.add(it)
        }
    }
}

fun allSuperClass(clazz: Class<*>, list: ArrayList<Class<*>>) {
    var superClass = clazz.genericSuperclass

    if (superClass is ParameterizedType) {
        superClass = superClass.rawType
    }

    if (superClass is Class<*> && superClass != Object::class.java) {

        list.add(superClass)

        allSuperClass(superClass, list)
    }
}