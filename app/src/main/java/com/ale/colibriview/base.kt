package com.ale.colibriview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

class base : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        findViewById<ImageView>(R.id.menu_resultados).setOnClickListener{
            Toast.makeText(this,"Dentro de Resultados",Toast.LENGTH_SHORT).show()
        }

    }
}