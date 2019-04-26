package com.wipro.day4superselectorapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class PlayersActivity: AppCompatActivity() {
    private lateinit var playersListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players)

        playersListView = this.findViewById(R.id.list_view_selected_players)

        val receivePlayers = intent.getStringArrayListExtra("selected_players") ?: return
        val receiveImages = intent.getStringArrayExtra("array_players_images")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, receivePlayers)
        playersListView.adapter = adapter

        playersListView.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val selectedPlayer = parent.getItemAtPosition(position) as String
            //Toast.makeText(this@PlayersActivity, selectedItem, Toast.LENGTH_SHORT).show()
            // Send Intent to a PlayerDetailsActivity class with selected player
            val intentSendPlayerDetails = Intent(this, PlayerDetailsActivity::class.java).apply {
                putExtra("selected_player", selectedPlayer)
                putExtra("players_images", receiveImages)
            }
            startActivity(intentSendPlayerDetails)
        }
    }
}
