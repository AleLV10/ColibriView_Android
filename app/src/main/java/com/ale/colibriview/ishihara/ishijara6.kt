package com.ale.colibriview.ishihara

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ale.colibriview.databinding.ActivityIshijara6Binding

class ishijara6 : AppCompatActivity() {

    private lateinit var binding: ActivityIshijara6Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIshijara6Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val preferences = getSharedPreferences("Respuestas", Activity.MODE_PRIVATE)


        binding.boton1.setOnClickListener {
            with(preferences.edit()){
                putInt("IR5", 37).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR5",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara7::class.java ))
        }

        binding.boton2.setOnClickListener {
            with(preferences.edit()){
                putInt("IR5", 57).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR5",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara7::class.java ))
        }

        binding.boton3.setOnClickListener {
            with(preferences.edit()){
                putInt("IR5", 0).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR5",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ishijara7::class.java ))
        }
    }
}