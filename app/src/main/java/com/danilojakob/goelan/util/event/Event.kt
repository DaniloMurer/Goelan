package com.danilojakob.goelan.util.event

class Event {

    private var observers: MutableList<Observer> = mutableListOf()

    fun addObserver(observer: Observer) {
        observers.toMutableList().add(observer)
    }

    fun notifyObservers() {
        this.observers.forEach { o ->
            o.update()
        }
    }
}