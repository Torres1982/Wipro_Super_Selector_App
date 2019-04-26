package com.wipro.day4superselectorapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONObject

class PlayerDetailsActivity: AppCompatActivity() {
    private lateinit var playerImage: ImageView
    private lateinit var playerName: TextView
    private lateinit var playerAge: TextView
    private lateinit var playerCountry: TextView
    private lateinit var playerPosition: TextView
    private lateinit var playersJson: String
    private lateinit var playersJsonArray: JSONArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_details_activity)

        playerImage = findViewById(R.id.image_view_player)
        playerName = findViewById(R.id.text_view_player_name)
        playerAge = findViewById(R.id.text_view_player_age)
        playerCountry = findViewById(R.id.text_view_player_country)
        playerPosition = findViewById(R.id.text_view_player_position)

        var playersArraySize = 0
        val receivePlayerName = intent.getStringExtra("selected_player")
        val receiveImages = intent.getStringArrayExtra("players_images")
        //Toast.makeText(this@PlayerDetailsActivity, "Images: ${receiveImages[0]}", Toast.LENGTH_SHORT).show()

        try {
            playersJson = resources.openRawResource(R.raw.players).bufferedReader().use { it.readText() }

            val playersJsonObject = JSONObject(playersJson)
            playersJsonArray = playersJsonObject.getJSONArray("players")
            playersArraySize = playersJsonArray.length()

        } catch (e: Exception) {
            Toast.makeText(this@PlayerDetailsActivity, "Something went wrong! - $e", Toast.LENGTH_SHORT).show()
        }

        for (i in 0..(playersArraySize - 1)) {
            if (receivePlayerName == playersJsonArray.getJSONObject(i).getString("lastName")) {
                val playerFullName = playersJsonArray.getJSONObject(i).getString("firstName") + " " + receivePlayerName
                val imageString = receiveImages[i].decapitalize()

                playerImage.setImageResource(this.resources.getIdentifier(imageString,"drawable", packageName))
                playerName.text = playerFullName
                playerAge.text = playersJsonArray.getJSONObject(i).getString("age")
                playerCountry.text = playersJsonArray.getJSONObject(i).getString("country")
                playerPosition.text = playersJsonArray.getJSONObject(i).getString("position")
                break
            }
        }
    }
}
