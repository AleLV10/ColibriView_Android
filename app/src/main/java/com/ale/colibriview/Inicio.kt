package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import android.view.View.OnClickListener
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityInicioBinding
import com.ale.colibriview.models.onClickListenerInicio

class Inicio : AppCompatActivity() {
    private lateinit var binding: ActivityInicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btntestD.setOnClickListener {
            startNewActivity(Cards_Test::class.java)
        }

        binding.mnuBarraInicio.home.setOnClickListener {
            startNewActivity(Inicio::class.java)
        }

        binding.mnuBarraInicio.menutest.setOnClickListener {
            startNewActivity(Cards_Test::class.java)
        }

        binding.mnuBarraInicio.menuResultados.setOnClickListener {
            startNewActivity(Ussuarios::class.java)
        }

        binding.mnuBarraInicio.usuario.setOnClickListener {
            startNewActivity(perfil_usuario::class.java)
        }

        val login: ImageView = findViewById(R.id.home)
        login.setOnClickListener {
            startNewActivity(Inicio::class.java)
        }
    }

    private fun startNewActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
        finish()
    }
}
