package com.ale.colibriview.Adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.ResultadosActivity
import com.ale.colibriview.databinding.ItemResultadosBinding

class TesterAdapter(private val list: List<String>,private val listener:ResultadosActivity):
    RecyclerView.Adapter<TesterAdapter.ViewHolder>()
{
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view)
    {
        val mBinding= ItemResultadosBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TesterAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TesterAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}