package com.ale.colibriview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.ale.colibriview.databinding.ActivityInicioBinding

class Inicio : AppCompatActivity() {
    private lateinit var binding: ActivityInicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.testDaltonismo.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, tipos::class.java)
            startActivity(intent)
            finish()
        }
        binding.carrdque.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, DefinicionActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.carrdtipos.setOnClickListener {
            // Do something in response to button click
            val intent= Intent(this, daltonismo_tipos::class.java)
            startActivity(intent)
            finish()
        }
        binding.carrd3.setOnClickListener {
            // Do something in response to button click
            val intent3 = Intent(this, daltonismo_causas::class.java)
            startActivity(intent3)
            finish()
        }
        binding.carrd4.setOnClickListener {
            // Do something in response to button click
            val intent4 = Intent(this, daltonismo_en_riesgo::class.java)
            startActivity(intent4)
            finish()
        }


        binding.mnuBarra.home.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarra.menutest.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, tipos::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarra.menuResultados.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Ussuarios::class.java)
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