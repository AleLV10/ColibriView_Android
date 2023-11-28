package com.ale.colibriview.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.Inicio
import com.ale.colibriview.R
import com.ale.colibriview.databinding.ActivityItemInicioBinding
import com.ale.colibriview.item_inicio
import com.ale.colibriview.models.IconPicker

class InicioAdapter(private val Inicio:MutableList<Inicio>, private val listener: item_inicio):
    RecyclerView.Adapter<InicioAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val binding = ActivityItemInicioBinding.bind(view)

        fun setListener(inicio: Inicio, valor:Int){
            binding.root.setOnClickListener {
                listener.onClick(inicio,valor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context
        val view= LayoutInflater.from(context).inflate(R.layout.activity_item_inicio,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =Inicio.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val inicio=Inicio[position]
        with(holder)
        {
            setListener(inicio,(position+1))
            binding.tituloinicio.text = inicio.title
      /*      binding.describeInicio.text = inicio.description

       */
            binding.imagenInicio.setImageResource(IconPicker.getIcon())

        }
    }


}