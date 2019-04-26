package com.wipro.day4superselectorapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private lateinit var playersListView: ListView
    private lateinit var sendPlayersButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playersListView = findViewById(R.id.list_view_players)
        playersListView.removeAllViewsInLayout()
        sendPlayersButton = findViewById(R.id.button_send_players)

        val playersListArray = arrayOf("Messi", "Dembele", "Suarez", "Coutinho", "Boateng", "Melo", "Vidal", "Pique", "Rakitic", "Stegen", "Umtiti", "Busquets", "Alba", "Rafinha", "Cillessen")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, playersListArray)

        playersListView.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        playersListView.adapter = adapter

        // Assign Listener for the Button to send an Intent
        sendPlayersButton.setOnClickListener {
            val selectedPlayersArray: ArrayList<String> = ArrayList()

            for (i in 0 until playersListView.count) {
                if (playersListView.isItemChecked(i)) {
                    selectedPlayersArray.add(playersListView.getItemAtPosition(i).toString())
                }
            }

            // Send Intent to a PlayersActivity class only when 11 players selected
            if (selectedPlayersArray.size == 11) {
                val intentSendPlayers = Intent(this, PlayersActivity::class.java).apply {
                    putExtra("selected_players", selectedPlayersArray)
                    putExtra("array_players_images", playersListArray)
                }
                startActivity(intentSendPlayers)
            } else {
                Toast.makeText(this@MainActivity, "You must select 11 players! Selected ${selectedPlayersArray.size} players!", Toast.LENGTH_LONG).show()
            }
        }
    }
}