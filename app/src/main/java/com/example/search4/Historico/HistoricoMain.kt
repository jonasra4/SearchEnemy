package com.example.search4.Historico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.example.search4.R
//import com.example.search4.RetrofitDetalhes
import com.example.search4.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoricoMain : AppCompatActivity(), AdapterView.OnItemClickListener {


    private var retrofitDetalhes: RetrofitDetalhes? = null

    private var matchList = ArrayList<Match>()

//    var partidas: Partidas? = null


//    lateinit var adapter : PartidasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico_main)

        retrofitDetalhes = RetrofitDetalhes()


//        val listView = ListView(this)
//        this.setContentView(listView)

//        adapter = PartidasAdapter(this,listPartidas)

//        listView.adapter = adapter

//        listView.setOnItemClickListener(this)


//        val accId = intent.getStringExtra("accId")
        getPartidass("tvZOMhsc7_m8T2SzhrsCiLqweff5kb253_X1tDg-eIa5jH8")

    }


    fun getPartidass(accId: String) {

        var acc_id: String = "Yxie0vwPya2-IQNw_6DjOE-hML_StKiun3__inIyMdM3"

        val call: Call<MatchList> = retrofitDetalhes?.matchesService()!!.buscarMatches(acc_id)

        buscarAssincrono(call)

    }


    private fun buscarAssincrono(call: Call<MatchList>) {


        call.enqueue(object : Callback<MatchList> {

            override fun onResponse(call: Call<MatchList>, response: Response<MatchList>) {

                var matches_local: List<Match> = response?.body()?.matches ?: ArrayList<Match>()


                // add item a item na variavel externa "lista de partidas"
                for (x in 0 until matches_local.size) {
                    matchList.add(matches_local[x])
                    println(matches_local[x])
                }
//                adapter!!.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<MatchList>, t: Throwable) {

                exibirErro(t) // nao funciona
            }
        })

    }

    private fun exibirErro(t: Throwable) {
        Toast.makeText(this, "Jogador não encontrado", Toast.LENGTH_SHORT).show()
    }


//    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//        val (platformId, gameId) = listPartidas[p2]
//
//        Toast.makeText(this,"$gameId",
//            Toast.LENGTH_SHORT).show()
//
//        val intent = Intent(this, InfoPartida::class.java)
//
//        intent.putExtra("matchId", gameId)
//
//        startActivityForResult(intent,1)
//    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}