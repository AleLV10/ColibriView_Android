package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityDatosTestBinding
import java.util.Calendar


class DatosTest : AppCompatActivity() {
    private lateinit var binding:  ActivityDatosTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDatosTestBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = (c.get(Calendar.MONTH)+1)
        val day = c.get(Calendar.DAY_OF_MONTH)
        Log.i("Datos para cuestionario",""+day+"-"+month+"-"+year)
        binding.ContinuarTest.setOnClickListener {
            Log.i("Datos para cuestionario","${binding.name1.text.toString()}")
            Log.i("Datos para cuestionario","${binding.radicas.text.toString()}")
            Log.i("Datos para cuestionario","${binding.edad.text.toString()}")
            if(binding.name1.text.toString()!=""||binding.radicas.text.toString()!=""||binding.edad.text.toString()!="") {
                val intent = Intent(this, PlantillaPreguntaActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

}