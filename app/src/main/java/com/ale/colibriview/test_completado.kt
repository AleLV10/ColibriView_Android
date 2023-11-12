package com.ale.colibriview

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.ale.colibriview.databinding.ActivityTestCompletadoBinding

class test_completado : AppCompatActivity() {



    private lateinit var binding: ActivityTestCompletadoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_completado)

        binding = ActivityTestCompletadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.boton12.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, tipos::class.java)
            startActivity(intent)
            finish()
        }
        binding.boton22.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Ussuarios::class.java)
            startActivity(intent)
            finish()
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