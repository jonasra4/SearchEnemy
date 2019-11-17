package com.example.search4.Interfaces

import com.example.search4.Historico.MatchList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchesService {

    @GET("{account_id}?api_key=RGAPI-efe53f4c-d33b-4564-9c4a-ecd5aaae6c93")
    fun buscarMatches(@Path("account_id") acc_id:String): Call<MatchList>
}