package com.ale.colibriview.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.Inicio
import com.ale.colibriview.Ussuarios
import com.ale.colibriview.databinding.ActivityDaltonismoEnRiesgoBinding
import com.ale.colibriview.perfil_usuario
import com.ale.colibriview.tipos

class daltonismo_en_riesgo : AppCompatActivity() {
    private lateinit var binding: ActivityDaltonismoEnRiesgoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaltonismoEnRiesgoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.regresar.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraDefinicion.home.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraDefinicion.menutest.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, tipos::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraDefinicion.menuResultados.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Ussuarios::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraDefinicion.usuario.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, perfil_usuario::class.java)
            startActivity(intent)
            finish()
        }
    }
}