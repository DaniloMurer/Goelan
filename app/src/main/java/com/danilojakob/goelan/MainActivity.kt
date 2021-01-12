package com.danilojakob.goelan

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.danilojakob.goelan.activity.RoundActivity
import com.danilojakob.goelan.data.GameData
import com.danilojakob.goelan.data.Player
import com.danilojakob.goelan.util.ComponentFactory

class MainActivity : AppCompatActivity() {

    private val AMOUNT_OF_ROUNDS: Int = 5
    private val componentFactory = ComponentFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addPlayerButton = findViewById<Button>(R.id.add_player_button)
        addPlayerButton.setOnClickListener(this::onAddPlayer)
        val startGameButton = findViewById<Button>(R.id.start_game_button)
        startGameButton.setOnClickListener(this::onStartGame)
    }

    /**
     * Method called when add_player_button is pressed
     */
    private fun onAddPlayer(view: View) {

        // Get Player Name TextBox
        val playerNameTextBox = findViewById<EditText>(R.id.name_textbox)
        // Create new player based on the player name
        val player = Player(playerNameTextBox.text.toString())
        // Add Player to the GameData
        GameData.players.add(player)

        // Add Player name to the VerticalLayout
        val playerListLayout = findViewById<LinearLayout>(R.id.playerlist_layout)
        val horizontalLayout = componentFactory.createLinearLayout(LinearLayout.HORIZONTAL, applicationContext)
        horizontalLayout.addView(componentFactory.createTextView(player.name, applicationContext))
        horizontalLayout.addView(componentFactory.createButton("X", applicationContext))
        playerListLayout.addView(horizontalLayout)
    }

    /**
     * Method called when start_game_button is pressed
     */
    private fun onStartGame(view: View) {
        // Get the amount_rounds_textbox
        val roundsTextBox = findViewById<EditText>(R.id.amount_rounds_textbox)
        // Check if the amount_rounds_textbox has a value
        if (roundsTextBox.text.toString() != "") {
            // Set the value from the textbox to the GameData
            GameData.rounds = roundsTextBox.text.toString().toInt()
        } else {
            // Set the default amount of rounds
            GameData.rounds = AMOUNT_OF_ROUNDS
        }
        // Create new intent for starting RoundActivity
        val roundActivityIntent = Intent(applicationContext, RoundActivity::class.java)
        // Start RoundActivity with the intent
        startActivity(roundActivityIntent)

    }
}