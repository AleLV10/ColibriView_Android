package com.ale.colibriview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ale.colibriview.databinding.ActivityIshijaraBinding

class ishijara : AppCompatActivity() {

    private lateinit var binding: ActivityIshijaraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIshijaraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.titulo3.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, ishijara2::class.java)
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
            val intent = Intent(this, tipos::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraIshihara.menuResultados.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Ussuarios::class.java)
            startActivity(intent)
            finish()
        }
    }
}