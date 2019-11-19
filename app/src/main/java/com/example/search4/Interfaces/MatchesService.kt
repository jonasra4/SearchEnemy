package com.example.search4.Interfaces

import com.example.search4.Historico.MatchList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchesService {

    @GET("{account_id}?api_key=RGAPI-ae3ed035-8329-447a-9e1d-219ca0477067")
    fun buscarMatches(@Path("account_id") acc_id:String): Call<MatchList>
}