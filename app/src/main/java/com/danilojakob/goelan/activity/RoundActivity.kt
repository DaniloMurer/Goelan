package com.danilojakob.goelan.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import androidx.constraintlayout.widget.ConstraintLayout
import com.danilojakob.goelan.R
import com.danilojakob.goelan.data.GameData
import com.danilojakob.goelan.data.Player
import com.danilojakob.goelan.service.GameService

/**
 * Round Activity
 *
 * Service binding from: https://developer.android.com/guide/components/bound-services#kotlin
 */
class RoundActivity : AppCompatActivity() {

    private lateinit var gameService: GameService
    private var isServiceBound: Boolean = false

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as GameService.LocalBinder
            gameService = binder.getService()
            isServiceBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            isServiceBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        // Bind to LocalService
        Intent(this, GameService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_round)
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
}