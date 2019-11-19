package com.example.search4.Historico

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.example.search4.DetalhesMatche.*
import com.example.search4.DetalhesMatche.DataJson.MatcheMain
import com.example.search4.Perfil.Player
import com.example.search4.R
//import com.example.search4.RetrofitDetalhes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class HistoricoMain : AppCompatActivity(), AdapterView.OnItemClickListener {

    var progressDialog:  ProgressDialog? = null

    private var retrofitDetalhes: RetrofitDetalhes? = null

    private var player: Player? = null

    private var partidas = ArrayList<Partida>()

    private var historico = ArrayList<PlayerStatus>()

    lateinit var adapter : HistoricoAdapter

    private var matchList = ArrayList<Match>()

    private var  matchesMain= ArrayList<MatcheMain>() //lista com os detalhes de cada partida

//    private var partida: Partida? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("Listando Histórico")
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_historico_main)
        getSupportActionBar()?.hide()
//        setTitle("Histórico de partidas")

        val listView = ListView(this)
        this.setContentView(listView)

        adapter = HistoricoAdapter(this, historico)

        listView.adapter = adapter

        listView.setOnItemClickListener(this)



        retrofitDetalhes = RetrofitDetalhes()

        val intent = intent

        player = intent.getSerializableExtra(Player.PLAYER_INFO) as Player


//        getMatchList("tvZOMhsc7_m8T2SzhrsCiLqweff5kb253_X1tDg-eIa5jH8")

        getMatchList(player?.accountId!!)

    }


    fun getMatchList(accId: String) {

//        var acc_id: String = "ZUOO_6JtmdUA8CtuuhjkxRGzAGJLm8BOoufoAFA_ODs"

        val call: Call<MatchList> = retrofitDetalhes?.matchesService()!!.buscarMatches(accId)



        buscarAssincrono(call)

    }


    private fun buscarAssincrono(call: Call<MatchList>) {


        call.enqueue(object : Callback<MatchList> {

            override fun onResponse(call: Call<MatchList>, response: Response<MatchList>) {

                var matches_local: List<Match> = response?.body()?.matches ?: ArrayList<Match>()

                var qt_partidas = matches_local.size

                if (matches_local.size >= 20){
                    qt_partidas = 20
                }


                for (x in 0 until qt_partidas) {
                    getMatchesMains(matches_local[x].gameId)
                }



            }

            override fun onFailure(call: Call<MatchList>, t: Throwable) {

                exibirErro(t) // nao funciona
            }
        })

    }


    fun getMatchesMains(id_match:String){

        val call: Call<MatcheMain> = retrofitDetalhes?.matchesDetailsService()!!.buscarMatchDetails(id_match)

        buscarMatchDetailsAssincrono(call)
    }

    private fun buscarMatchDetailsAssincrono(call: Call<MatcheMain>) {


        call.enqueue(object : Callback<MatcheMain> {

            override fun onResponse(call: Call<MatcheMain>, response: Response<MatcheMain>) {

                if(response?.body() != null){
                    val current_match_main = response?.body()
                    configurarParida(current_match_main!!)
                }

            }

            override fun onFailure(call: Call<MatcheMain>, t: Throwable) {
                exibirErro(t) // nao funciona
            }
        })

    }


    fun configurarParida(matcheMain: MatcheMain){


        var partida = Partida()

        partida.partidaId = matcheMain.gameId

        partida.equipe1 = configurarEquipe(0, matcheMain, "Equipe 1")
        partida.equipe2 = configurarEquipe(5, matcheMain, "Equipe 2")

//        println(partida?.equipe1?.playerStatus?.get(0)?.nome)
//        println(partida?.equipe2?.playerStatus?.get(0)?.nome)

//        println(partida.partidaId)

        partidas.add(partida)

    }

    fun configurarEquipe(i: Int, matcheMain: MatcheMain, equipeName:String):Equipe{

        val equipe = Equipe()

        val statusPlayers = ArrayList<PlayerStatus>()

        equipe.nomeEquioe = equipeName
        equipe.venceu = matcheMain.participants?.get(i).stats?.win

        println("slfkggoaerçgoiehrgejgsldgdlifgbsfkuhbkusdhskudhhhhhhhhhhhhhhhhhhh")
        for (x in 0 + i until 5 + i){
            var currentPlayer = PlayerStatus()

            currentPlayer.nome = matcheMain.participantIdentities.get(x).player?.summonerName
            currentPlayer.participanteId = matcheMain.participantIdentities.get(x).participantId

            currentPlayer.kills = matcheMain.participants.get(x).stats?.kills
            currentPlayer.mortes = matcheMain.participants.get(x).stats?.deaths
            currentPlayer.assistencias = matcheMain.participants.get(x).stats?.assists

            currentPlayer.campeao = matcheMain.participants.get(x).participantId

            currentPlayer.cs = matcheMain.participants.get(x).stats?.neutralMinionsKilled!! + matcheMain.participants.get(x).stats?.totalMinionsKilled!!

            currentPlayer.gold = matcheMain.participants.get(x).stats?.goldEarned

            currentPlayer.level = matcheMain.participants.get(x).stats?.champLevel
            currentPlayer.team = matcheMain.participants.get(x).teamId
            currentPlayer.win = matcheMain.participants.get(x).stats?.win

            statusPlayers?.add(currentPlayer)

            if (currentPlayer.nome == player?.name){
                historico.add(currentPlayer)
//                println(currentPlayer.nome + " " + player?.name)
                adapter!!.notifyDataSetChanged()

                if (progressDialog!!.isShowing){
                    progressDialog!!.dismiss()
                }
            }
        }


        equipe.playerStatus = statusPlayers

        return equipe

    }


    private fun exibirErro(t: Throwable) {
        Toast.makeText(this, "Jogador não encontrado", Toast.LENGTH_SHORT).show()
    }



    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        val partidaSelecionada = partidas.get(p2)

        val intent = Intent(this, PartidaMain::class.java)

        intent.putExtra(Partida.PARTIDA_INFO, partidaSelecionada)

        startActivityForResult(intent,1)


    }
}





