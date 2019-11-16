package com.example.search4.DetalhesMatche

import com.example.search4.Interfaces.MatchDetailsService
import com.example.search4.Interfaces.MatchesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitDetalhes {

    private val retrofitMatches: Retrofit
    private val retrofitMatchesDetails: Retrofit


    init {
        retrofitMatches = Retrofit.Builder()
            .baseUrl("https://br1.api.riotgames.com/lol/match/v4/matchlists/by-account/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitMatchesDetails = Retrofit.Builder()
            .baseUrl("https://br1.api.riotgames.com/lol/match/v4/matches/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun matchesService(): MatchesService {
        return retrofitMatches.create(MatchesService::class.java)
    }


    fun matchesDetailsService(): MatchDetailsService {
        return retrofitMatchesDetails.create(MatchDetailsService ::class.java)
    }


}