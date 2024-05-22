package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.adapter.TestsAdapter
import com.ale.colibriview.databinding.ActivityCardsTestsBinding
import com.ale.colibriview.models.TestIshihara
import com.ale.colibriview.models.onClickListener


class CardsTest : AppCompatActivity(),onClickListener {
    private lateinit var binding: ActivityCardsTestsBinding
    lateinit var testAdapter: TestsAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCardsTestsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        testAdapter= TestsAdapter(getTests(),this)
        linearLayoutManager= LinearLayoutManager(this)

        binding.TestRecyclerView.apply {
            layoutManager=linearLayoutManager
            adapter= adapter
           adapter=testAdapter
        }

        binding.ver.setOnClickListener {
            startNewActivity(ResultadosActivity::class.java)
        }
        binding.mnBarraTest.home.setOnClickListener {
            startNewActivity(PagingInitio::class.java)
        }

        binding.mnBarraTest.menutest.setOnClickListener {
            startNewActivity(CardsTest::class.java)
        }

        binding.mnBarraTest.menuResultados.setOnClickListener {
            startNewActivity(ResultadosActivity::class.java)
        }

        binding.mnBarraTest.usuario.setOnClickListener {
            startNewActivity(PerilUser::class.java)
        }
        binding.mnBarraTest.menutest.setImageResource(R.drawable.ic_test_morado)
    }
    private fun startNewActivity(activityClass: Class<*>) {
        binding.mnBarraTest.root.animate().alpha(0f).withEndAction {
            val intent = Intent(this, activityClass)
            startActivity(intent)
            finish()
            binding.mnBarraTest.root.animate().alpha(1f)
        }
    }

    private fun getTests(): MutableList<TestIshihara>{
        val testIshiharas= mutableListOf<TestIshihara>()
        val ish=TestIshihara( resources.getString(R.string.nomtest),
            resources.getString(R.string.texto1))
        val prt=TestIshihara( resources.getString(R.string.nomtest_PD),
            resources.getString(R.string.texto_protan))
        val trt=TestIshihara( resources.getString(R.string.nomtest_Tr),
            resources.getString(R.string.texto_tritan))
        val lat=TestIshihara( resources.getString(R.string.nomtest_Tl),
            resources.getString(R.string.texto_lantern))
        testIshiharas.add(ish)
        testIshiharas.add(prt)
        testIshiharas.add(trt)
        testIshiharas.add(lat)
        return testIshiharas
    }
    override fun onClick(testIshihara:TestIshihara, valor:Int) {
        when (testIshihara.title) {
            resources.getString(R.string.nomtest) -> launchDefActivity(resources.getString(R.string.nomtest))
            resources.getString(R.string.nomtest_PD) -> launchDefActivity(resources.getString(R.string.nomtest_PD))
            resources.getString(R.string.nomtest_Tr) -> launchDefActivity(resources.getString(R.string.nomtest_Tr))
            resources.getString(R.string.nomtest_Tl) -> launchDefActivity(resources.getString(R.string.nomtest_Tl))
        }
    }
    private fun launchDefActivity(title: String) {
        val intent = Intent(this, DatTest::class.java)
        intent.putExtra("nom_variable", title)
        startActivity(intent)
    }

}
