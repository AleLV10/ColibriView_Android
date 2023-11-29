package com.ale.colibriview.Adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.Cards_Test
import com.ale.colibriview.R
import com.ale.colibriview.databinding.ItemTestBinding
import com.ale.colibriview.models.IconPicker
import com.ale.colibriview.models.Test

class TestsAdapter(private val Tests:MutableList<Test>, private val listener: Cards_Test)
    :RecyclerView.Adapter<TestsAdapter.ViewHolder>() {

    private lateinit var context:Context

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view)
    {
        val binding=ItemTestBinding.bind(view)

        fun setListener(test: Test, valor:Int){
            binding.root.setOnClickListener {
                listener.onClic(test,valor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context
        val view=LayoutInflater.from(context).inflate(R.layout.item_test,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =Tests.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val test=Tests[position]
        with(holder)
        {
            setListener(test,(position+1))
            binding.TituloTest.text=test.title
           // binding.DescripcionTest.text=test.description
            binding.imagenTest.setImageResource(IconPicker.getIcon())
        }
    }


}