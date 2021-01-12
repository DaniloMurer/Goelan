package com.danilojakob.goelan.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import com.danilojakob.goelan.data.GameData
import com.danilojakob.goelan.data.Round

class GameService : Service() {

    private val binder = LocalBinder()
    private lateinit var apiService: ApiService

    inner class LocalBinder: Binder() {
        fun getService(): GameService = this@GameService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    /**
     * Change current round
     */
    fun changeRound(): Round? {
        this.apiService = ApiService(applicationContext)
        // Return null object if there are no rounds left
        if (GameData.rounds == 0) return null
        val randomQuestion = this.apiService.getQuestion()
        if (getRandomNumber() == 1) {
            // TODO: Return round with random question
        } else {
            // TODO: Return round with mini-game
        }

        // Decrement the rounds left after changing round
        GameData.rounds--
        return null;
    }

    /**
     * Get a random number between 1 and 2
     */
    private fun getRandomNumber(): Int = ((Math.random() * 2) + 1).toInt()
}