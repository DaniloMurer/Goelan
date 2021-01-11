package com.danilojakob.goelan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danilojakob.goelan.data.Player

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val player = Player("Danilo Jakob", 0, 0)
    }
}