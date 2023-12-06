package com.ale.colibriview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityIshijara2Binding

class ishijara2 : AppCompatActivity() {
    private lateinit var binding: ActivityIshijara2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIshijara2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val preferences = getSharedPreferences("Respuestas", Activity.MODE_PRIVATE)

        binding.boton1.setOnClickListener {
            with(preferences.edit()){
                putInt("IR1", 12).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR1",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara2::class.java ))
        }

        binding.boton2.setOnClickListener {
            with(preferences.edit()){
                putInt("IR1", 8).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR1",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara2::class.java ))
        }

        binding.boton3.setOnClickListener {
            with(preferences.edit()){
                putInt("IR1", 0).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR1",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara2::class.java ))
        }




/*
        binding.boton21.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, test_completado::class.java)
            startActivity(intent)
            finish()
        }

        binding.boton31.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, test_completado::class.java)
            startActivity(intent)
            finish()
        }

 */
        binding.mnuBarraIshihara2.home.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraIshihara2.menutest.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Cards_Test::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraIshihara2.menuResultados.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, ResultadosActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraIshihara2.usuario.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, perfil_usuario::class.java)
            startActivity(intent)
            finish()
        }
    }
}