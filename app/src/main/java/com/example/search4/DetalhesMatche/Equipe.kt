package com.example.search4.DetalhesMatche

import java.io.Serializable

class Equipe: Serializable {

    var nomeEquioe: String? = null

    var venceu: Boolean? = null

    var killTotal: Int? = null
    var mortesTotal: Int? = null
    var assistTotal: Int? = null

    var ouroTotal: Int? = null

    var playerStatus: ArrayList<PlayerStatus>? = null


    fun getKillTotal():Int{

        var total = 0

        for (x in 0  until playerStatus?.size!!){
            total += playerStatus?.get(x)?.kills!!
        }

        return  total
    }

    fun getMortesTotal():Int{

        var total = 0

        for (x in 0  until playerStatus?.size!!){
            total += playerStatus?.get(x)?.mortes!!
        }

        return  total
    }

    fun getAssistTotal():Int{

        var total = 0

        for (x in 0  until playerStatus?.size!!){
            total += playerStatus?.get(x)?.assistencias!!
        }

        return  total
    }

    fun getGoldTotal():Int{

        var total = 0

        for (x in 0  until playerStatus?.size!!){
            total += playerStatus?.get(x)?.gold!!
        }

        return  total
    }

}