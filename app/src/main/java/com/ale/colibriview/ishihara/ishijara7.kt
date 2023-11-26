package com.ale.colibriview.ishihara

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ale.colibriview.databinding.ActivityIshijara7Binding

class ishijara7 : AppCompatActivity() {

    private lateinit var binding: ActivityIshijara7Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIshijara7Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val preferences = getSharedPreferences("Respuestas", Activity.MODE_PRIVATE)


        binding.boton1.setOnClickListener {
            with(preferences.edit()){
                putInt("IR6", 7).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR6",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara8::class.java ))
        }

        binding.boton2.setOnClickListener {
            with(preferences.edit()){
                putInt("IR6", 5).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR6",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara8::class.java ))
        }

        binding.boton3.setOnClickListener {
            with(preferences.edit()){
                putInt("IR6", 0).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR6",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara8::class.java ))
        }
    }
}