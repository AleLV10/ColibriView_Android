package com.ale.colibriview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityPlantQuestTritanBinding
import com.ale.colibriview.models.QuestionProtan
import com.ale.colibriview.models.TestProtan
import com.google.firebase.firestore.FirebaseFirestore

class PlantQuestTritan : AppCompatActivity() {
    private lateinit var mBinding: ActivityPlantQuestTritanBinding
    private var testProtan :MutableList<TestProtan>? = null
    private var questions:MutableMap<String, QuestionProtan>?= null
    private var index: Int = 0
    private var continc=0
    private var contRC=0
    private var resultado=""
    private  var string:String =""
    private val respuestasMatriz = mutableListOf<Pair<Int, String>>()
    private var contRep=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPlantQuestTritanBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        index=1
        setUpFireStore()
        mBinding.b1.setOnClickListener {
            validateAnswers(1)
        }
        mBinding.b2.setOnClickListener {
            validateAnswers(2)
        }
        mBinding.b3.setOnClickListener {
            validateAnswers(3)
        }
        mBinding.b4.setOnClickListener {
            validateAnswers(4)
        }
        mBinding.b5.setOnClickListener {
            validateAnswers(5)
        }
        mBinding.b6.setOnClickListener {
            validateAnswers(6)
        }
        mBinding.b7.setOnClickListener {
            validateAnswers(7)
        }
        mBinding.b8.setOnClickListener {
            validateAnswers(8)
        }
        mBinding.noveonada.setOnClickListener {
            validateAnswers(0)
        }

    }
    private fun validateAnswers(respuesta: Int) {
        val question=questions!!["question$index"]
        val res:String
        if(respuesta.toString()== question?.answer) {
            res = "Correcto"
            //contRC++
            guardar(respuesta,res)
            if (index==10&&continc==1)
            {   contRep++
                if(contRep==1)
                    index--
                else
                    index++
            }
            else
            {
                index++
            }



        }
        else
        {
            res = "Incorrecto"
            continc++
            guardar(respuesta,res)
            if (index==10&&continc==1)
            {
                //guardar(respuesta,res)
                index--
            }else
            {

                if (continc==2)
                {
                    index=11
                    guardar(respuesta,res)
                }
                else
                    index++
            }
        }

        if (index==11)
        {
            val intent = Intent(this, TestCompleted::class.java)
            intent.putExtra("nom_variable", resources.getString(R.string.nomtest_Tr))
            startActivity(intent)
            finish()
        }
        bindViews()

    }
    private fun guardar(respuesta:Int,res:String)
    {
        if(index!=11)
        {
            string += "Respuesta$index: $res\n"
            val existente = respuestasMatriz.indexOfFirst { it.first == index }

            if (existente != -1) {
                respuestasMatriz[existente] = Pair(index, res)
            } else {
                respuestasMatriz.add(Pair(index, res))
            }
            contRC=0
            respuestasMatriz.forEach { (i, v) ->
                if(v=="Correcto")
                    contRC++
                println("Índice: $i, Validación: $v")
            }
        }

        val preferences = getSharedPreferences("Titan", Activity.MODE_PRIVATE)
        with(preferences.edit()) {
            if(index!=11)
            {
                putString("Respuesta$index", respuesta.toString()).apply()
                putString("Validacion$index", res).apply()
                putString("Completos",string).apply()
            }
            if(index>=10)
            {
                val ultimoElemento = respuestasMatriz.last()
                val indice = ultimoElemento.first
                println("Índice: $indice")
                putString("Resultados correctos","Respuestas Correctas $contRC/10 ").apply()
                resultado = when (indice) {
                    in 1..2 -> "Tritanomalía ó Tritanopía extremo"
                    in 3..4 -> "Tritanomalía severo"
                    in 5..6 -> "Tritanomalía moderado"
                    in 7..8 -> "Tritanomalía leve"
                    else -> "Visión normal"
                }
                /*resultado = if (contRC==10) {
                    "Persona no daltónica. "
                } else {
                    "Puede que usted tenga deficiencia en la vision."
                }*/
                putString("Resultados",resultado).apply()
            }

        }
    }
    private fun bindViews() {
        val question=questions!!["question$index"]
        question?.let {
            mBinding.Imagen.setImageResource(selImage(question.imagen))
        }
    }

    private fun setUpFireStore() {

        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        firestore.collection("quizzes").whereEqualTo("title",resources.getString(R.string.nomtest_Tr))
            .get()
            .addOnSuccessListener {
                if(it != null && !it.isEmpty)
                {
                    testProtan= it.toObjects(TestProtan::class.java)
                    questions=testProtan!![0].questions
                    bindViews()
                }
            }
    }

    private fun selImage(img:String):Int{
        return when(img){
            "img1" -> return  R.drawable.tritan1
            "img2" -> return  R.drawable.tritan2
            "img3" -> return  R.drawable.tritan3
            "img4" -> return  R.drawable.tritan4
            "img5" -> return  R.drawable.tritan5
            "img6" -> return  R.drawable.tritan6
            "img7" -> return  R.drawable.tritan7
            "img8" -> return  R.drawable.tritan8
            "img9" -> return  R.drawable.tritan9
            "img10" -> return  R.drawable.tritan10
            else -> 0
        }
    }


}