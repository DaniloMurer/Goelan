package com.danilojakob.goelan.util.views

import android.content.Context
import com.danilojakob.goelan.data.GameData
import com.danilojakob.goelan.data.Question
import com.danilojakob.goelan.service.ApiService
import com.danilojakob.goelan.util.ComponentFactory

class QuestionView(context: Context, orientation: Int) : AbstractView(context, orientation) {

    private val apiService = ApiService(context)
    private var question: Question
    private val componentFactory = ComponentFactory()
    private val context = context

    init {
        this.question = apiService.getQuestion()
        buildLayout()
    }

    override fun buildLayout() {
        this.addChildToLayout(componentFactory.createTextView("The Spitfire originated from a racing plane.", this.context))
        val button = componentFactory.createButton("TRUE", this.context)
        button.setOnClickListener {
            GameData.changeRoundEvent.notifyObservers()
        }
        this.addChildToLayout(button)
    }
}