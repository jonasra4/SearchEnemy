package com.example.search4.DetalhesMatche

import java.io.Serializable


class Partida: Serializable {

    var partidaId: Long? = null

    var equipe1: Equipe? = null
    var equipe2: Equipe? = null

    companion object {

        val PARTIDA_INFO = "PARTIDA_INFO"
        private var idCount = 0
    }
}