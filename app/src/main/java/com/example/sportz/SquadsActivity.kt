package com.example.sportz

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportz.databinding.ActivitySquadsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SquadsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySquadsBinding
    private val viewModel: MatchViewModel by viewModels()
    private var allPlayers = listOf<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySquadsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSpinner()

        viewModel.fetchMatchDetails(1) // Fetch data from API 1
        viewModel.matchDetails.observe(this) { match ->
            try {
                if (match != null) {
                    allPlayers = match.teams.values.flatMap { it.players }
                    updateRecyclerView(allPlayers)
                }
            }catch (e: Exception) {
                e.printStackTrace()

            }

        }
    }

    private fun setupRecyclerView() {
        binding.rvPlayers.layoutManager = LinearLayoutManager(this)
        binding.rvPlayers.adapter = PlayerAdapter(this, emptyList())
    }

    private fun setupSpinner() {
        val filterOptions = arrayOf("All", "Team A", "Team B")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, filterOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerFilter.adapter = adapter

//        binding.spinnerFilter.setOnItemSelectedListener { _, _, position, _ ->
//            val filteredPlayers = when (position) {
//                1 -> viewModel.matchDetails.value?.teams?.get("TeamA")?.players ?: emptyList()
//                2 -> viewModel.matchDetails.value?.teams?.get("TeamB")?.players ?: emptyList()
//                else -> allPlayers
//            }
//            updateRecyclerView(filteredPlayers)
//        }
    }

    private fun updateRecyclerView(players: List<Player>) {
        binding.rvPlayers.adapter = PlayerAdapter(this, players)
    }
}