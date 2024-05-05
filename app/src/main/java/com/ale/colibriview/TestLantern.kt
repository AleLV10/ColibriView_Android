package com.ale.colibriview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityTestLanternBinding
import com.ale.colibriview.models.QuestionLantern
import com.ale.colibriview.models.Test
import com.google.firebase.firestore.FirebaseFirestore

class TestLantern : AppCompatActivity() {
    private lateinit var mBinding: ActivityTestLanternBinding
    private var tests :MutableList<Test>? = null
    private var questions:MutableMap<String, QuestionLantern>?= null
    private var index: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityTestLanternBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setUpFireStore()
    }
    private fun bindViews() {
        val question=questions!!["question$index"]
        question?.let {
            mBinding.imagenArriba.setImageResource(selImage(question.arriba))
            mBinding.imagenAbajo.setImageResource(selImage(question.abajo))
            //val LanternAdapter = LanternAdapter(this,question,this,index)
            //mBinding.optionList.layoutManager = LinearLayoutManager(this)
            //mBinding.optionList.adapter= LanternAdapter
            //mBinding.optionList.setHasFixedSize(true)
        }
    }

    private fun setUpFireStore() {

        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        firestore.collection("quizzes").whereEqualTo("title","Test de Lantern")
            .get()
            .addOnSuccessListener {
                if(it != null && !it.isEmpty)
                {
                    tests= it.toObjects(Test::class.java)
                    //questions=tests!![0].questionLantern
                    bindViews()
                }
            }
    }

    private fun selImage(img:String):Int{
        return when(img){
            "Verde" -> R.drawable.circulo_verde
            "Amarillo" -> R.drawable.circulo_amarillo
            "Rojo" -> R.drawable.circulo_rojo
            else -> 0
        }
    }
}