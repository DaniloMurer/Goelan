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
import org.json.JSONArray
import org.json.JSONObject

class ApiService(context: Context) {

    private val API_URL = "https://opentdb.com/api.php?amount=1&type=boolean"
    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun getQuestion(): Question {
        var question = Question(0, JSONArray())
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, API_URL, null, { response ->
            question.response_code = response.getInt("response_code")
            question.results = response.getJSONArray("results")
        }, { error ->
            question = Question(0, JSONArray())
        })
        this.requestQueue.add(jsonObjectRequest)
        return question
    }
}