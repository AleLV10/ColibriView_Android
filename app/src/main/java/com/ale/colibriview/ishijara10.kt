package com.ale.colibriview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ale.colibriview.databinding.ActivityIshijara10Binding

class ishijara10 : AppCompatActivity() {

    private lateinit var binding: ActivityIshijara10Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIshijara10Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val preferences = getSharedPreferences("Respuestas", Activity.MODE_PRIVATE)


        binding.boton1.setOnClickListener {
            with(preferences.edit()){
                putInt("IR9", 74).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR9",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,ishijara11::class.java ))
        }

        binding.boton2.setOnClickListener {
            with(preferences.edit()){
                putInt("IR9", 24).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR9",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,ishijara11::class.java ))
        }

        binding.boton3.setOnClickListener {
            with(preferences.edit()){
                putInt("IR9", 0).apply()
            }
            Toast.makeText(this,"${preferences.getInt("IR9",0)}",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,ishijara11::class.java ))
        }
    }
}