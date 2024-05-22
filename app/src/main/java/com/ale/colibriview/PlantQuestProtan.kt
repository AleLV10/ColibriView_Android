package com.ale.colibriview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityPlantQuestProtanBinding
import com.ale.colibriview.models.QuestionProtan
import com.ale.colibriview.models.TestProtan
import com.google.firebase.firestore.FirebaseFirestore

class PlantQuestProtan : AppCompatActivity() {
    private lateinit var mBinding: ActivityPlantQuestProtanBinding
    private var testProtan :MutableList<TestProtan>? = null
    private var questions:MutableMap<String, QuestionProtan>?= null
    private var index: Int = 0
    private var continc2=0
    private var continc1=0
    private var contP1=0
    private var contP2=0
    private var contP3=0
    private var resultados=""
    private var contRep=0
    private var contRep2=0
    private  var string:String =""
    private val respuestasMatriz = mutableListOf<Pair<Int, String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPlantQuestProtanBinding.inflate(layoutInflater)
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
            if (index==14&&continc1==1)
            {
               // contP1++
                contRep++
                guardar(respuesta,res)
                if(contRep==1)
                    index--
                else
                    index++
            }
            else
                if(index==23&&continc2==1)
                {
                    //contP2++
                    contRep2++
                    guardar(respuesta,res)
                    if(contRep2==1)
                        index--
                    else
                        index++
            }else
            {
                /*when (index) {
                    in 1..14 -> contP1++
                    in 15..23 -> contP2++
                    else -> contP3++
                }*/
                guardar(respuesta,res)
                index++
            }
        }
        else
        {
            res = "Incorrecto"
            if (index==14&&continc1<=1)
            {
                continc1++
                guardar(respuesta,res)
                if (continc1==2)
                    index=15
                else
                    index--
            }else
            if (index==23&&continc2<=1)
            {
                continc2++
                guardar(respuesta,res)
                if (continc2==2)
                    index=24
                else
                    index--
            }else
            if (index<15)
            {
                continc1++
                guardar(respuesta,res)
                if (continc1==2)
                    index=15
                else
                    index++
            }else
            if (index<24)
            {
                continc2++
                guardar(respuesta,res)
                if (continc2==2)
                    index=24
                else
                    index++
            }
            else
            {
                guardar(respuesta,res)
                index++
            }

        }

        bindViews()

    }
    private fun guardar(respuesta:Int,res:String)
    {
        if(index==1)
            string += "Rojo-Verde:\n"
        if(index==15)
            string += "\n\nVioleta-Azul:\n"
        if(index==24)
            string += "\n\nVioleta-Verde:\n"

        string += "Respuesta$index: $res\n"
        //Toast.makeText(this, resultados, Toast.LENGTH_SHORT).show()
        //Toast.makeText(this, " $contP1,$contP2,$contP3", Toast.LENGTH_SHORT).show()
        val preferences = getSharedPreferences("ProtanDeutan", Activity.MODE_PRIVATE)
        val existente = respuestasMatriz.indexOfFirst { it.first == index }

        if (existente != -1) {
            respuestasMatriz[existente] = Pair(index, res)
        } else {
            respuestasMatriz.add(Pair(index, res))
        }

        // Imprimir la matriz para verificación
        contP1=0
        contP2=0
        contP3=0
        respuestasMatriz.forEach { (i, v) ->
            if(v=="Correcto")
                when (i) {
                    in 1..14 -> contP1++
                    in 15..23 -> contP2++
                    else -> contP3++
                }
            //println("Índice: $i, Validación: $v")
        }
        with(preferences.edit()) {
            putString("Respuesta$index", respuesta.toString()).apply()
            putString("Validacion$index", res).apply()
            putString("Completos",string).apply()
            if(index==32||contP1==14)
            {
               // Log.i("contP1",contP1.toString())
               // Log.i("contP2",contP2.toString())
                //Log.i("contP3",contP3.toString())
                if (contP1==14) {
                    putString(
                        "Resultados correctos",
                        "Respuestas Correctas \n $contP1/14 Rojo-Verde"
                    ).apply()
                    resultados = "Persona no daltónica. "
                }
                else
                {
                    putString("Resultados correctos","Respuestas Correctas \n $contP1/14 Rojo-Verde \n" +
                            " $contP2/9 Violeta-Azul\n" +
                            " $contP3/9 Violeta-Verde").apply()

                    resultados = if (contP2<contP3)
                        "Puede que usted tenga deficiencia en la vision con el color Rojo (Protan). "
                    else
                        "Puede que usted tenga deficiencia en la vision con el color Verde (Deutan). "
                }
                putString("Resultados",resultados).apply()
            }

        }
    }
    private fun bindViews() {
        if (index==33||contP1==14)
        {
            val intent = Intent(this, TestCompleted::class.java)
            intent.putExtra("nom_variable", resources.getString(R.string.nomtest_PD))
            startActivity(intent)
            finish()
        }
        else{
            val question=questions!!["question$index"]
            question?.let {
                mBinding.Imagen.setImageResource(selImage(question.imagen))
            }
        }
    }

    private fun setUpFireStore() {

        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        firestore.collection("quizzes").whereEqualTo("title",resources.getString(R.string.nomtest_PD))
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
            "img1" -> return  R.drawable.protan1
            "img2" -> return  R.drawable.protan2
            "img3" -> return  R.drawable.protan3
            "img4" -> return  R.drawable.protan4
            "img5" -> return  R.drawable.protan5
            "img6" -> return  R.drawable.protan6
            "img7" -> return  R.drawable.protan7
            "img8" -> return  R.drawable.protan8
            "img9" -> return  R.drawable.protan9
            "img10" -> return  R.drawable.protan10
            "img11" -> return  R.drawable.protan11
            "img12" -> return  R.drawable.protan12
            "img13" -> return  R.drawable.protan13
            "img14" -> return  R.drawable.protan14
            "img15" -> return  R.drawable.protan15
            "img16" -> return  R.drawable.protan16
            "img17" -> return  R.drawable.protan17
            "img18" -> return  R.drawable.protan18
            "img19" -> return  R.drawable.protan19
            "img20" -> return  R.drawable.protan20
            "img21" -> return  R.drawable.protan21
            "img22" -> return  R.drawable.protan22
            "img23" -> return  R.drawable.protan23
            "img24" -> return  R.drawable.protan24
            "img25" -> return  R.drawable.protan25
            "img26" -> return  R.drawable.protan26
            "img27" -> return  R.drawable.protan27
            "img28" -> return  R.drawable.protan28
            "img29" -> return  R.drawable.protan29
            "img30" -> return  R.drawable.protan30
            "img31" -> return  R.drawable.protan31
            "img32" -> return  R.drawable.protan32
            else -> 0
        }
    }


}