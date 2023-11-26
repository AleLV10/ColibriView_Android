package com.ale.colibriview.ishihara

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ale.colibriview.databinding.ActivityIshijara8Binding

class ishijara8 : AppCompatActivity() {

    private lateinit var binding: ActivityIshijara8Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIshijara8Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val preferences = getSharedPreferences("Respuestas", Activity.MODE_PRIVATE)


        binding.boton1.setOnClickListener {
            with(preferences.edit()){
                putInt("IR7", 7).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR7",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara9::class.java ))
        }

        binding.boton2.setOnClickListener {
            with(preferences.edit()){
                putInt("IR7", 5).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR7",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara9::class.java ))
        }

        binding.boton3.setOnClickListener {
            with(preferences.edit()){
                putInt("IR7", 0).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR7",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara9::class.java ))
        }
    }
}