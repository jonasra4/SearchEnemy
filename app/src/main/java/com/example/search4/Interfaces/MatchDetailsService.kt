package com.example.search4.Interfaces

import com.example.search4.DetalhesMatche.DataJson.MatcheMain
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchDetailsService {
    @GET("{match_id}?api_key=RGAPI-efe53f4c-d33b-4564-9c4a-ecd5aaae6c93")
    fun buscarMatchDetails(@Path("match_id") match_id:String): Call<MatcheMain>
}