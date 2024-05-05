package com.ale.colibriview.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.PagingInitio
import com.ale.colibriview.R
import com.ale.colibriview.databinding.ItemInicioBinding
import com.ale.colibriview.models.IconInitio
import com.ale.colibriview.models.Initio
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class InitioAdapter(private val initio:MutableList<Initio>, private val listener: PagingInitio):
    RecyclerView.Adapter<InitioAdapter.ViewHolder>() {

    private lateinit var context:Context

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
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_inicio, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =initio.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val initio = initio[position]

        holder.setListener(initio, (position + 1))
        holder.binding.tituloinicio.text = initio.title
        holder.binding.describeInicio.text = initio.description
        Glide.with(context)
            .load(IconInitio.getIcon2(position))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .circleCrop()
            .into(holder.binding.imagenInicio)
    }

}