package com.example.search4

import android.content.Intent
import android.content.LocusId
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.search4.Perfil.PerfilJogador
import com.example.search4.Perfil.Player
import com.example.search4.Perfil.PlayerRanks
import com.example.search4.Perfil.Ranks
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var player: Player? = null
    private var ranksList:List<PlayerRanks> = emptyList()
    private var rank: Ranks? = null

    private var retrofitInitializer: RetrofitInitializer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        retrofitInitializer = RetrofitInitializer()

    }

    fun getUsuario(view: View) {

        var usuario:String = editUsuario.text.toString()


//        val call: Call<Player> = retrofitInitializer?.playerService()!!.buscarPlayer(usuario)
        val call: Call<Player> = retrofitInitializer?.playerService()!!.buscarPlayer("paiN KamiKat")

        buscarPlayerAssincrono(call)
    }


    private fun buscarPlayerAssincrono(call: Call<Player>) {

        call.enqueue(object : Callback<Player> {

            override fun onResponse(call: Call<Player>, response: Response<Player>) {

                player = response?.body()!!

                if (player != null){ //se o jogador for encontrado ele tentara procurar seu rank

                    getRankUsuario(player?.id.toString())

                }
                else{
                    exibirErro()
                }

            }

            override fun onFailure(call: Call<Player>, t: Throwable) {

                exibirErro()
            }
        })
    }

    fun getRankUsuario(id: String) {

        val call: Call<List<PlayerRanks>> = retrofitInitializer?.playerRankService()!!.buscarPlayerRank(id)

        buscarPlayerRankAssincrono(call)
    }


    private fun buscarPlayerRankAssincrono(call: Call<List<PlayerRanks>>) {

        call.enqueue(object : Callback<List<PlayerRanks>> {

            override fun onResponse(call: Call<List<PlayerRanks>>, response: Response<List<PlayerRanks>>) {

                ranksList = response?.body()!!

                if (ranksList != null){

                    rank = configuraRanks(ranksList)

                    showPerfil(player!!, rank!!)
                }

            }

            override fun onFailure(call: Call<List<PlayerRanks>>, t: Throwable) {


                exibirErro()
            }
        })
    }




    fun configuraRanks(rankList: List<PlayerRanks> ):Ranks{

        val currentRank: Ranks = Ranks()

        for (x in 0 until rankList.size) {

            if (rankList.get(x).queueType == "RANKED_TFT"){
                currentRank?.tftRank = rankList.get(x).rank.toString()
                currentRank?.tftTier = rankList.get(x).tier.toString()
            }
            else if (rankList.get(x).queueType == "RANKED_SOLO_5x5"){
                currentRank?.soloDuoRank = rankList.get(x).rank.toString()
                currentRank?.soloDuoTier = rankList.get(x).tier.toString()
            }
            else if (rankList.get(x).queueType == "RANKED_FLEX_TT"){
                currentRank?.flex3v3Rank= rankList.get(x).rank.toString()
                currentRank?.flex3v3Tier = rankList.get(x).tier.toString()
            }
            else{ //RANKED_FLEX_5v5
                currentRank?.flex5v5Rank = rankList.get(x).rank.toString()
                currentRank?.flex5v5Tier = rankList.get(x).tier.toString()
            }

        }


        return currentRank
    }

    fun showPerfil(currentPlayer: Player, currentPlayerRank: Ranks) {

        val intent= Intent(this, PerfilJogador::class.java)

        intent.putExtra(Player.PLAYER_INFO, currentPlayer)
        intent.putExtra(Ranks.RANKS_PLAY_INFO, currentPlayerRank)

        startActivityForResult(intent, PLAYER)
    }

    private fun exibirErro(){
        Toast.makeText(this, "Jogador não encontrado", Toast.LENGTH_SHORT).show()
    }


    companion object { //NAO SEI PRA QUE SERVE ISSO
        protected val PLAYER = 0
    }


}
