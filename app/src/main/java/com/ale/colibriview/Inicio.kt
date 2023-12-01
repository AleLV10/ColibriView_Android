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
    private fun getInitio(): MutableList<Initio>{
        val cards= mutableListOf<Initio>()
        val Que_es= Initio("¿Que es?",
            "El daltonismo es una afección en la cual no se pueden ver algunos colores...")
        val Tipos= Initio("Tipos",
            "El daltonismo puede presentarse en diferentes modalidades, en funcion al tipo...")
        val Causas= Initio("Causas",
            "la mayoria de las personas que tienen daltonismo nacen con esta condicion...")
        val Riesgo= Initio("Riesgo",
            "Los hombres tienen un riesgo mucho mayor de nacer con un tipo de daltonismo...")

        cards.add(Que_es)
        cards.add(Tipos)
        cards.add(Causas)
        cards.add(Riesgo)
        return cards
    }

    override fun onClick(initio: Initio, valor: Int) {
        if(initio.title=="¿Que es?")
        {
            intent = Intent(this, DefinicionActivity::class.java)
            startActivity(intent)
            finish()
        }
        if(initio.title=="Tipos")
        {
            intent = Intent(this, daltonismo_tipos::class.java)
            startActivity(intent)
            finish()
        }
        if(initio.title=="Causas")
        {
            intent = Intent(this, daltonismo_causas::class.java)
            startActivity(intent)
            finish()
        }
        if(initio.title=="Riesgo")
        {
            intent = Intent(this, daltonismo_en_riesgo::class.java)
            startActivity(intent)
            finish()
        }

    }
}
