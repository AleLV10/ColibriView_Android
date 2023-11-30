package com.ale.colibriview.Adapter
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.R
import com.ale.colibriview.databinding.ItemQuestionBinding
import com.ale.colibriview.models.Question

class OptionAdapter(private var context: Context, private val question: Question):
    RecyclerView.Adapter<OptionAdapter.ViewHolder>() {

    private var options : List<String> = listOf(question.Option1,question.Option2,question.Option3)
    private var boolean=false
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view)
    {
        val binding=ItemQuestionBinding.bind(view)
        var optionView=binding.botonRespuesta
        fun setListener(question: Question, valor:Int){
            binding.root.setOnClickListener {

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context
        val view=LayoutInflater.from(context).inflate(R.layout.item_question,parent,false)
        return ViewHolder(view)
    }



    override fun getItemCount() :Int =options.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder)
        {

            optionView.text=options[position]

            itemView.setOnClickListener{
                setListener(question, position)
                optionView.text=options[position]
                question.UserAnswer=options[position]

                notifyDataSetChanged()
                boolean = if (boolean) {
                    false
                }else{
                    itemView.setBackgroundColor(Color.MAGENTA)
                    true
                }

               // itemView.setBackgroundResource(R.color.morado)
                //binding.botonRespuesta.setBackgroundResource(R.color.morado)
/*
                if(question.UserAnswer==options[position]){
                    Toast.makeText(context,options[position]+question.Answer,Toast.LENGTH_SHORT).show()
                    binding.botonRespuesta.setBackgroundColor(Color.GREEN)

                }
                else {
                    binding.botonRespuesta.setBackgroundColor(Color.RED)
                    Toast.makeText(context,options[position]+question.Answer,Toast.LENGTH_SHORT).show()

                }*/

            }
           // itemView.setBackgroundResource(R.color.morado)
        }


    }

}