package com.example.search4.Historico

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.search4.DetalhesMatche.PlayerStatus
import com.example.search4.R
import kotlinx.android.synthetic.main.partida_item.view.*

class HistoricoAdapter (
    private val ctx: Context,
    private val veiculos: ArrayList<PlayerStatus>) : BaseAdapter(){

    //    1786578923
//    private val logos: TypedArray by lazy {
//        ctx.resources.obtainTypedArray(R.array.logos)
//    }

    override fun getCount(): Int = veiculos.size

    override fun getItem(p0: Int): Any = veiculos[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val veiculo = veiculos[position] //acessa o veiculo com todos os parametros (eu acho)

        val holder: ViewHolder // outra classe que ainda vai ser declarada em baixo
        val row: View

        if (convertView == null){
            Log.d("holder","Nova posicao: $position")
            row = LayoutInflater
                .from(ctx)
                .inflate(R.layout.partida_item,parent, false)

            holder = ViewHolder(row)
            row.tag = holder
        }
        else{
            Log.d("holder", "Existente posicao: $position")
            row = convertView
            holder = convertView.tag as ViewHolder
        }

        holder.txtLevel.text = veiculo.level.toString()
        holder.txtKDA.text = veiculo.kills.toString() + " / " + veiculo.mortes.toString() + " / " + veiculo.assistencias.toString()
        holder.txtMinion.text = veiculo.cs.toString()

        if (veiculo.win == true){
            holder.view.setBackgroundColor(Color.GREEN)
        }
        else if(veiculo.win == false){
            holder.view.setBackgroundColor(Color.RED)
        }
        else{
            holder.view.setBackgroundColor(Color.GRAY)
        }

        return row
    }

    companion object{
        data class ViewHolder(val view: View) {
            //            val imgLogo: ImageView = view.imgLogo
            val txtLevel: TextView = view.txtLevel1
            val imgCampeao: ImageView = view.iconCampeao
            val txtKDA: TextView = view.textKDAg
            val txtMinion: TextView = view.textGold

//            val txtCombustivel: TextView = view.txtCombustivel
        }
    }


}