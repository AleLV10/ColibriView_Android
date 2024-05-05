package com.ale.colibriview

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.adapter.TestsAdapter

import com.ale.colibriview.databinding.ActivityCardsTestsBinding
import com.ale.colibriview.models.Test
import com.ale.colibriview.models.onClickListener
import com.google.firebase.firestore.FirebaseFirestore


class CardsTest : AppCompatActivity(),onClickListener {
    private lateinit var binding: ActivityCardsTestsBinding
    lateinit var testAdapter: TestsAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var firestore: FirebaseFirestore

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

        setUpFireStore()
    }
    private fun startNewActivity(activityClass: Class<*>) {
        binding.mnBarraTest.root.animate().alpha(0f).withEndAction {
            val intent = Intent(this, activityClass)
            startActivity(intent)
            finish()
            binding.mnBarraTest.root.animate().alpha(1f)
        }
    }
    private fun getTests(): MutableList<Test>{
        val tests= mutableListOf<Test>()
        val ish=Test( resources.getString(R.string.nomtest),
            resources.getString(R.string.des_ishihara))
        val prt=Test( resources.getString(R.string.nomtest_PD),
            resources.getString(R.string.des_PD))
        val trt=Test( resources.getString(R.string.nomtest_Tr),
            resources.getString(R.string.des_TR))
        val lat=Test( resources.getString(R.string.nomtest_Tl),
            resources.getString(R.string.des_Tl))
        tests.add(ish)
        tests.add(prt)
        tests.add(trt)
        tests.add(lat)
        return tests
    }
    override fun onClick(test:Test, valor:Int) {
        when (test.title) {
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
    @SuppressLint("NotifyDataSetChanged")
    private fun setUpFireStore() {
        val tests = mutableListOf<Test>()
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if (value == null || error != null) {
                Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA", value.toObjects(Test::class.java).toString())
            tests.clear()
            tests.addAll(value.toObjects(Test::class.java))
           testAdapter.notifyDataSetChanged()

        }

    }

}