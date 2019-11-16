package com.example.search4.Interfaces

import com.example.search4.DetalhesMatche.DataJson.MatcheMain
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchDetailsService {
    @GET("{match_id}?api_key=RGAPI-fa510811-cd40-41a7-bd61-3ff65ab17ac3")
    fun buscarMatchDetails(@Path("match_id") match_id:String): Call<MatcheMain>
}