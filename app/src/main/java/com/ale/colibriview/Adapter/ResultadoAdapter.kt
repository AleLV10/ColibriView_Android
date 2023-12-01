package com.ale.colibriview.Adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.R
import com.ale.colibriview.ResultadosActivity
import com.ale.colibriview.databinding.ItemResultadosBinding
import com.ale.colibriview.models.Resultados

class ResultadoAdapter(private val res: Resultados, private val listener: ResultadosActivity)
    :RecyclerView.Adapter<ResultadoAdapter.ViewHolder>() {

    private lateinit var context:Context
    private var options : List<String> = listOf(res.Resultados,res.Resultados_correctos,res.Fecha,res.Lugar,res.Nombre,res.Test)
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view)
    {
        val binding= ItemResultadosBinding.bind(view)

        fun setListener(resultados: Resultados, valor:Int){
            binding.root.setOnClickListener {
                listener.onClick(resultados,valor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context
        val view=LayoutInflater.from(context).inflate(R.layout.item_resultados,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =options.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resultado = options[position]
        holder.setListener(res, (position + 1))
        holder.binding.nomtest.text = res.Test
        holder.binding.nombre.text = res.Nombre
        holder.binding.correctas.text = res.Resultados_correctos
        holder.binding.diag.text = res.Resultados
        holder.binding.fecha.text=res.Fecha
    }

}