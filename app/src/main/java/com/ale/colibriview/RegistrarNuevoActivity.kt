package com.ale.colibriview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ale.colibriview.databinding.ActivityRegistrarNuevoBinding

class RegistrarNuevoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrarNuevoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarNuevoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ingresar.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }

        binding.olvida.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, restablecer::class.java)
            startActivity(intent)
            finish()
        }
    }
}