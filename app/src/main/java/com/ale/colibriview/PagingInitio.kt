package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.adapter.InitioAdapter
import com.ale.colibriview.databinding.ActivityInicioBinding
import com.ale.colibriview.models.Initio
import com.ale.colibriview.models.onClickListenerInicio

class PagingInitio : AppCompatActivity(), onClickListenerInicio {
    private lateinit var binding: ActivityInicioBinding
   private lateinit var initioAdapter: InitioAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

       initioAdapter = InitioAdapter(getInitio(), this)
        linearLayoutManager = LinearLayoutManager(this)

        binding.InicioRecyclerView.apply {
            layoutManager = linearLayoutManager
           adapter = initioAdapter
        }

        binding.btntestD.setOnClickListener {
            startNewActivity(CardsTest::class.java)
        }

        binding.mnuBarraInicio.home.setOnClickListener {
            startNewActivity(PagingInitio::class.java)
        }

        binding.mnuBarraInicio.menutest.setOnClickListener {
            startNewActivity(CardsTest::class.java)
        }

        binding.mnuBarraInicio.menuResultados.setOnClickListener {
            startNewActivity(ResultadosActivity::class.java)
        }

        binding.mnuBarraInicio.usuario.setOnClickListener {
            startNewActivity(PerilUser::class.java)
        }

        val login: ImageView = findViewById(R.id.home)
        login.setOnClickListener {
            startNewActivity(PagingInitio::class.java)
        }
    }

    private fun startNewActivity(activityClass: Class<*>) {
        binding.mnuBarraInicio.root.animate().alpha(0f).withEndAction {
            val intent = Intent(this, activityClass)
            startActivity(intent)
            finish()
            binding.mnuBarraInicio.root.animate().alpha(1f)
        }
    }

    private fun getInitio(): MutableList<Initio>{
        val cards= mutableListOf<Initio>()
        val que= Initio(resources.getString(R.string.quees),
            resources.getString(R.string.descripcion))
        val tips= Initio(resources.getString(R.string.tipos_daltonismo),
            resources.getString(R.string.descripcion_Tipos))
        val causes= Initio(resources.getString(R.string.causas),
            resources.getString(R.string.causas_daltonismo))
        val rie= Initio(resources.getString(R.string.personas_riesgo),
            resources.getString(R.string.riesgo_daltonismo))

        cards.add(que)
        cards.add(tips)
        cards.add(causes)
        cards.add(rie)
        return cards
    }

    override fun onClick(initio: Initio, valor: Int) {
        val title = initio.title ?: return
        when (title) {
            resources.getString(R.string.quees) -> launchDefActivity(resources.getString(R.string.quees))
            resources.getString(R.string.tipos_daltonismo) -> launchDefActivity(resources.getString(R.string.tipos_daltonismo))
            resources.getString(R.string.causas) -> launchDefActivity(resources.getString(R.string.causas))
            resources.getString(R.string.personas_riesgo) -> launchDefActivity(resources.getString(R.string.personas_riesgo))
        }
    }

    private fun launchDefActivity(title: String) {
        val intent = Intent(this, DefinitionActivity::class.java)
        intent.putExtra("initio", title)
        startActivity(intent)
    }
}
