package com.example.sportz

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sportz.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MatchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.fetchMatchDetails(1)
        viewModel.matchDetails.observe(this) { match ->
            try {
                if (match != null && match.matchDetails != null) {
                    binding.tvMatchInfo.text = "${match.matchDetails.team1} vs ${match.matchDetails.team2}\n" +
                            "Venue: ${match.matchDetails.venue}\nDate: ${match.matchDetails.matchDateTime}"
                } else {
                    binding.tvMatchInfo.text = "Match details not available."
                }
            } catch (e: Exception) {
                e.printStackTrace()
                binding.tvMatchInfo.text = "Error: Unable to load match details."
            }
        }


        binding.btnViewSquads.setOnClickListener {
            startActivity(Intent(this, SquadsActivity::class.java))
        }
    }
}