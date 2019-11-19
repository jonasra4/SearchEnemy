package com.example.search4.Interfaces

import com.example.search4.DetalhesMatche.DataJson.MatcheMain
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchDetailsService {
    @GET("{match_id}?api_key=RGAPI-ae3ed035-8329-447a-9e1d-219ca0477067")
    fun buscarMatchDetails(@Path("match_id") match_id:String): Call<MatcheMain>
}