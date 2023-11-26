package com.ale.colibriview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityTestIshiharaBinding
import com.ale.colibriview.models.Tests
import com.ale.colibriview.models.TestsAdapter

class Test_Ishihara : AppCompatActivity() {
    private lateinit var binding: ActivityTestIshiharaBinding
    private lateinit var adapter: TestsAdapter
    private var quizList= mutableListOf<Tests>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTestIshiharaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        populateDummyData()
        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
       // adapter=QuizAdapter(mutableListOf())
        adapter= TestsAdapter(quizList)
        binding.TestRecyclerView.adapter=adapter
        binding.TestRecyclerView.apply {
            setHasFixedSize(true)
            adapter=adapter
        }
    }

    private fun populateDummyData()
    {
        quizList.add(Tests("Test de Ishihara","Test de Ishihara",
            "El daltonismo es una alteración genética que afecta la capacidad de percibir colores."))
        quizList.add(Tests("Test de Ishihara","Test de Ishihara",
            "El daltonismo es una alteración genética que afecta la capacidad de percibir colores."))
        quizList.add(Tests("Test de Ishihara","Test de Ishihara",
            "El daltonismo es una alteración genética que afecta la capacidad de percibir colores."))
        quizList.add(Tests("Test de Ishihara","Test de Ishihara",
            "El daltonismo es una alteración genética que afecta la capacidad de percibir colores."))

    }
   /* private fun setUpFireStore(){
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference : CollectionReference=firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if (value==null||error !=null)
            {
                Toast.makeText(this,"Error fetching data",Toast.LENGTH_SHORT).show()
                return true
            }
            return@addSnapshotListener
            Log.d("DATA",value.toObjects(Quiz::java).toString())
        }
    }*/
}