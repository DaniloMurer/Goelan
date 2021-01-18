package com.danilojakob.goelan.util.views

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.danilojakob.goelan.data.GameData
import com.danilojakob.goelan.util.ComponentFactory
import com.danilojakob.goelan.util.event.Observer
import java.time.LocalDateTime

/**
 * UI and logic for the actuator MiniGame
 */
class ActuatorGameView(private val context: Context, orientation: Int): AbstractView(context, orientation), Observer {

    private lateinit var colorChanged: String
    private var actuatorPressed: String? = null
    private val componentFactory = ComponentFactory()
    private lateinit var textView: TextView

    init {
        GameData.actuatorPressedEvent.addObserver(this)
        this.buildLayout()
    }

    override fun buildLayout() {
        val nextPlayerText = this.componentFactory.createTextView("${GameData.currentPlayer} it's your turn", this.context)
        val button = this.componentFactory.createButton("START", this.context)
        button.setOnClickListener(this::triggerColorChange)
        this.addChildToLayout(button)
        this.textView = this.componentFactory.createTextView("When the current text is 'NOW' press the volume button!", this.context)
        this.addChildToLayout(nextPlayerText)
        this.addChildToLayout(this.textView)
    }

    private fun triggerColorChange(view: View) {
        Thread.sleep(randomDelay().toLong())
        this.textView.text = "NOW"
        val dateTime = LocalDateTime.now()
        this.colorChanged = "${dateTime.second}.${dateTime.nano}"
    }

    override fun update() {
        val dateTime = LocalDateTime.now()
        this.actuatorPressed = "${dateTime.second}.${dateTime.nano}"


        if (this.actuatorPressed!!.toDouble() < this.colorChanged.toDouble()) {
            Toast.makeText(this.context, "You've Lost!", Toast.LENGTH_LONG).show()
            GameData.getNextPlayer()
            GameData.changeRoundEvent.notifyObservers()
        } else if (this.colorChanged.toDouble() == this.actuatorPressed!!.toDouble()) {
            Toast.makeText(this.context, "You've Won!", Toast.LENGTH_LONG).show()
            GameData.addPoint()
            GameData.getNextPlayer()
            GameData.changeRoundEvent.notifyObservers()
        } else if (this.actuatorPressed!!.toDouble() - this.colorChanged.toDouble() <= 1.0000000) {
            Toast.makeText(this.context, "You've Won!", Toast.LENGTH_LONG).show()
            GameData.addPoint()
            GameData.getNextPlayer()
            GameData.changeRoundEvent.notifyObservers()
        } else {
            Toast.makeText(this.context, "You've Lost!", Toast.LENGTH_LONG).show()
            GameData.getNextPlayer()
            GameData.changeRoundEvent.notifyObservers()
        }
    }

    private fun randomDelay() = ((Math.random() * 5000) + 1).toInt()
}