package com.example.search4

import com.example.search4.Interfaces.PlayerRankService
import com.example.search4.Interfaces.PlayerService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofitPlayer: Retrofit
    private val retrofitPlayerRank: Retrofit

    init {
        retrofitPlayer = Retrofit.Builder()
            .baseUrl("https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitPlayerRank = Retrofit.Builder()
            .baseUrl("https://br1.api.riotgames.com/lol/league/v4/entries/by-summoner/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun playerService(): PlayerService {
        return retrofitPlayer.create(PlayerService::class.java)
    }

    fun playerRankService(): PlayerRankService {
        return retrofitPlayerRank.create(PlayerRankService::class.java)
    }

}