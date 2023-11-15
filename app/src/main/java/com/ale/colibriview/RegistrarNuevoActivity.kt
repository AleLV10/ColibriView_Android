package com.ale.colibriview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.ale.colibriview.databinding.ActivityRegistrarNuevoBinding

class RegistrarNuevoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrarNuevoBinding

    //VARIABLES DE LOS DATOS QUE VAMOS A REGISTRAR
    private var name: String = ""
    private var email: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarNuevoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.iniciar.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }

        binding.crear.setOnClickListener {
            // Do something in response to button click
            name=binding.name.toString()
        }
    }
}