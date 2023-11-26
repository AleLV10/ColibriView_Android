package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.databinding.ActivityCardsTestsBinding
import com.ale.colibriview.models.Test
import com.ale.colibriview.models.TestsAdapter
import com.ale.colibriview.models.onClickkListener

class Cards_Test : AppCompatActivity(),onClickkListener {
    private lateinit var binding: ActivityCardsTestsBinding
    private lateinit var TestAdapter: TestsAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private var quizList= mutableListOf<Test>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCardsTestsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        TestAdapter= TestsAdapter(getTests(),this)
        linearLayoutManager= LinearLayoutManager(this)

        binding.TestRecyclerView.apply {
            layoutManager=linearLayoutManager
            adapter=TestAdapter
        }

        binding.ver.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Ussuarios::class.java)
            startActivity(intent)
            finish()
        }

        binding.mnBarraTest.home.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnBarraTest.menutest.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Cards_Test::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnBarraTest.menuResultados.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Ussuarios::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnBarraTest.usuario.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this,perfil_usuario::class.java)
            startActivity(intent)
            finish()
        }

    }
    private fun getTests(): MutableList<Test>{
        val tests= mutableListOf<Test>()
        val Ishihara=Test("Test de Ishihara",
            "El daltonismo es una alteración genética que afecta la capacidad de percibir colores.")
        val Protan=Test("Test de Protan y Deutan",
            "Las personas con este problema pueden tener distintos grados de dificultad en su detección.")
        val Tritan=Test("Test de Tritan",
            "El daltonismo es una alteración genética que afecta la capacidad de percibir colores.")
        val Latern=Test("Test de Lantern",
            "Las personas con este problema pueden tener distintos grados de dificultad en su detección.")

        tests.add(Ishihara)
        tests.add(Protan)
        tests.add(Tritan)
        tests.add(Latern)
        return tests
    }
    override fun onClic(test:Test,valor:Int) {
        if(test.title=="Test de Ishihara")
        {
            intent = Intent(this, DatosTest::class.java)
            startActivity(intent)
            finish()
        }
        if(test.title=="Test de Protan y Deutan")
        {

        }
        if(test.title=="Test de Tritan")
        {

        }
        if(test.title=="Test de Lantern")
        {
            intent = Intent(this, test_Lantern::class.java)
            startActivity(intent)
            finish()
        }

    }
  /*  private fun setUpFireStore(){
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference : CollectionReference=firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if (value==null||error !=null)
            {
                Toast.makeText(this,"Error fetching data",Toast.LENGTH_SHORT).show()
                return true
            }
            return@addSnapshotListener
            Log.d("DATA",value.toObjects(Tests::java).toString())
        }
    }*/
}