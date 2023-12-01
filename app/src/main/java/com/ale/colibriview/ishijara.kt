package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityIshijaraBinding

class ishijara : AppCompatActivity() {

    private lateinit var binding: ActivityIshijaraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIshijaraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.titulo3.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, PlantillaPreguntaActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mnuBarraIshihara.home.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraIshihara.menutest.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Cards_Test::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraIshihara.menuResultados.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Ussuarios::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraIshihara.usuario.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this,perfil_usuario::class.java)
            startActivity(intent)
            finish()
        }
    }
}