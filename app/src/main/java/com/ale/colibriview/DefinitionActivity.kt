package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityDefinicionBinding

class DefinitionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDefinicionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefinicionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val valorInitio = intent.getStringExtra("initio")
        binding.queesss.text = valorInitio


        when (valorInitio) {
            resources.getString(R.string.quees) -> {
                binding.dalto.setImageResource(R.drawable.card_quees)
                binding.queesDaltonismo.text = resources.getString(R.string.descripcion)
            }
            resources.getString(R.string.tipos_daltonismo) -> {
                binding.dalto.setImageResource(R.drawable.card_tipos)
                binding.queesDaltonismo.text = resources.getString(R.string.descripcion_Tipos)

            }
            resources.getString(R.string.causas) -> {
                binding.dalto.setImageResource(R.drawable.card_causas)
                binding.queesDaltonismo.text = resources.getString(R.string.causas_daltonismo)
            }
            resources.getString(R.string.personas_riesgo) -> {
                binding.dalto.setImageResource(R.drawable.card_riesgo)
                binding.queesDaltonismo.text = resources.getString(R.string.riesgo_daltonismo)
            }
        }
        
        binding.regresar.setOnClickListener {
            startNewActivity(PagingInitio::class.java)
        }
        binding.mnuBarraDefinicion.home.setOnClickListener {
            startNewActivity(PagingInitio::class.java)
        }

        binding.mnuBarraDefinicion.menutest.setOnClickListener {
            startNewActivity(CardsTest::class.java)
        }

        binding.mnuBarraDefinicion.menuResultados.setOnClickListener {
            startNewActivity(ResultadosActivity::class.java)
        }

        binding.mnuBarraDefinicion.usuario.setOnClickListener {
            startNewActivity(PerilUser::class.java)
        }
    }
    private fun startNewActivity(activityClass: Class<*>) {
        binding.mnuBarraDefinicion.root.animate().alpha(0f).withEndAction {
            val intent = Intent(this, activityClass)
            startActivity(intent)
            finish()
            binding.mnuBarraDefinicion.root.animate().alpha(1f)
        }
    }
}