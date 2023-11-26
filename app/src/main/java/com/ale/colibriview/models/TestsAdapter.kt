package com.ale.colibriview.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.R
import com.ale.colibriview.databinding.ItemTestBinding

class TestsAdapter (val Tests: MutableList<Tests> ):
RecyclerView.Adapter<TestsAdapter.QuizViewHolder>() {
    private lateinit var mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        mContext=parent.context
        var view = LayoutInflater.from(mContext).inflate(R.layout.item_test,parent,false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {

        holder.textViewTitle.text=Tests[position].title
        holder.textViewDescripcion.text=Tests[position].description
        holder.iconView.setImageResource(IconPicker.getIcon())
        holder.itemView.setOnClickListener{
            Toast.makeText(mContext,Tests[position].title,Toast.LENGTH_SHORT).show()

        }

    }

    override fun getItemCount(): Int = Tests.size

    inner class QuizViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        var binding=ItemTestBinding.bind(view)

        var textViewTitle: TextView=binding.TituloTest
        var iconView:ImageView=binding.imagenTest
        var textViewDescripcion: TextView=binding.DescripcionTest
        var cardContainer:CardView=binding.cardContainer

    }
}