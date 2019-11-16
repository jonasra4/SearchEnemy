package com.example.search4.Perfil

import java.io.Serializable
import java.util.*


public class Player: Serializable {

    public var profileIconId: String? = null
    public var name: String? = null
    public var puild: String? = null
    public var summonerLevel: String? = null
    public var accountId: String? = null
    public var id: String? = null
    public var revisionDate: String? = null


    companion object {

        val PLAYER_INFO = "PLAYER_INFO"
        private var idCount = 0
    }
}