package com.ale.colibriview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ale.colibriview.databinding.ActivityIshijara5Binding

class ishijara5 : AppCompatActivity() {
    private lateinit var binding: ActivityIshijara5Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIshijara5Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val preferences = getSharedPreferences("Respuestas", Activity.MODE_PRIVATE)

        binding.boton1.setOnClickListener {
            with(preferences.edit()){
                putInt("IR4", 6).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR4",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara6::class.java ))
        }

        binding.boton2.setOnClickListener {
            with(preferences.edit()){
                putInt("IR4", 29).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR4",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara6::class.java ))
        }

        binding.boton3.setOnClickListener {
            with(preferences.edit()){
                putInt("IR4", 0).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR4",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara6::class.java ))
        }
    }
}