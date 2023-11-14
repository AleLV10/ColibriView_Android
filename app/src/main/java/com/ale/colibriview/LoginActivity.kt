package com.ale.colibriview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ale.colibriview.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
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

        binding.registrarte.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, RegistrarNuevoActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.anonimo.setOnClickListener{

        }
    }
}