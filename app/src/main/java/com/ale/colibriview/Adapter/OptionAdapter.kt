package com.ale.colibriview.Adapter
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.R
import com.ale.colibriview.databinding.ItemQuestionBinding
import com.ale.colibriview.models.Question

class OptionAdapter(private var context: Context, private val question: Question):
    RecyclerView.Adapter<OptionAdapter.ViewHolder>() {

    private var options:List<String> = listOf(question.Option1,question.Option2,question.Option3)
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
           // setListener(question,(position+1))
           // binding.op.text=question.title
            optionView.text=options[position]
            itemView.setOnClickListener{
                optionView.text=options[position]
                question.UserAnswer=options[position]
                notifyDataSetChanged()
                if(question.Answer==options[position]){
                    Toast.makeText(context,options[position]+question.Answer,Toast.LENGTH_SHORT).show()
                    binding.botonRespuesta.setBackgroundColor(Color.GREEN)
                }
                else {
                    binding.botonRespuesta.setBackgroundColor(Color.RED)
                    Toast.makeText(context,options[position]+question.Answer,Toast.LENGTH_SHORT).show()
                }
            }
        }


    }


}