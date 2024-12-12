package com.example.sportz

import javax.inject.Inject

class MatchRepository @Inject constructor(private val api: SportzApi) {
    suspend fun fetchMatchDetails1(): MatchResponse = api.getMatchDetails1()
    suspend fun fetchMatchDetails2(): MatchResponse = api.getMatchDetails2()
}