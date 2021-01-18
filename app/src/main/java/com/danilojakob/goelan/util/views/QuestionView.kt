package com.danilojakob.goelan.util.views

import android.content.Context
import android.os.CountDownTimer
import android.widget.LinearLayout
import android.widget.Toast
import com.danilojakob.goelan.data.GameData
import com.danilojakob.goelan.data.Question
import com.danilojakob.goelan.service.ApiService
import com.danilojakob.goelan.util.ComponentFactory

class QuestionView(private val context: Context, orientation: Int) : AbstractView(context, orientation) {

    private val apiService = ApiService(context)
    private lateinit var question: Question
    private val componentFactory = ComponentFactory()
    private lateinit var correctAnswer: String

    init {
        apiService.createQuestion(this)
    }

    override fun buildLayout() {
        this.question = apiService.getQuestion()
        this.correctAnswer = question.results.getJSONObject(0).getString("correct_answer")
        val nextPlayerText = componentFactory.createTextView("${GameData.currentPlayer} it's your turn!", context)
        this.addChildToLayout(nextPlayerText)
        this.addChildToLayout(componentFactory.createTextView(question.results.getJSONObject(0).getString("question"), this.context))

        val buttonTrue = componentFactory.createButton("TRUE", this.context)
        val buttonFalse = componentFactory.createButton("FALSE", this.context)
        val buttonLayout = componentFactory.createLinearLayout(LinearLayout.HORIZONTAL, this.context)
        buttonTrue.setOnClickListener {
            if (this.correctAnswer == "True") {
                val timer = object: CountDownTimer(3000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        // DO NOTHING
                    }
                    override fun onFinish() {
                        // If the correct answer was false, add a point to the current playing player
                        GameData.addPoint()
                        // Get the next player as currentPlayer
                        GameData.getNextPlayer()
                        // Change round
                        GameData.changeRoundEvent.notifyObservers()
                    }
                }
                Toast.makeText(this.context, GameData.getCorrectAnswerCaption(), Toast.LENGTH_LONG).show()
                timer.start()
            } else {
                val timer = object: CountDownTimer(3000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        // DO NOTHING
                    }
                    override fun onFinish() {
                        // Get the next player as currentPlayer
                        GameData.getNextPlayer()
                        // Change round
                        GameData.changeRoundEvent.notifyObservers()
                    }
                }
                Toast.makeText(this.context, GameData.getWrongAnswerCaption(), Toast.LENGTH_LONG).show()
                timer.start()
            }
        }

        buttonFalse.setOnClickListener {
            if (this.correctAnswer == "False") {
                Toast.makeText(context, GameData.getCorrectAnswerCaption(), Toast.LENGTH_LONG).show()
                val timer = object: CountDownTimer(3000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        // DO NOTHING
                    }
                    override fun onFinish() {
                        // If the correct answer was false, add a point to the current playing player
                        GameData.addPoint()
                        // Get the next player as currentPlayer
                        GameData.getNextPlayer()
                        // Change round
                        GameData.changeRoundEvent.notifyObservers()
                    }
                }
                timer.start()
            } else {
                Toast.makeText(context, GameData.getWrongAnswerCaption(), Toast.LENGTH_LONG).show()
                val timer = object: CountDownTimer(3000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        // DO NOTHING
                    }
                    override fun onFinish() {
                        // Get the next player as currentPlayer
                        GameData.getNextPlayer()
                        // Change round
                        GameData.changeRoundEvent.notifyObservers()
                    }
                }
                timer.start()
            }
        }
        buttonLayout.addView(buttonTrue)
        buttonLayout.addView(buttonFalse)
        this.addChildToLayout(buttonLayout)
    }
}