package com.ale.colibriview.Adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.R
import com.ale.colibriview.ResultadosActivity
import com.ale.colibriview.databinding.ItemResultadosBinding
import com.ale.colibriview.models.Resultados
import com.google.firebase.firestore.FirebaseFirestore

class ResultadoAdapter(private var context: Context, private val res: MutableList<Resultados>, private val listener: ResultadosActivity, private val lista: MutableList<String>):
    RecyclerView.Adapter<ResultadoAdapter.ViewHolder>() {

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

    override fun getItemCount(): Int =res.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val resultado = res[position]
        with(holder)
        {
            binding.nomtest.text = resultado.Test
            binding.nombre.text = resultado.Nombre
            binding.correctas.text = resultado.Resultados_correctos
            binding.fecha.text = resultado.Fecha
            binding.diag.text = resultado.Resultados
            binding.delete.setOnClickListener {
                val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
                firestore.collection("users").document(lista[position])
                    .delete()
                    .addOnSuccessListener {  Toast.makeText(context,"Eliminado",Toast.LENGTH_SHORT).show() }
                    .addOnFailureListener { e -> Toast.makeText(context,"Error al Eliminar: $e",Toast.LENGTH_SHORT).show() }
            }
        }
       // holder.binding.diag.text = res.Resultados
      //  holder.binding.fecha.text=res.Fecha

        holder.setListener(resultado, position)
    }
}