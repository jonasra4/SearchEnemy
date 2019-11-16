package com.example.search4.Interfaces

import com.example.search4.Perfil.Player
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayerService {

    @GET("{userName}?api_key=RGAPI-fa510811-cd40-41a7-bd61-3ff65ab17ac3")
    fun buscarPlayer(@Path("userName") useName:String): Call<Player>

}