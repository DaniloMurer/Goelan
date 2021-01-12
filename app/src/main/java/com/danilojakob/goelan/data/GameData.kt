package com.danilojakob.goelan.data

object GameData {
    var players: MutableList<Player> = mutableListOf()
    var rounds: Int = 0
    var currentPlayer: String = ""

    /**
     * Sort the Player list based on the points and return it as a MutableList
     */
    fun getLeaderBoard(): MutableList<Player> {
        this.players.sortWith(Comparator { o1, o2 ->
            when {
                o1?.points!! > o2?.points!! -> 1
                o1.points == o2.points -> 0
                else -> -1
            }
        })
        return this.players
    }

    /**
     * Add a point to the current playing player
     */
    fun addPoint() {
        // Get current player
        val player = this.players.find { player -> player.name == currentPlayer }

        if (player != null) {
            // Increment points
            player.points++
        }
    }

    /**
     * Get the next player to play
     */
    fun getNextPlayer() {
        val player = this.players.find { player -> player.name == currentPlayer }

        if (player != null) {
            var index = players.indexOf(player)
            // Check if the current player is the last from the list
            if (++index == players.size - 1) {
                // Restart with the first player
                val newPlayer = this.players[0]
                this.currentPlayer = newPlayer.name
            } else {
                // Go to the next player
                val newPlayer = this.players[++index]
                this.currentPlayer = newPlayer.name
            }
        }
    }
}
