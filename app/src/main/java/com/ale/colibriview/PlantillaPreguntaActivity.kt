package com.ale.colibriview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ale.colibriview.Adapter.OptionAdapter
import com.ale.colibriview.databinding.ActivityPlantillaPreguntaBinding
import com.ale.colibriview.models.Question
import com.ale.colibriview.models.Test
import com.google.firebase.firestore.FirebaseFirestore

class PlantillaPreguntaActivity : AppCompatActivity(), OnClickListenerQuestion {
    private lateinit var mBinding:ActivityPlantillaPreguntaBinding
    private var tests :MutableList<Test>? = null
    private var questions:MutableMap<String,Question>?= null
    private var index: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityPlantillaPreguntaBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.Siguiente.setOnClickListener {
            index+=1
            bindViews()
        }

        setUpFireStore()


    }

    private fun bindViews() {
        val question=questions!!["question$index"]
        //Toast.makeText(this,"Answer"+question?.Answer, Toast.LENGTH_SHORT).show()
        question?.let {
            mBinding.Imagen.setImageResource(selImage(question.Imagen))
            val optionAdapter = OptionAdapter(this,question,this)
            mBinding.optionList.layoutManager = LinearLayoutManager(this)
            mBinding.optionList.adapter= optionAdapter
            mBinding.optionList.setHasFixedSize(true)

        }




    }

    override fun onClick(question: Question, valor: Int) {
        question.UserAnswer
        Toast.makeText(this,"UserAnswer "+question.UserAnswer, Toast.LENGTH_SHORT).show()
        val answer=OptionAdapter(this,question,this)
        answer.getAnswerQ()
        Toast.makeText(this,"Answer "+ answer.getAnswerQ(), Toast.LENGTH_SHORT).show()

    }
    private fun setUpFireStore() {
        index=1
        val firestore: FirebaseFirestore=FirebaseFirestore.getInstance()
        firestore.collection("quizzes").whereEqualTo("title","Test de Ishihara")
            .get()
            .addOnSuccessListener {
                if(it != null && !it.isEmpty)
                {
                    tests= it.toObjects(Test::class.java)
                    questions=tests!![0].questions
                    bindViews()
                }
                else
                {

                }
            }
    }

    fun selImage(img:String):Int{

        when(img){
            "is1" -> return  R.drawable.is1
            "is2" -> return  R.drawable.is2
            "is3" -> return  R.drawable.is3
            "is4" -> return  R.drawable.is4
            "is5" -> return  R.drawable.is5
            "is6" -> return  R.drawable.is6
            "is7" -> return  R.drawable.is7
            "is8" -> return  R.drawable.is8
            "is9" -> return  R.drawable.is9
            "is10" -> return  R.drawable.is10
           // "is11" -> return  R.drawable.is11
           // "is12" -> return  R.drawable.is12
            else -> return 0
        }
    }
}