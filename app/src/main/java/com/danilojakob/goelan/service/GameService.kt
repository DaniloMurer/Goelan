package com.danilojakob.goelan.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.LinearLayout
import com.danilojakob.goelan.data.GameData
import com.danilojakob.goelan.data.Round
import com.danilojakob.goelan.util.views.AbstractView
import com.danilojakob.goelan.util.views.ActuatorGameView
import com.danilojakob.goelan.util.views.OrientationGameView
import com.danilojakob.goelan.util.views.QuestionView

class GameService() : Service() {

    private val binder = LocalBinder()
    private lateinit var currentView: AbstractView
    private lateinit var context: Context

    inner class LocalBinder: Binder() {
        fun getService(): GameService = this@GameService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    /**
     * Change current round
     */
    fun changeRound(): AbstractView? {
        // Return null object if there are no rounds left
        if (GameData.rounds == 0) return null
        val randomNumber = getRandomNumber()
        if (randomNumber == 1) {
            // Decrement the rounds left after changing round
            GameData.rounds--
            Log.i("DEBUG", "Starting Question View")
            return QuestionView(context, LinearLayout.VERTICAL)
        } else if (randomNumber == 2) {
            // Decrement the rounds left after changing round
            GameData.rounds--
            return OrientationGameView(context, LinearLayout.VERTICAL)
        } else {
            // Decrement the rounds left after changing round
            GameData.rounds--
            return ActuatorGameView(context, LinearLayout.VERTICAL)
        }
    }

    /**
     * Get a random number between 1 and 2
     */
    private fun getRandomNumber(): Int = ((Math.random() * 3) + 1).toInt()

    fun setContext(context: Context) {
        this.context = context
    }
}