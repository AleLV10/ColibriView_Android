package com.ale.colibriview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ale.colibriview.databinding.ActivityIshijara11Binding

class ishijara11 : AppCompatActivity() {

    private lateinit var binding: ActivityIshijara11Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIshijara11Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val preferences = getSharedPreferences("Respuestas", Activity.MODE_PRIVATE)

        binding.boton1.setOnClickListener {
            with(preferences.edit()){
                putInt("IR10", 2).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR10",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,test_completado::class.java ))
        }

        binding.boton2.setOnClickListener {
            with(preferences.edit()){
                putInt("IR10", 5).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR10",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,test_completado::class.java ))
        }

        binding.boton3.setOnClickListener {
            with(preferences.edit()){
                putInt("IR10", 0).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR10",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,test_completado::class.java ))
        }
    }
}