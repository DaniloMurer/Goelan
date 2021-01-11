package com.danilojakob.goelan.data

import java.time.LocalDateTime

class MiniGame() {

    private var x: Int = 0
    private var y: Int = 0
    private var z: Int = 0
    private var isActuatorPressed: Boolean = false

    private var gameStarted: LocalDateTime = LocalDateTime.now()

    /**
     * Get Accelerometer Data
     */
    fun getSensorData() {

    }

    /**
     * Event when the actuator is pressed
     */
    fun actuatorPressed() {

    }

    fun startGame() {
        this.gameStarted = LocalDateTime.now()
    }
}