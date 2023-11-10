package com.ale.colibriview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val ingresar: Button = findViewById(R.id.ingresar)
        ingresar.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }

        val olvida: TextView = findViewById(R.id.olvida)
        olvida.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, restablecer::class.java)
            startActivity(intent)
            finish()
        }
    }
}