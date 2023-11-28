package com.ale.colibriview

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ale.colibriview.Adapter.OptionAdapter
import com.ale.colibriview.databinding.ActivityPlantillaPreguntaBinding
import com.ale.colibriview.models.Question
import com.ale.colibriview.models.Test
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PlantillaPreguntaActivity : AppCompatActivity(), OnClickListenerQuestion {
    private lateinit var mBinding:ActivityPlantillaPreguntaBinding
    private lateinit var firestore: FirebaseFirestore
    private var tests :MutableList<Test>? = null
    private var questions:MutableMap<String,Question>?= null
    private var index = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityPlantillaPreguntaBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        bindViews()
        setUpFireStore()
    }

    private fun bindViews() {
        val question = Question(
            "Pregunta",
            "Opcion 1",
            "Opcion 2",
            "Opcion 3",
            "Opcion 2"
        )

        mBinding.Imagen.setImageDrawable(Drawable.createFromPath(question.Imagen))
        val optionAdapter = OptionAdapter(this,question)
        mBinding.optionList.layoutManager = LinearLayoutManager(this)
        mBinding.optionList.adapter= optionAdapter
        mBinding.optionList.setHasFixedSize(true)
    }

    override fun onClick(question: Question, valor: Int) {

    }
    private fun setUpFireStore() {
        val db = Firebase.firestore
        db.collection("quizzes")
            .get()
            .addOnSuccessListener {
                  if(it != null && !it.isEmpty)
                  {
                      tests= it.toObjects(Test::class.java)
                      questions=tests!![0].questions
                      bindViews()
                  }


            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
    companion object {
        private const val TAG = "KotlinActivity"
    }
}