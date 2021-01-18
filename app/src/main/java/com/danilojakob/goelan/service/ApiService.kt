package com.danilojakob.goelan.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.danilojakob.goelan.data.Question
import com.danilojakob.goelan.util.views.AbstractView
import org.json.JSONArray
import org.json.JSONObject

class ApiService(context: Context) {

    private val API_URL = "https://opentdb.com/api.php?amount=1&type=boolean"
    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)
    private var question = Question(0, JSONArray())
    fun createQuestion(abstractView: AbstractView) {
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, API_URL, null, { response ->
            this.question.response_code = response.getInt("response_code")
            this.question.results = response.getJSONArray("results")
            abstractView.buildLayout()
        }, { error ->
            question = Question(0, JSONArray())
        })
        this.requestQueue.add(jsonObjectRequest)
    }

    fun getQuestion(): Question {
        return this.question
    }
}