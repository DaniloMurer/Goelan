package com.danilojakob.goelan.util.event

class ActuatorPressedEvent {
    private var observers: MutableList<Observer> = mutableListOf()

    fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    fun notifyObservers() {
        this.observers.forEach { o ->
            o.update()
        }
    }
}