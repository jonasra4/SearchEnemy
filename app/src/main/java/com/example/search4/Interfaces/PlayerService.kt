package com.example.search4.Interfaces

import com.example.search4.Perfil.Player
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayerService {

    @GET("{userName}?api_key=RGAPI-ae3ed035-8329-447a-9e1d-219ca0477067")
    fun buscarPlayer(@Path("userName") useName:String): Call<Player>

}