package com.ale.colibriview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.ale.colibriview.databinding.ActivityInicioBinding
import com.google.android.material.card.MaterialCardView

class Inicio : AppCompatActivity() {
    private lateinit var binding: ActivityInicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val test_daltonismo : Button= findViewById(R.id.test_daltonismo)
        test_daltonismo.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, tipos::class.java)
            startActivity(intent)
            finish()
        }
        val cardq : MaterialCardView = findViewById(R.id.carrd)
        cardq.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, DefinicionActivity::class.java)
            startActivity(intent)
            finish()
        }
        val cardq1 : MaterialCardView = findViewById(R.id.carrd2)
        cardq1.setOnClickListener {
            // Do something in response to button click
            val intent2 = Intent(this, daltonismo_tipos::class.java)
            startActivity(intent2)
            finish()
        }
        val cardq2 : MaterialCardView = findViewById(R.id.carrd3)
        cardq2.setOnClickListener {
            // Do something in response to button click
            val intent3 = Intent(this, daltonismo_causas::class.java)
            startActivity(intent3)
            finish()
        }
        val cardq3 : MaterialCardView = findViewById(R.id.carrd4)
        cardq3.setOnClickListener {
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