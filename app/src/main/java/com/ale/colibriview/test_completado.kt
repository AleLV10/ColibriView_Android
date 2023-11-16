package com.ale.colibriview

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.ale.colibriview.databinding.ActivityTestCompletadoBinding

class test_completado : AppCompatActivity() {
    private lateinit var binding: ActivityTestCompletadoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestCompletadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getSharedPreferences("Respuestas", Activity.MODE_PRIVATE)
        Toast.makeText(this,"${preferences.getInt("IR1",0)}", Toast.LENGTH_SHORT).show()

/*
        binding.boton12.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, tipos::class.java)
            startActivity(intent)
            finish()
        }

 */
        binding.boton22.setOnClickListener {
            // Do something in response to button click
            binding.prueba.append("Respuesta 1: ${preferences.getInt("IR1", 0)}\n")
            binding.prueba.append("Respuesta 2: ${preferences.getInt("IR2", 0)}\n")
            binding.prueba.append("Respuesta 3: ${preferences.getInt("IR3", 0)}\n")
            binding.prueba.append("Respuesta 4: ${preferences.getInt("IR4", 0)}\n")
            binding.prueba.append("Respuesta 5: ${preferences.getInt("IR5", 0)}\n")
            binding.prueba.append("Respuesta 6: ${preferences.getInt("IR6", 0)}\n")
            binding.prueba.append("Respuesta 7: ${preferences.getInt("IR7", 0)}\n")
            binding.prueba.append("Respuesta 8: ${preferences.getInt("IR8", 0)}\n")
            binding.prueba.append("Respuesta 9: ${preferences.getInt("IR9", 0)}\n")
            binding.prueba.append("Respuesta 10: ${preferences.getInt("IR10", 0)}\n")



        }
/*
        binding.mnuBarraCompleto.home.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }

        binding.mnuBarraCompleto.menutest.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, tipos::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraCompleto.menuResultados.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Ussuarios::class.java)
            startActivity(intent)
            finish()
        }*/


    }
}