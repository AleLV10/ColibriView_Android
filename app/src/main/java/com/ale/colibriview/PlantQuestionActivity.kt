package com.ale.colibriview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ale.colibriview.adapter.OptionAdapter
import com.ale.colibriview.databinding.ActivityPlantillaPreguntaBinding
import com.ale.colibriview.models.OnClickListenerQuestionIshihara
import com.ale.colibriview.models.QuestionIshihara
import com.ale.colibriview.models.Test
import com.google.firebase.firestore.FirebaseFirestore

class PlantQuestionActivity : AppCompatActivity(), OnClickListenerQuestionIshihara {
    private lateinit var mBinding:ActivityPlantillaPreguntaBinding
    private var tests :MutableList<Test>? = null
    private var questions:MutableMap<String,QuestionIshihara>?= null
    private var index: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityPlantillaPreguntaBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val valor: String? = intent.getStringExtra("Validacion")
        val valor2: String? = intent.getStringExtra("Respuesta")
        val preferences = getSharedPreferences("Ishihara", Activity.MODE_PRIVATE)
        index= intent.getIntExtra("Index",index)
        //Toast.makeText(this,""+index + "$valor", Toast.LENGTH_SHORT).show()
        with(preferences.edit()) {
            putString("Respuesta$index", valor2).apply()
            putString("Validacion$index", valor).apply()
        }

        if(valor!=null) {
            index += 1
            if(index==39)
            {
                val intent = Intent(this, TestCompleted::class.java)
                startActivity(intent)
                finish()
            }

        }
        else
        {
            index=1

        }
        setUpFireStore()

    }

    private fun bindViews() {
        val question=questions!!["question$index"]
        question?.let {
            mBinding.Imagen.setImageResource(selImage(question.Imagen))
            val optionAdapter = OptionAdapter(this,question,this,index)
            mBinding.optionList.layoutManager = LinearLayoutManager(this)
            mBinding.optionList.adapter= optionAdapter
            mBinding.optionList.setHasFixedSize(true)
        }
    }

    override fun onClick(option: QuestionIshihara, valor: Int) {

    }
    private fun setUpFireStore() {

        val firestore: FirebaseFirestore=FirebaseFirestore.getInstance()
        firestore.collection("quizzes").whereEqualTo("title",resources.getString(R.string.nomtest))
            .get()
            .addOnSuccessListener {
                if(it != null && !it.isEmpty)
                {
                    tests= it.toObjects(Test::class.java)
                    questions=tests!![0].questions
                    bindViews()
                }
            }
    }

    private fun selImage(img:String):Int{
        when(img){
            "is1" -> return  R.drawable.lamina_num1
            "is2" -> return  R.drawable.lamina_num2
            "is3" -> return  R.drawable.lamina_num3
            "is4" -> return  R.drawable.lamina_num4
            "is5" -> return  R.drawable.lamina_num5
            "is6" -> return  R.drawable.lamina_num6
            "is7" -> return  R.drawable.lamina_num7
            "is8" -> return  R.drawable.lamina_num8
            "is9" -> return  R.drawable.lamina_num9
            "is10" -> return  R.drawable.lamina_num10
            "is11" -> return  R.drawable.lamina_num11
            "is12" -> return  R.drawable.lamina_num12
            "is13" -> return  R.drawable.lamina_num13
            "is14" -> return  R.drawable.lamina_num14
            "is15" -> return  R.drawable.lamina_num15
            "is16" -> return  R.drawable.lamina_num16
            "is17" -> return  R.drawable.lamina_num17
            "is18" -> return  R.drawable.lamina_num18
            "is19" -> return  R.drawable.lamina_num19
            "is20" -> return  R.drawable.lamina_num20
            "is21" -> return  R.drawable.lamina_num21
            "is22" -> return  R.drawable.lamina_num22
            "is23" -> return  R.drawable.lamina_num23
            "is24" -> return  R.drawable.lamina_num24
            "is25" -> return  R.drawable.lamina_num25
            "is26" -> return  R.drawable.lamina_num26
            "is27" -> return  R.drawable.lamina_num27
            "is28" -> return  R.drawable.lamina_num28
            "is29" -> return  R.drawable.lamina_num29
            "is30" -> return  R.drawable.lamina_num30
            "is31" -> return  R.drawable.lamina_num31
            "is32" -> return  R.drawable.lamina_num32
            "is33" -> return  R.drawable.lamina_num33
            "is34" -> return  R.drawable.lamina_num34
            "is35" -> return  R.drawable.lamina_num35
            "is36" -> return  R.drawable.lamina_num36
            "is37" -> return  R.drawable.lamina_num37
            "is38" -> return  R.drawable.lamina_num38
            else -> return 0
        }
    }
}