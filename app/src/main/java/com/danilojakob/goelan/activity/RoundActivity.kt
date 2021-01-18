package com.danilojakob.goelan.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
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
class RoundActivity : AppCompatActivity(), Observer, SensorEventListener {

    private lateinit var gameService: GameService
    private var isServiceBound: Boolean = false
    private var sensorManager: SensorManager? = null
    private var sensor: Sensor? = null

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as GameService.LocalBinder
            gameService = binder.getService()
            isServiceBound = true
            gameService.setContext(applicationContext)
            update()
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            isServiceBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        // Bind to GameService
        GameData.addObserver(this)
        this.sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        this.sensor = this.sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        this.sensorManager!!.registerListener(this, this.sensor, SensorManager.SENSOR_DELAY_NORMAL)
        Intent(this, GameService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            GameData.actuatorPressedEvent.notifyObservers()
            return true
        }
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            GameData.actuatorPressedEvent.notifyObservers()
            return true
        }
        return false
    }

    /**
     * Set layout for the RoundActivity
     * @param abstractView [AbstractView] view to set
     */
    private fun setLayout(abstractView: AbstractView) {
        val layout = findViewById<ConstraintLayout>(R.id.main_layout)
        // First remove all Views from layout
        layout.removeAllViews()
        // Add view to layout
        layout.addView(abstractView.getLayout())
    }

    override fun update() {
        this.setLayout(this.gameService.changeRound()!!)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val x = event!!.values[0]
        val y = event.values[1]
        val z = event.values[2]
        if (GameData.orientationGameFinished) {
            this.sensor = null
            this.sensorManager = null
        }
        GameData.sensorDataEvent.notifyObservers(x, y, z)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Noting happens here
    }
}