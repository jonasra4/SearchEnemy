package com.example.search4.Interfaces

import com.example.search4.Historico.MatchList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchesService {

    @GET("{account_id}?api_key=RGAPI-fa510811-cd40-41a7-bd61-3ff65ab17ac3")
    fun buscarMatches(@Path("account_id") acc_id:String): Call<MatchList>
}