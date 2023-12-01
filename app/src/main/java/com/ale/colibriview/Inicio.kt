package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.Adapter.InicioAdapter
import com.ale.colibriview.databinding.ActivityInicioBinding
import com.ale.colibriview.models.Initio
import com.ale.colibriview.models.onClickListenerInicio

class Inicio : AppCompatActivity(), onClickListenerInicio {
    private lateinit var binding: ActivityInicioBinding
    private lateinit var InicioAdapter: InicioAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        InicioAdapter= InicioAdapter(getInitio(),this)
        linearLayoutManager= LinearLayoutManager(this)

        binding.InicioRecyclerView.apply {
            layoutManager=linearLayoutManager
            adapter=InicioAdapter
        }

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
            startNewActivity(ResultadosActivity::class.java)
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
    private fun getInitio(): MutableList<Initio>{
        val cards= mutableListOf<Initio>()
        val Ishihara= Initio("Test de Ishihara",
            "El daltonismo es una alteración genética que afecta la capacidad de percibir colores.")
        val Protan= Initio("Test de Protan y Deutan",
            "Las personas con este problema pueden tener distintos grados de dificultad en su detección.")
        val Tritan= Initio("Test de Tritan",
            "El daltonismo es una alteración genética que afecta la capacidad de percibir colores.")
        val Latern= Initio("Test de Lantern",
            "Las personas con este problema pueden tener distintos grados de dificultad en su detección.")

        cards.add(Ishihara)
        cards.add(Protan)
        cards.add(Tritan)
        cards.add(Latern)
        return cards
    }

    override fun onClick(initio: Initio, valor: Int) {
        if(initio.title=="Test de Ishihara")
        {
            intent = Intent(this, DatosTest::class.java)
            startActivity(intent)
            finish()
        }
        if(initio.title=="Test de Protan y Deutan")
        {

        }
        if(initio.title=="Test de Tritan")
        {

        }
        if(initio.title=="Test de Lantern")
        {
            intent = Intent(this, test_Lantern::class.java)
            startActivity(intent)
            finish()
        }

    }
}
