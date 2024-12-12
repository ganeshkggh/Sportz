package com.example.sportz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PlayerAdapter(
    private val context: Context,
    private val players: List<Player>
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.bind(player)

        holder.itemView.setOnClickListener {
            val message = "Batting: ${player.battingStyle}\nBowling: ${player.bowlingStyle}"
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = players.size

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val playerName: TextView = itemView.findViewById(R.id.tv_player_name)

        fun bind(player: Player) {
            val role = when {
                player.role.contains("c", true) && player.role.contains("wk", true) -> "(c & wk)"
                player.role.contains("c", true) -> "(c)"
                player.role.contains("wk", true) -> "(wk)"
                else -> ""
            }
            playerName.text = "${player.name} $role"
        }
    }
}