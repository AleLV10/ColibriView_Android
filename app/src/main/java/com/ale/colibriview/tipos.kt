package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityTiposBinding


class tipos : AppCompatActivity() {
    private lateinit var binding: ActivityTiposBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTiposBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ver.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Ussuarios::class.java)
            startActivity(intent)
            finish()
        }

        binding.carrdTest.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, ishijara::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnBarraTest.home.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnBarraTest.menutest.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, tipos::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnBarraTest.menuResultados.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Ussuarios::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnBarraTest.usuario.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this,perfil_usuario::class.java)
            startActivity(intent)
            finish()
        }
    }
}