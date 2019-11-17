package com.example.search4.Perfil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.search4.Historico.HistoricoMain
import com.example.search4.R
import kotlinx.android.synthetic.main.activity_perfil_jogador.*

class PerfilJogador : AppCompatActivity() {

    private var player: Player? = null
    private var rank: Ranks? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_jogador)

        val intent = intent

        player = intent.getSerializableExtra(Player.PLAYER_INFO) as Player
        rank = intent.getSerializableExtra(Ranks.RANKS_PLAY_INFO) as Ranks

        showPlayer(player!!)

        showRank(rank!!)


        button.setOnClickListener {

            val intent = Intent(this, HistoricoMain::class.java)

            intent.putExtra(Player.PLAYER_INFO, player)

            startActivityForResult(intent,1)
        }
    }

    fun  showPlayer(player: Player){
        txtNomeJogador.text = player.name
        txtLevel1.text = player.summonerLevel
    }

    fun showRank(ranks: Ranks){


        if(ranks.soloDuoTier != null){
            textSoloDuo.text = ranks.soloDuoTier + " " + ranks.soloDuoRank
            showEmblemaRank(imgSoloDuo, ranks.soloDuoTier!!)
        }

        if(ranks.flex5v5Tier != null){
            textFlex5V5.text = ranks.flex5v5Tier + " " + ranks.flex5v5Rank
            showEmblemaRank(imgFlex5v5, ranks.flex5v5Tier!!)
        }

        if(ranks.tftTier != null){
            textTFT.text = ranks.tftTier + " " + ranks.tftRank
            showEmblemaRank(imgTFT, ranks.tftTier!!)

        }

        if(ranks.flex3v3Tier != null){
            textFlex3v3.text = ranks.flex3v3Tier+ " " + ranks.flex3v3Rank
            showEmblemaRank(imgFlex3v3, ranks.flex3v3Tier!!)
        }
    }

    fun showEmblemaRank(img:ImageView, tier: String){

        when (tier) {
            "BRONZE" -> img.setImageResource(R.drawable.bronze)
            "CHALLENGER" -> img.setImageResource(R.drawable.challenger)
            "DIAMOND" -> img.setImageResource(R.drawable.diamond)
            "GOLD" -> img.setImageResource(R.drawable.gold)
            "GRANDMASTER" -> img.setImageResource(R.drawable.grandmaster)
            "IRON" -> img.setImageResource(R.drawable.iron)
            "MASTER" -> img.setImageResource(R.drawable.master)
            "PLATINUM" -> img.setImageResource(R.drawable.platinum)
            "SILVER" -> img.setImageResource(R.drawable.silver)

            else -> { // Note the block
                img.setImageResource(R.drawable.unranked)
            }
        }
    }

}
