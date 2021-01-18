package com.danilojakob.goelan.util.views

import android.content.Context
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
        this.addChildToLayout(componentFactory.createTextView(question.results.getJSONObject(0).getString("question"), this.context))

        val buttonTrue = componentFactory.createButton("TRUE", this.context)
        val buttonFalse = componentFactory.createButton("FALSE", this.context)
        val buttonLayout = componentFactory.createLinearLayout(LinearLayout.HORIZONTAL, this.context)
        buttonTrue.setOnClickListener {
            if (this.correctAnswer == "True") {
                Toast.makeText(this.context, GameData.getCorrectAnswerCaption(), Toast.LENGTH_LONG).show()
                // If the correct answer was false, add a point to the current playing player
                GameData.addPoint()
                // Get the next player as currentPlayer
                GameData.getNextPlayer()
                // Change round
                GameData.changeRoundEvent.notifyObservers()
            } else {
                Toast.makeText(this.context, GameData.getWrongAnswerCaption(), Toast.LENGTH_LONG).show()
                // Get the next player as currentPlayer
                GameData.getNextPlayer()
                // Change round
                GameData.changeRoundEvent.notifyObservers()
            }
        }

        buttonFalse.setOnClickListener {
            if (this.correctAnswer == "False") {
                Toast.makeText(this.context, GameData.getCorrectAnswerCaption(), Toast.LENGTH_LONG).show()
                // If the correct answer was false, add a point to the current playing player
                GameData.addPoint()
                // Get the next player as currentPlayer
                GameData.getNextPlayer()
                // Change round
                GameData.changeRoundEvent.notifyObservers()
            } else {
                Toast.makeText(this.context, GameData.getWrongAnswerCaption(), Toast.LENGTH_LONG).show()
                // Get the next player as currentPlayer
                GameData.getNextPlayer()
                // Change round
                GameData.changeRoundEvent.notifyObservers()
            }
        }
        buttonLayout.addView(buttonTrue)
        buttonLayout.addView(buttonFalse)
        this.addChildToLayout(buttonLayout)
    }
}