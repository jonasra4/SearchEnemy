package com.example.search4.DetalhesMatche

import android.graphics.Color.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.search4.R
import kotlinx.android.synthetic.main.activity_perfil_jogador.view.*
import kotlinx.android.synthetic.main.fragment_jogador_equipe.view.*
import kotlinx.android.synthetic.main.fragment_resumo_equipe_partida.view.*

class PartidaMain : AppCompatActivity() {

    private var partida: Partida? = null

    private var resumeEquipe1: ResumoEquipePartida? = null
    private var resumeEquipe2: ResumoEquipePartida? = null

    private var jogador1: JogadorEquipe? = null
    private var jogador2: JogadorEquipe? = null
    private var jogador3: JogadorEquipe? = null
    private var jogador4: JogadorEquipe? = null
    private var jogador5: JogadorEquipe? = null
    private var jogador6: JogadorEquipe? = null
    private var jogador7: JogadorEquipe? = null
    private var jogador8: JogadorEquipe? = null
    private var jogador9: JogadorEquipe? = null
    private var jogador10: JogadorEquipe? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_partida_main)

        val intent = intent

        partida = intent.getSerializableExtra(Partida.PARTIDA_INFO) as Partida

        resumeEquipe1 = supportFragmentManager.findFragmentById(R.id.fragEquipe1) as ResumoEquipePartida?
        resumeEquipe2 = supportFragmentManager.findFragmentById(R.id.fragEquipe2) as ResumoEquipePartida?


        jogador1 = supportFragmentManager.findFragmentById(R.id.fragJogador1) as JogadorEquipe?
        jogador2 = supportFragmentManager.findFragmentById(R.id.fragJogador2) as JogadorEquipe?
        jogador3 = supportFragmentManager.findFragmentById(R.id.fragJogador3) as JogadorEquipe?
        jogador4 = supportFragmentManager.findFragmentById(R.id.fragJogador4) as JogadorEquipe?
        jogador5 = supportFragmentManager.findFragmentById(R.id.fragJogador5) as JogadorEquipe?
        jogador6 = supportFragmentManager.findFragmentById(R.id.fragJogador6) as JogadorEquipe?
        jogador7 = supportFragmentManager.findFragmentById(R.id.fragJogador7) as JogadorEquipe?
        jogador8 = supportFragmentManager.findFragmentById(R.id.fragJogador8) as JogadorEquipe?
        jogador9 = supportFragmentManager.findFragmentById(R.id.fragJogador9) as JogadorEquipe?
        jogador10 = supportFragmentManager.findFragmentById(R.id.fragJogador10) as JogadorEquipe?


        showEquipe(resumeEquipe1!!, partida?.equipe1!!)

        showJogador(jogador1!!,partida?.equipe1!!,0)
        showJogador(jogador2!!,partida?.equipe1!!,1)
        showJogador(jogador3!!,partida?.equipe1!!,2)
        showJogador(jogador4!!,partida?.equipe1!!,3)
        showJogador(jogador5!!,partida?.equipe1!!,4)


        showEquipe(resumeEquipe2!!, partida?.equipe2!!)

        showJogador(jogador6!!,partida?.equipe2!!,0)
        showJogador(jogador7!!,partida?.equipe2!!,1)
        showJogador(jogador8!!,partida?.equipe2!!,2)
        showJogador(jogador9!!,partida?.equipe2!!,3)
        showJogador(jogador10!!,partida?.equipe2!!,4)




    }


    fun showJogador(jogador: Fragment, equipe: Equipe,i:Int){
        jogador?.view!!.txtJogadorNome?.text = equipe?.playerStatus?.get(i)?.nome
        jogador?.view!!.textKDA?.text = equipe?.playerStatus?.get(i)?.kills.toString() + " / "+ partida?.equipe1?.playerStatus?.get(i)?.mortes.toString() + " / "+partida?.equipe1?.playerStatus?.get(i)?.assistencias.toString()
        jogador?.view!!.textMinion.text = equipe?.playerStatus?.get(i)?.cs.toString()
        jogador?.view!!.txtGold.text = equipe?.playerStatus?.get(i)?.gold.toString()
        jogador?.view!!.txtLevelJogador.text = equipe?.playerStatus?.get(i)?.level.toString()

    }

    fun showEquipe(equipeFrag: Fragment, equipe: Equipe){
        equipeFrag?.view!!.txtNomeEquipe.text = equipe.nomeEquioe
//        equipeFrag?.view!!.txtWin.text = equipe.venceu.toString()

        if (equipe.venceu == true){
            equipeFrag?.view!!.txtWin.text = "Vitória"
            equipeFrag?.view!!.txtWin.setTextColor(GREEN)
        }
        else if(equipe.venceu == false){
            equipeFrag?.view!!.txtWin.text = "Derrota"
            equipeFrag?.view!!.txtWin.setTextColor(RED)

        }
        else{
            equipeFrag?.view!!.txtWin.text = "Empate"
            equipeFrag?.view!!.txtWin.setTextColor(GRAY)

        }

        equipeFrag?.view!!.txtKDATotal.text = equipe.getKillTotal().toString() + " / " + equipe.getMortesTotal().toString() + " / " +equipe.getAssistTotal().toString()
        equipeFrag?.view!!.txtGoldTotal.text = equipe.getGoldTotal().toString()

    }
}
