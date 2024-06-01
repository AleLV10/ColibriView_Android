package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityInstruccionesBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class Instructions : AppCompatActivity() {

    private lateinit var binding: ActivityInstruccionesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstruccionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val valorRec = intent.getStringExtra("Instructions")

        binding.titulo1.text = valorRec

        val imageTextMap = mapOf(
            resources.getString(R.string.nomtest) to Pair(R.drawable.ishihara, R.string.texto1),
            resources.getString(R.string.nomtest_PD) to Pair(R.drawable.protan, R.string.texto_protan),
            resources.getString(R.string.nomtest_Tr) to Pair(R.drawable.tritan, R.string.texto_tritan),
            resources.getString(R.string.nomtest_Tl) to Pair(R.drawable.lantern, R.string.texto_lantern)
        )

        val pair = imageTextMap[valorRec]
        pair?.let {
            Glide.with(this)
                .load(it.first)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.ti1)

            binding.titulo2.text = resources.getString(it.second)
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
