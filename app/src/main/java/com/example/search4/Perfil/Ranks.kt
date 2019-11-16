package com.example.search4.Perfil

import java.io.Serializable

public class Ranks : Serializable {

    public var soloDuoRank: String? = null
    public var flex5v5Rank: String? = null
    public var tftRank: String? = null
    public var flex3v3Rank: String? = null


    public var soloDuoTier: String? = null
    public var flex5v5Tier: String? = null
    public var tftTier: String? = null
    public var flex3v3Tier: String? = null

    companion object {

        val RANKS_PLAY_INFO = "RANKS_PLAY_INFO"
        var idCount = 0
    }
}
