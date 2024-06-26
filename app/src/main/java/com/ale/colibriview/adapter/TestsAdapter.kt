package com.ale.colibriview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.CardsTest
import com.ale.colibriview.R
import com.ale.colibriview.databinding.ItemTestBinding
import com.ale.colibriview.models.IconPicker
import com.ale.colibriview.models.TestIshihara
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class TestsAdapter(private val testIshiharas:MutableList<TestIshihara>, private val listener: CardsTest)
    :RecyclerView.Adapter<TestsAdapter.ViewHolder>() {

    private lateinit var context:Context

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view)
    {
        val binding=ItemTestBinding.bind(view)

        fun setListener(testIshihara: TestIshihara, valor:Int){
            binding.root.setOnClickListener {
                listener.onClick(testIshihara,valor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context
        val view=LayoutInflater.from(context).inflate(R.layout.item_test,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =testIshiharas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val test=testIshiharas[position]
        with(holder)
        {
            setListener(test,(position+1))
            binding.TituloTest.text=test.title
            binding.DescripcionTest.text=test.description
            Glide.with(context)
                .load(IconPicker.getIcon(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(holder.binding.imagenTest)
        }
    }


}