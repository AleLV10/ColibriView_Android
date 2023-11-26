package com.ale.colibriview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ale.colibriview.databinding.ActivityIshijara4Binding

class ishijara4 : AppCompatActivity() {
    private lateinit var binding: ActivityIshijara4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIshijara4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val preferences = getSharedPreferences("Repuestas",Activity.MODE_PRIVATE)

        binding.boton1.setOnClickListener {
            with(preferences.edit()){
                putInt("IR3", 9).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR3",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara5::class.java ))
        }

        binding.boton2.setOnClickListener {
            with(preferences.edit()){
                putInt("IR3", 6).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR3",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara5::class.java ))
        }

        binding.boton3.setOnClickListener {
            with(preferences.edit()){
                putInt("IR3", 0).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR3",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara5::class.java ))
        }
    }
}