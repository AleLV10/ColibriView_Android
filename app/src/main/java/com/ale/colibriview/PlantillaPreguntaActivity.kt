package com.ale.colibriview

import android.os.Bundle
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
    private var index = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityPlantillaPreguntaBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setUpFireStore()
    }

    private fun bindViews() {

        val question=questions!!["question$index"]
        question?.let {

           // val img:Int =  ("R."+question.Imagen).toInt()
           mBinding.Imagen.setImageResource(selImage(question.Imagen))
           //mBinding.Imagen.setImageURI(Uri.parse(question.Imagen))



            val optionAdapter = OptionAdapter(this,question)
            mBinding.optionList.layoutManager = LinearLayoutManager(this)
            mBinding.optionList.adapter= optionAdapter
            mBinding.optionList.setHasFixedSize(true)
        }


    }

    override fun onClick(question: Question, valor: Int) {

    }
    private fun setUpFireStore() {
        val firestore: FirebaseFirestore=FirebaseFirestore.getInstance()
        //var date:String?=intent.getStringExtra("DATE")
        //Log.i("DATE",date+"")
       // if(date!=null)
       // {
            firestore.collection("quizzes").whereEqualTo("title","Test de Ishihara")
                .get()
                .addOnSuccessListener {
                    if(it != null && !it.isEmpty)
                    {
                        tests= it.toObjects(Test::class.java)
                        questions=tests!![0].questions
                        bindViews()
                    }

                }
        //}
    }

    fun selImage(img:String):Int{

        when(img){
            "is1" -> return  R.drawable.is1
            "is2" -> return  R.drawable.is2

            else -> return 0
        }
    }
}