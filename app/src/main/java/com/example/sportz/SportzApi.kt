package com.example.sportz

import retrofit2.http.GET

interface SportzApi {
    @GET("nzin01312019187360.json")
    suspend fun getMatchDetails1(): MatchResponse

    @GET("sapk01222019186652.json")
    suspend fun getMatchDetails2(): MatchResponse
}