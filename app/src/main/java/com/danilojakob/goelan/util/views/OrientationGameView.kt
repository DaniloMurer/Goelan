package com.danilojakob.goelan.util.views

import android.content.Context
import android.widget.TextView
import com.danilojakob.goelan.data.GameData
import com.danilojakob.goelan.util.ComponentFactory
import com.danilojakob.goelan.util.event.DataObserver
import java.time.LocalDateTime

/**
 * UI and Logic for the orientation MiniGame
 */
class OrientationGameView(private val context: Context, orientation: Int): AbstractView(context, orientation), DataObserver {

    private lateinit var gameStarted: String
    private lateinit var gameFinished: String
    private val componentFactory = ComponentFactory()
    private lateinit var textView: TextView

    init {
        GameData.sensorDataEvent.addObserver(this)
        buildLayout()
    }
    override fun buildLayout() {
        val nextPlayerText = this.componentFactory.createTextView("${GameData.currentPlayer} it's your turn", context)
        val button = this.componentFactory.createButton("START", this.context)
        button.setOnClickListener {
            val dateTime = LocalDateTime.now()
            this.gameStarted = "${dateTime.second}.${dateTime.nano}"
        }
        this.textView = this.componentFactory.createTextView("After Starting you will see the sensor data, get everything to 0!", this.context)
        this.addChildToLayout(nextPlayerText)
        this.addChildToLayout(button)
        this.addChildToLayout(textView)
    }

    override fun update(x: Float, y: Float, z: Float) {
        // Show Current data
        val stringBuilder = StringBuilder()
        stringBuilder.append("X: $x\n")
        stringBuilder.append("Y: $y\n")
        stringBuilder.append("Z: $z\n")

        this.textView.text = stringBuilder.toString()

        if (x <= 0.2f && z <= 0.2f) {
            val dateTime = LocalDateTime.now()
            this.gameFinished = "${dateTime.second}.${dateTime.nano}"
            checkWinner()
        }
    }

    private fun checkWinner() {
        if (gameFinished.toDouble() - gameStarted.toDouble() < 30) {
            GameData.orientationGameFinished = true
            GameData.addPoint()
            GameData.getNextPlayer()
            GameData.changeRoundEvent.notifyObservers()
        }
    }
}