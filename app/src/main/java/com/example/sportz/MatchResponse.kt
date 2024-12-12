package com.example.sportz

data class MatchResponse(
    val matchDetails: MatchDetails,
    val teams: Map<String, Team>
)

data class MatchDetails(
    val team1: String,
    val team2: String,
    val matchDateTime: String,
    val venue: String
)

data class Team(
    val name: String,
    val players: List<Player>
)

data class Player(
    val name: String,
    val role: String,
    val battingStyle: String,
    val bowlingStyle: String
)