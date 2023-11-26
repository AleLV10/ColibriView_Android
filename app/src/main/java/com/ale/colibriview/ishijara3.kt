package com.ale.colibriview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityIshijara3Binding

class ishijara3 : AppCompatActivity() {
    private lateinit var binding: ActivityIshijara3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIshijara3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val preferences = getSharedPreferences("Respuestas", Activity.MODE_PRIVATE)

        binding.boton1.setOnClickListener {
            with(preferences.edit()){
                putInt("IR2", 8).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR2",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara4::class.java ))
        }

        binding.boton2.setOnClickListener {
            with(preferences.edit()){
                putInt("IR2", 6).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR2",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara4::class.java ))
        }

        binding.boton3.setOnClickListener {
            with(preferences.edit()){
                putInt("IR2", 0).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR2",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara4::class.java ))
        }

    }
}