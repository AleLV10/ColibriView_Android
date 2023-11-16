package com.ale.colibriview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ale.colibriview.databinding.ActivityDaltonismoCausasBinding
import com.ale.colibriview.databinding.ActivityDefinicionBinding

class daltonismo_causas : AppCompatActivity() {
    private lateinit var binding: ActivityDaltonismoCausasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaltonismoCausasBinding.inflate(layoutInflater)
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
    }
}