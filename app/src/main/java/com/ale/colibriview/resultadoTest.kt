package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityResultadoTestBinding

class resultadoTest : AppCompatActivity() {
    private lateinit var binding: ActivityResultadoTestBinding
    private var titulo:String =""
    private var resultados:String =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        titulo = intent.getStringExtra("Titulo_test").toString()
        resultados = intent.getStringExtra("Resultado_test").toString()

        binding.resultado.text = resultados
        if(resultados=="Persona no daltónica. "||resultados=="Visión normal")
            binding.imagen.setImageResource(R.drawable.daltonicono)
        else
            binding.imagen.setImageResource(R.drawable.daltonico)
        if(titulo==resources.getString(R.string.nomtest_Tr))
        {
            if(resultados=="Tritanomalía ó Tritanopía extremo")
                binding.imagenTritan.setImageResource(R.drawable.extremo)
            else
                if(resultados=="Tritanomalía severo")
                    binding.imagenTritan.setImageResource(R.drawable.severa)
                else
                    if(resultados=="Tritanomalía moderado")
                        binding.imagenTritan.setImageResource(R.drawable.moderada)
                    else
                        if(resultados=="Tritanomalía leve")
                            binding.imagenTritan.setImageResource(R.drawable.leve)
                        else
                            if(resultados=="Visión normal")
                                binding.imagenTritan.setImageResource(R.drawable.normal)

        }
        else
        {
            binding.imagenTritan.visibility = View.GONE
        }
        // Animación para el texto
        val textoFestejoAnim = AnimationUtils.loadAnimation(this, R.anim.texto_festejo_anim)
        binding.completo.startAnimation(textoFestejoAnim)

        // Animación para la imagen
        val imagenFestejoAnim = AnimationUtils.loadAnimation(this, R.anim.imagen_festejo_anim)
        binding.imagen.startAnimation(imagenFestejoAnim)
        binding.continuar.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, ResultadosActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}