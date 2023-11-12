package com.ale.colibriview

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ale.colibriview.databinding.ActivityIshijara2Binding

class ishijara2 : AppCompatActivity() {
    private lateinit var binding: ActivityIshijara2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIshijara2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.boton11.setOnClickListener {
            // Do something in response to button click
            val preferences = getPreferences(Context.MODE_PRIVATE)
            Log.i("resultado","${getString(R.string.numero)}")

            val intent = Intent(this, test_completado::class.java)
            startActivity(intent)
            finish()

        }

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
        binding.mnuBarraIshihara2.home.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraIshihara2.menutest.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, tipos::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraIshihara2.menuResultados.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Ussuarios::class.java)
            startActivity(intent)
            finish()
        }
    }
}