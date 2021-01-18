package com.danilojakob.goelan.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.danilojakob.goelan.R
import com.danilojakob.goelan.data.GameData
import com.danilojakob.goelan.service.GameService
import com.danilojakob.goelan.util.event.Observer
import com.danilojakob.goelan.util.views.AbstractView

/**
 * Round Activity
 *
 * Service binding from: https://developer.android.com/guide/components/bound-services#kotlin
 */
class RoundActivity : AppCompatActivity(), Observer {

    private lateinit var gameService: GameService
    private var isServiceBound: Boolean = false

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as GameService.LocalBinder
            gameService = binder.getService()
            isServiceBound = true
            gameService.changeRound()
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            isServiceBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        // Bind to GameService
        Intent(this, GameService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        GameData.addObserver(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_round)
        Log.i("DEBUG", "GameData.players = " + GameData.players.size)
    }

    override fun onStop() {
        super.onStop()
        unbindService(this.connection)
        isServiceBound = false
    }

    /**
     * Remove all elements from Layout
     */
    private fun clearLayout() {
        val mainLayout = findViewById<ConstraintLayout>(R.id.main_layout)
        mainLayout.removeAllViewsInLayout()
    }

    /**
     * Set layout for the RoundActivity
     * @param abstractView [AbstractView] view to set
     */
    private fun setLayout(abstractView: AbstractView) {
        val layout = findViewById<LinearLayout>(R.id.main_layout)
        layout.addView(abstractView.getLayout())
    }

    override fun update() {
        this.setLayout(this.gameService.changeRound()!!)
    }
}