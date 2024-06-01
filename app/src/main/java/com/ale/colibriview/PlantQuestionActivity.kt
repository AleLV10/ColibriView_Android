package com.ale.colibriview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ale.colibriview.adapter.OptionAdapter
import com.ale.colibriview.databinding.ActivityPlantillaPreguntaBinding
import com.ale.colibriview.models.OnClickListenerQuestionIshihara
import com.ale.colibriview.models.QuestionIshihara
import com.ale.colibriview.models.TestIshihara
import com.google.firebase.firestore.FirebaseFirestore

class PlantQuestionActivity : AppCompatActivity(), OnClickListenerQuestionIshihara {
    private lateinit var mBinding: ActivityPlantillaPreguntaBinding
    private var testIshiharas: MutableList<TestIshihara>? = null
    private var questions: MutableMap<String, QuestionIshihara>? = null
    private var index: Int = 0
    private var visionDefRojoVerde = 0
    private var mentira = 0
    private var protanopiaProtanomalia = 0
    private var deuteranopiaDeuteranomalia = 0
    private var sinVisionColor = 0
    private var correctAnswers = 0
    private var incorrectAnswers = 0
    private var result: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPlantillaPreguntaBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        index = intent.getIntExtra("Index", index)
        val valor: String? = intent.getStringExtra("Validacion")
        val valor2: String? = intent.getStringExtra("Respuesta")
        //var string: String

       // Toast.makeText(this, valor.toString(), Toast.LENGTH_SHORT).show()
        if (valor == "Incorrecto") {
            incorrectAnswers++
            when (index) {
                1 -> if (valor2 == "nada") {
                    sinVisionColor++
                    //"Sin vision de color."
                } else {
                    mentira++
                   // "Mentira"
                }
                in 2..9 ->  if (valor2 == "nada") {
                    sinVisionColor++
                    //"Sin vision de color."
                } else {
                    visionDefRojoVerde++
                    //"Visión con deficiencia rojo–verde."
                }
                in 10..17 -> {
                    visionDefRojoVerde++
                    //string = "Visión con deficiencia rojo–verde."
                }
                18 -> if (valor2 == "5") {
                    visionDefRojoVerde++
                    //"Visión con deficiencia rojo–verde."
                } else {
                    mentira++
                   // "Mentira"
                }
                19 -> if (valor2 == "2") {
                    visionDefRojoVerde++
                    //"Visión con deficiencia rojo–verde."
                } else {
                    mentira++
                    //"Mentira"
                }
                20 ->  if (valor2 == "45") {
                    visionDefRojoVerde++
                    //"Visión con deficiencia rojo–verde."
                } else {
                    mentira++
                   // "Mentira"
                }
                21 -> if (valor2 == "45") {
                    visionDefRojoVerde++
                   // "Visión con deficiencia rojo–verde."
                } else {
                    mentira++
                   // "Mentira"
                }
                22 ->  if (valor2 == "6") {
                    protanopiaProtanomalia++
                   // "Visión con Protanomalía (rojo débil) o Protanopía (rojo nulo)."
                } else {
                    deuteranopiaDeuteranomalia++
                    //"Visión con Deuteranomalía (verde débil) o Deuteranopía (verde nulo)."
                }
                23 ->if (valor2 == "2") {
                    protanopiaProtanomalia++
                   // "Visión con Protanomalía (rojo débil) o Protanopía (rojo nulo)."
                } else {
                    deuteranopiaDeuteranomalia++
                    //"Visión con Deuteranomalía (verde débil) o Deuteranopía (verde nulo)."
                }
                24 -> if (valor2 == "5") {
                    protanopiaProtanomalia++
                    //"Visión con Protanomalía (rojo débil) o Protanopía (rojo nulo)."
                } else {
                    deuteranopiaDeuteranomalia++
                    //"Visión con Deuteranomalía (verde débil) o Deuteranopía (verde nulo)."
                }
                25 -> if (valor2 == "6") {
                    protanopiaProtanomalia++
                    ///"Visión con Protanomalía (rojo débil) o Protanopía (rojo nulo)."
                } else {
                    deuteranopiaDeuteranomalia++
                    //"Visión con Deuteranomalía (verde débil) o Deuteranopía (verde nulo)."
                }
                in 26..27 ->  if (valor2 == "Línea púrpura") {
                    protanopiaProtanomalia++
                    //"Visión con Protanomalía (rojo débil) o Protanopía (rojo nulo)."
                } else {
                    deuteranopiaDeuteranomalia++
                    //"Visión con Deuteranomalía (verde débil) o Deuteranopía (verde nulo)."
                }
                in 28..29 ->  if (valor2 == "Hay linea clara") {
                    visionDefRojoVerde++
                    //"Visión con deficiencia rojo–verde."
                } else {
                    mentira++
                    //"Mentira"
                }
                in 30..31 -> if (valor2 == "No hay linea") {
                    visionDefRojoVerde++
                    //"Visión con deficiencia rojo–verde."
                } else {
                    mentira++
                    //"Mentira"
                }
                in 32..33 -> {
                    visionDefRojoVerde++
                    //string = "Visión con deficiencia rojo–verde."
                }
                in 34..37 -> if (valor2 == "Línea uniendo la azulada-verde y la púrpura") {
                    visionDefRojoVerde++
                    //"Visión con deficiencia rojo–verde."
                } else {
                    mentira++
                    //"Mentira"
                }
                38 ->  if (valor2 == "No hay linea") {
                    sinVisionColor++
                    //"Sin vision de color."
                } else {
                    mentira++
                    //"Mentira"
                }
                //else -> string = ""
            }
        } else {
            correctAnswers++
        }

        val preferences = getSharedPreferences("Ishihara", Activity.MODE_PRIVATE)

        with(preferences.edit()) {
            putString("Respuesta$index", valor2).apply()
            putString("Validacion$index", valor).apply()
        }

        if (valor != null) {
            index += 1
            if (index == 39) {
                determineResult()
                with(preferences.edit()) {
                    putString("Resultados", result).apply()
                }
                val intent = Intent(this, TestCompleted::class.java)
                intent.putExtra("nom_variable", resources.getString(R.string.nomtest))
                startActivity(intent)
                finish()
            }
        } else {
            index = 1
        }
        setUpFireStore()
    }

    private fun bindViews() {
        val question = questions!!["question$index"]
        question?.let {
            mBinding.Imagen.setImageResource(selImage(question.Imagen))
            val optionAdapter = OptionAdapter(this, question, this, index)
            mBinding.optionList.layoutManager = LinearLayoutManager(this)
            mBinding.optionList.adapter = optionAdapter
            mBinding.optionList.setHasFixedSize(true)
        }
    }

    override fun onClick(option: QuestionIshihara, valor: Int) {
        // Implementar si es necesario
    }

    private fun setUpFireStore() {
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        firestore.collection("quizzes").whereEqualTo("title", resources.getString(R.string.nomtest))
            .get()
            .addOnSuccessListener {
                if (it != null && !it.isEmpty) {
                    testIshiharas = it.toObjects(TestIshihara::class.java)
                    questions = testIshiharas!![0].questions
                    bindViews()
                }
            }
    }

    private fun selImage(img: String): Int {
        return when (img) {
            "is1" -> R.drawable.lamina_num1
            "is2" -> R.drawable.lamina_num2
            "is3" -> R.drawable.lamina_num3
            "is4" -> R.drawable.lamina_num4
            "is5" -> R.drawable.lamina_num5
            "is6" -> R.drawable.lamina_num6
            "is7" -> R.drawable.lamina_num7
            "is8" -> R.drawable.lamina_num8
            "is9" -> R.drawable.lamina_num9
            "is10" -> R.drawable.lamina_num10
            "is11" -> R.drawable.lamina_num11
            "is12" -> R.drawable.lamina_num12
            "is13" -> R.drawable.lamina_num13
            "is14" -> R.drawable.lamina_num14
            "is15" -> R.drawable.lamina_num15
            "is16" -> R.drawable.lamina_num16
            "is17" -> R.drawable.lamina_num17
            "is18" -> R.drawable.lamina_num18
            "is19" -> R.drawable.lamina_num19
            "is20" -> R.drawable.lamina_num20
            "is21" -> R.drawable.lamina_num21
            "is22" -> R.drawable.lamina_num22
            "is23" -> R.drawable.lamina_num23
            "is24" -> R.drawable.lamina_num24
            "is25" -> R.drawable.lamina_num25
            "is26" -> R.drawable.lamina_num26
            "is27" -> R.drawable.lamina_num27
            "is28" -> R.drawable.lamina_num28
            "is29" -> R.drawable.lamina_num29
            "is30" -> R.drawable.lamina_num30
            "is31" -> R.drawable.lamina_num31
            "is32" -> R.drawable.lamina_num32
            "is33" -> R.drawable.lamina_num33
            "is34" -> R.drawable.lamina_num34
            "is35" -> R.drawable.lamina_num35
            "is36" -> R.drawable.lamina_num36
            "is37" -> R.drawable.lamina_num37
            "is38" -> R.drawable.lamina_num38
            else -> 0
        }
    }

    private fun determineResult() {
        val mostFrequentIncorrect = when {
            visionDefRojoVerde >= maxOf(
                mentira,
                protanopiaProtanomalia,
                deuteranopiaDeuteranomalia,
                sinVisionColor
            ) -> "Visión con deficiencia rojo–verde."
            mentira >= maxOf(
                visionDefRojoVerde,
                protanopiaProtanomalia,
                deuteranopiaDeuteranomalia,
                sinVisionColor
            ) -> "Hay cuestionamiento."
            protanopiaProtanomalia >= maxOf(
                visionDefRojoVerde,
                mentira,
                deuteranopiaDeuteranomalia,
                sinVisionColor
            ) -> "Visión con Protanomalía (rojo débil) o Protanopía (rojo nulo)."
            deuteranopiaDeuteranomalia >= maxOf(
                visionDefRojoVerde,
                mentira,
                protanopiaProtanomalia,
                sinVisionColor
            ) -> "Visión con Deuteranomalía (verde débil) o Deuteranopía (verde nulo)."
            else -> "Sin vision de color."
        }

        result = if (incorrectAnswers > correctAnswers) {
            "Daltónico: $mostFrequentIncorrect"
        } else {
            "Persona no daltónica. "
        }

        Toast.makeText(this, result, Toast.LENGTH_LONG).show()
    }
}
