package com.example.search4.Interfaces

import com.example.search4.Perfil.Player
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayerService {

    @GET("{userName}?api_key=RGAPI-efe53f4c-d33b-4564-9c4a-ecd5aaae6c93")
    fun buscarPlayer(@Path("userName") useName:String): Call<Player>

}