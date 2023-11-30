package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityInicioBinding

class Inicio : AppCompatActivity() {
    private lateinit var binding: ActivityInicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btntestD.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Cards_Test::class.java)
            startActivity(intent)
            finish()
        }

        binding.mnuBarraInicio.home.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraInicio.menutest.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Cards_Test::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraInicio.menuResultados.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Ussuarios::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraInicio.usuario.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this,perfil_usuario::class.java)
            startActivity(intent)
            finish()
        }
        val login: ImageView = findViewById(R.id.home)
        login.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }
    }

}