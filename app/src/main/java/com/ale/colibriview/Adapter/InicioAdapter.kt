package com.ale.colibriview.Adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.Inicio
import com.ale.colibriview.R
import com.ale.colibriview.databinding.ItemInicioBinding
import com.ale.colibriview.models.IconInicio
import com.ale.colibriview.models.Initio

class InicioAdapter(private val Inicio:MutableList<Initio>, private val listener: Inicio):
    RecyclerView.Adapter<InicioAdapter.ViewHolder>() {

    private var context: Context? = null

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val binding = ItemInicioBinding.bind(view)

        fun setListener(initio: Initio, valor:Int){
            binding.root.setOnClickListener {
                listener.onClick(initio,valor)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_inicio, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =Inicio.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val inicio = Inicio[position]

        holder.setListener(inicio, (position + 1))
        holder.binding.tituloinicio.text = inicio.title
        holder.binding.describeInicio.text = inicio.description
        // Si `getIcon2()` devuelve un recurso de imagen en forma de Int (ID del recurso)
        holder.binding.imagenInicio.setImageResource(IconInicio.getIcon2())

    }

}