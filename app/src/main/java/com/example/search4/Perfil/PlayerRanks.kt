package com.example.search4.Perfil

import java.io.Serializable

public class PlayerRanks: Serializable {

    val leagueId: String? = null
    val queueType: String? = null
    val tier: String? = null
    val rank: String? = null
    val summonerId: String? = null
    val summonerName: String? = null
    val leaguePoints: Int? = null
    val wins: Int? = null
    val losses: Int? = null
    val veteran: Boolean? = null
    val inactive: Boolean? = null
    val freshBlood: Boolean? = null
    val hotStreak: Boolean? = null


    companion object {

        val RANK_PLAY_INFO = "RANK_PLAY_INFO"
        private var idCount = 0
    }
}