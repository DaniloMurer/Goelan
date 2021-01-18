package com.danilojakob.goelan.util.event

interface DataObserver {
    fun update(x: Float, y: Float, z: Float)
}