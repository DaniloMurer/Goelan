package com.danilojakob.goelan.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.danilojakob.goelan.data.Round

class GameService : Service() {

    private var rounds: Int = 0

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    /**
     * Change current round
     */
    private fun changeRound(): Round? {
        return null;
    }

    /**
     * Get a random number between 1 and 2
     */
    private fun getRandomNumber(): Int = ((Math.random() * 2) + 1) as Int

    /**
     * Get the current amount of rounds left
     */
    fun getRounds(): Int = this.rounds
}