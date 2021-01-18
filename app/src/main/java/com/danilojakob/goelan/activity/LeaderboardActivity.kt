package com.danilojakob.goelan.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.danilojakob.goelan.R
import com.danilojakob.goelan.data.GameData
import com.danilojakob.goelan.util.ComponentFactory

class LeaderboardActivity : AppCompatActivity() {

    private val componentFactory = ComponentFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        val leaderboardLayout = findViewById<LinearLayout>(R.id.leaderboard_layout)
        val leaderBoardData = GameData.getLeaderBoard()

        for (i in 0 until leaderBoardData.size) {
            val layout = componentFactory.createLinearLayout(LinearLayout.HORIZONTAL, applicationContext)
            val textViewPosition = componentFactory.createTextView("\t\t\t\t${i+1}\t\t\t\t", applicationContext)
            val textViewPlayerName = componentFactory.createTextView("${leaderBoardData[i].name}\t\t\t\t", applicationContext)
            val textViewPlayerPoints = componentFactory.createTextView("${leaderBoardData[i].points}\t\t\t\t", applicationContext)

            layout.addView(textViewPosition)
            layout.addView(textViewPlayerName)
            layout.addView(textViewPlayerPoints)

            leaderboardLayout.addView(layout)
        }
    }
}