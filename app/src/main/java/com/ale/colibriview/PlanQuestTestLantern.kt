package com.ale.colibriview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityTestLanternBinding
import com.ale.colibriview.models.QuestionLantern
import com.ale.colibriview.models.TestLantern
import com.google.firebase.firestore.FirebaseFirestore

class PlanQuestTestLantern : AppCompatActivity() {
    private lateinit var mBinding: ActivityTestLanternBinding
    private var testLantern :MutableList<TestLantern>? = null
    private var questions:MutableMap<String, QuestionLantern>?= null
    private var index: Int = 0
    private var contVerde=0
    private var contRojo=0
    private var contAmarillo=0
    private var contador=0
    private var resultados=""
    private fun setUpRadioGroupListeners() {
        mBinding.radioGroupArriba.setOnCheckedChangeListener(null)
        mBinding.radioGroupAbajo.setOnCheckedChangeListener(null)

        mBinding.radioGroupArriba.setOnCheckedChangeListener { _, _ ->
            validateAnswers(true)
        }

        mBinding.radioGroupAbajo.setOnCheckedChangeListener { _, _ ->
            validateAnswers(true)
        }
        mBinding.siguiente.setOnClickListener(){
            validateAnswers(false)
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTestLanternBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        index=1
        setUpFireStore()
        setUpRadioGroupListeners() // Configurar listeners para grupos de radio

        validateAnswers(true) // Llamar a la función para validar las respuestas
    }

    private fun validateAnswers(ban:Boolean) {
        val radioArribaSelected = mBinding.radioGroupArriba.checkedRadioButtonId != -1
        val radioAbajoSelected = mBinding.radioGroupAbajo.checkedRadioButtonId != -1
        var valor: String?=""
        var valor2: String?=""
        if(!ban)
        {
            valor =""
            valor2 =""
            valida(valor,valor2)
        }
        else
        if (radioArribaSelected && radioAbajoSelected) {

            // Desvincular los listeners para evitar que se disparen múltiples veces
            mBinding.radioGroupArriba.setOnCheckedChangeListener(null)
            mBinding.radioGroupAbajo.setOnCheckedChangeListener(null)

            // Si ambas opciones están seleccionadas, proceder con la lógica
            valor = getSelectedRadioButtonText(mBinding.radioGroupArriba)
            valor2= getSelectedRadioButtonText(mBinding.radioGroupAbajo)
            //Toast.makeText(this, index.toString(), Toast.LENGTH_SHORT).show()
            valida(valor.toString(),valor2.toString())

            mBinding.radioGroupArriba.clearCheck()
            mBinding.radioGroupAbajo.clearCheck()

            // Volver a vincular los listeners después de la lógica
            setUpRadioGroupListeners()
        } else {
            // Si no se han seleccionado opciones en ambos grupos de radio, mostrar un mensaje de error
            // (Por ejemplo, un Toast)
            //Toast.makeText(this, "Por favor, selecciona una opción en ambos grupos", Toast.LENGTH_SHORT).show()
        }
    }
    private fun valida(valor:String,valor2:String,)
    {
        val question=questions!!["question$index"]
        val arriba: String
        val abajo: String
        if(valor== question?.Arriba) {
            arriba = "Correcto"
            contador++
        }
        else
        {
            arriba="Incorrecto"
            if(question?.Arriba=="Verde")
                contVerde++
            if(question?.Arriba=="Rojo")
                contRojo++
            if(question?.Arriba=="Amarillo")
                contAmarillo++
        }
        if(valor2== question?.Abajo) {
            abajo = "Correcto"
            contador++
        }
        else
        {
            abajo="Incorrecto"
            if(question?.Abajo=="Verde")
                contVerde++
            if(question?.Abajo=="Rojo")
                contRojo++
            if(question?.Abajo=="Amarillo")
                contAmarillo++
        }
        if(contRojo<=2&&contVerde<=2&&contAmarillo<=2)
            resultados ="Persona no daltónica. "
        else
            resultados ="Persona daltónica. Posible deficiencia al ver: "
        if(contRojo>2)
            resultados+="Rojo (Deuteranomalía o Protanomalía). "
        if(contVerde>2)
            resultados+="Verde (Deuteranopia o protanopia). "
        if(contAmarillo>2)
            resultados+="Amarillo (Trastorno de la visión de color amarillo-azul o Daltonismo generalizado). "

        val preferences = getSharedPreferences("Lantern", Activity.MODE_PRIVATE)
        with(preferences.edit()) {
            putString("Respuesta$index", "Arriba: $valor Abajo: $valor2").apply()
            putString("Validacion$index", "Arriba: $arriba Abajo: $abajo").apply()
            putString("Resultados",resultados).apply()
            putString("Resultados correctos","$contador/18 Respuestas Correctas").apply()
        }
        //Toast.makeText(this, contVerde.toString()+contAmarillo.toString()+contRojo.toString(), Toast.LENGTH_SHORT).show()
        //Toast.makeText(this, "Arriba: $arriba Abajo: $abajo", Toast.LENGTH_SHORT).show()
        if (index == 9) {
            val intent = Intent(this, TestCompleted::class.java)
            intent.putExtra("nom_variable", resources.getString(R.string.nomtest_Tl))
            startActivity(intent)
            finish()
        } else {
            index++
            bindViews()
        }
    }
    private fun getSelectedRadioButtonText(radioGroup: RadioGroup): String? {
        val radioButtonId = radioGroup.checkedRadioButtonId
        if (radioButtonId != -1) {
            val selectedRadioButton: RadioButton = findViewById(radioButtonId)
            return selectedRadioButton.text.toString()
        }
        return null
    }
    private fun bindViews() {
        val handler = Handler(Looper.getMainLooper())
        val question=questions!!["question$index"]
        question?.let {
            mBinding.imagenArriba.setImageResource(selImage(question.Arriba))
            mBinding.imagenAbajo.setImageResource(selImage(question.Abajo))
            handler.postDelayed({
                fadeOutImages()
            }, 500)
            //val LanternAdapter = LanternAdapter(this,question,this,index)
            //mBinding.optionList.layoutManager = LinearLayoutManager(this)
            //mBinding.optionList.adapter= LanternAdapter
            //mBinding.optionList.setHasFixedSize(true)
        }
    }
    private fun fadeOutImages() {
        // Crear animaciones de desvanecimiento para las imágenes
        val fadeOutTop = ObjectAnimator.ofFloat(mBinding.imagenArriba, "alpha", 1f, 0f)
        val fadeOutBottom = ObjectAnimator.ofFloat(mBinding.imagenAbajo, "alpha", 1f, 0f)

        // Configurar duración de la animación
        fadeOutTop.duration = 2000 // Duración en milisegundos
        fadeOutBottom.duration = 2000 // Duración en milisegundos

        // Combinar las animaciones en un conjunto
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(fadeOutTop, fadeOutBottom)

        // Iniciar la animación
        animatorSet.start()
    }

    private fun setUpFireStore() {

        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        firestore.collection("quizzes").whereEqualTo("title",resources.getString(R.string.nomtest_Tl))
            .get()
            .addOnSuccessListener {
                if(it != null && !it.isEmpty)
                {
                    testLantern= it.toObjects(TestLantern::class.java)
                    questions=testLantern!![0].questions
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