package com.ale.colibriview.adapter
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.PlantQuestionActivity
import com.ale.colibriview.R
import com.ale.colibriview.databinding.ItemQuestionBinding
import com.ale.colibriview.models.QuestionIshihara

class OptionAdapter(private var context: Context, private val question: QuestionIshihara, private val listener: PlantQuestionActivity, private val index:Int):
    RecyclerView.Adapter<OptionAdapter.ViewHolder>() {
    private var options : List<String> = listOf(question.Option1,question.Option2,question.Option3)
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view)
    {
        val binding=ItemQuestionBinding.bind(view)
        var optionView=binding.botonRespuesta

        fun setListener(question: QuestionIshihara, valor:Int){
            binding.root.setOnClickListener {
                listener.onClick(question,valor)
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
            setListener(question, position)
            itemView.setOnClickListener{

                optionView.text=options[position]
                question.UserAnswer=options[position]

                val intent = Intent(context,
                    PlantQuestionActivity::class.java
                )
                intent.putExtra("Respuesta", question.UserAnswer)
                intent.putExtra("Index", index)

                if(question.Answer==options[position]){
                    binding.botonRespuesta.setBackgroundColor(Color.GREEN)
                    intent.putExtra("Validacion", "Correcto")
                }
                else {
                    binding.botonRespuesta.setBackgroundColor(Color.RED)
                    intent.putExtra("Validacion", "Incorrecto")

                }
                //notifyDataSetChanged()
                notifyItemChanged(position)
                context.startActivity(intent)

            }


        }
    }

}