package com.danilojakob.goelan.util.event

import com.danilojakob.goelan.data.GameData

class SensorDataEvent {
    private var observers: MutableList<DataObserver> = mutableListOf()

    fun addObserver(observer: DataObserver) {
        observers.add(observer)
    }

    fun notifyObservers(x: Float, y: Float, z: Float) {
        if (GameData.orientationGameFinished) {
            return
        }
        this.observers.forEach { o ->
            o.update(x, y, z)
        }
    }
}