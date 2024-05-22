package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityInstruccionesBinding

class Instructions : AppCompatActivity() {

    private lateinit var binding: ActivityInstruccionesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstruccionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val valorRec = intent.getStringExtra("Instructions")

        binding.titulo1.text = valorRec

        when (valorRec) {
            resources.getString(R.string.nomtest) -> {
                binding.ti1.setImageResource(R.drawable.lamina_num1)
                binding.titulo2.text = resources.getString(R.string.texto1)

            }
            resources.getString(R.string.nomtest_PD) -> {
                binding.ti1.setImageResource(R.drawable.protan1)
                binding.titulo2.text = resources.getString(R.string.texto_protan)
            }
            resources.getString(R.string.nomtest_Tr) -> {
                binding.ti1.setImageResource(R.drawable.tritan1)
                binding.titulo2.text = resources.getString(R.string.texto_tritan)
            }
            resources.getString(R.string.nomtest_Tl) -> {
                binding.ti1.setImageResource(R.drawable.semaforo)
                binding.titulo2.text = resources.getString(R.string.texto_lantern)
            }
        }

        binding.presionaContinuar.setOnClickListener {
            when (valorRec) {
                resources.getString(R.string.nomtest) -> {
                    startNewActivity(PlantQuestionActivity::class.java)
                }
                resources.getString(R.string.nomtest_PD) -> {
                    startNewActivity(PlantQuestProtan::class.java)
                }
                resources.getString(R.string.nomtest_Tr) -> {
                    startNewActivity(PlantQuestTritan::class.java)
                }

                resources.getString(R.string.nomtest_Tl) -> {
                    startNewActivity(PlanQuestTestLantern::class.java)
                }
            }
        }

        binding.mnuBarraIshihara.home.setOnClickListener {
            startNewActivity(PagingInitio::class.java)
        }

        binding.mnuBarraIshihara.menutest.setOnClickListener {
            startNewActivity(CardsTest::class.java)
        }

        binding.mnuBarraIshihara.menuResultados.setOnClickListener {
            startNewActivity(ResultadosActivity::class.java)
        }

        binding.mnuBarraIshihara.usuario.setOnClickListener {
            startNewActivity(PerilUser::class.java)
        }
    }
    private fun startNewActivity(activityClass: Class<*>) {
        binding.mnuBarraIshihara.root.animate().alpha(0f).withEndAction {
            val intent = Intent(this, activityClass)
            startActivity(intent)
            finish()
            binding.mnuBarraIshihara.root.animate().alpha(1f)
        }
    }
}