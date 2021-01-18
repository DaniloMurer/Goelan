package com.danilojakob.goelan.data

import com.danilojakob.goelan.util.event.ActuatorPressedEvent
import com.danilojakob.goelan.util.event.ChangeRoundEvent
import com.danilojakob.goelan.util.event.Observer
import com.danilojakob.goelan.util.event.SensorDataEvent

object GameData {
    var players: MutableList<Player> = mutableListOf()
    var rounds: Int = 0
    var currentPlayer: String = ""
    var changeRoundEvent: ChangeRoundEvent = ChangeRoundEvent()
    var actuatorPressedEvent: ActuatorPressedEvent = ActuatorPressedEvent()
    var sensorDataEvent: SensorDataEvent = SensorDataEvent()
    var orientationGameFinished: Boolean = false

    private val wrongAnswerCaption = listOf("Wrong answer, seriously?",
                                                    "Already that wasted?",
                                                    "You're really a moron aren't you?",
                                                    "Skipped primary school?",
                                                    "It seems to be, that drinking affects your ability to think",
                                                    "C'mon that was easy, are you a special education kid?")

    private val correctAnswerCaption = listOf("Correct answer!", "Do we have a prodigy among us?",
                                              "So you surely weren't the class clown huh",
                                              "I'm starting to worry that you have no friends",
                                              "Only 2% of humanity knows the answer to that question," +
                                                      " I'm a computer I know what I'm talking about",
                                              "You're truly special")

    /**
     * Sort the Player list based on the points and return it as a MutableList
     */
    fun getLeaderBoard(): MutableList<Player> {
        this.players.sortWith(Comparator { o1, o2 ->
            when {
                o1?.points!! > o2?.points!! -> 1
                o1.points == o2.points -> 0
                else -> -1
            }
        })
        return this.players
    }

    /**
     * Add a point to the current playing player
     */
    fun addPoint() {
        // Get current player
        val player = this.players.find { player -> player.name == currentPlayer }

        if (player != null) {
            // Increment points
            player.points++
        }
    }

    /**
     * Get the next player to play
     */
    fun getNextPlayer() {
        val player = this.players.find { player -> player.name == currentPlayer }

        if (player != null) {
            var index = players.indexOf(player)
            // Check if the current player is the last from the list
            if (++index == players.size - 1) {
                // Restart with the first player
                val newPlayer = this.players[0]
                this.currentPlayer = newPlayer.name
            } else {
                // Go to the next player
                val newPlayer = this.players[++index]
                this.currentPlayer = newPlayer.name
            }
        }
    }

    /**
     * Get a random caption for wrong answers
     */
    fun getWrongAnswerCaption(): String {
        val randomNumber = (Math.random() * this.wrongAnswerCaption.size).toInt()
        return wrongAnswerCaption[randomNumber]
    }

    /**
     * Get a random caption for correct answers
     */
    fun getCorrectAnswerCaption(): String {
        val randomNumber = (Math.random() * this.correctAnswerCaption.size).toInt()
        return correctAnswerCaption[randomNumber]
    }

    /**
     * Add observer to the changeRoundEvent
     */
    fun addObserver(observer: Observer) {
        this.changeRoundEvent.addObserver(observer)
    }

    /**
     * Fire the ChangeRound event
     */
    fun fireChangeRoundEvent() {
        this.changeRoundEvent.notifyObservers()
    }
}
