package com.ale.colibriview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.Adapter.InicioAdapter
import com.ale.colibriview.databinding.ActivityInicioBinding
import com.ale.colibriview.models.Initio
import com.ale.colibriview.models.onClickListenerInicio
import com.google.firebase.firestore.FirebaseFirestore

class item_inicio : AppCompatActivity() , onClickListenerInicio {
    private lateinit var binding: ActivityInicioBinding
    private lateinit var InicioAdapter: InicioAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

      InicioAdapter= InicioAdapter(getInicio(),this)


        linearLayoutManager= LinearLayoutManager(this)

        binding.InicioRecyclerView.apply {
            layoutManager=linearLayoutManager
            adapter=InicioAdapter
        }

        binding.mnuBarraInicio.home.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, item_inicio::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraInicio.menutest.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Cards_Test::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraInicio.menuResultados.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Ussuarios::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraInicio.usuario.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this,perfil_usuario::class.java)
            startActivity(intent)
            finish()
        }

        setUpFireStore()
    }


    /*private fun getInicio(): MutableList<Initio>{
       val Inicios= mutableListOf<Initio>()

        val Que_es= Initio("¿Que es?",
            "El daltonismo es una afeccion en la cual no se pueden ver los colores...")
        val Tipos= Initio("Tipos",
            "El daltonismo puede presentarse en diferentes modalidades...")
        val Causas= Initio("Causas",
            "La mayoria de las personas que tienen daltonismo nacen con la condicion...")
        val Riesgo= Initio("¿Quienes estan en riesgo?",
            "Los hombres tienen un riesgo mucho mayor de nacer con esta condicion...")

        Inicios.add(Que_es)
        Inicios.add(Tipos)
        Inicios.add(Causas)
        Inicios.add(Riesgo)


        return Inicios
    }

     */

    private fun getInicio(): MutableList<Initio> {
        return listOf(
            Initio("¿Que es?", "El daltonismo es una afeccion en la cual no se pueden ver los colores..."),
            Initio("Tipos", "El daltonismo puede presentarse en diferentes modalidades..."),
            Initio("Causas", "La mayoría de las personas que tienen daltonismo nacen con la condición..."),
            Initio("¿Quienes estan en riesgo?", "Los hombres tienen un riesgo mucho mayor de nacer con esta condición...")
        ).toMutableList()
    }


    /* override fun onClick(initio: Initio,valor:Int) {
        if(initio.titleI=="¿Que es?")
        {
            intent = Intent(this, DefinicionActivity::class.java)
            startActivity(intent)
            finish()
        }
        if(initio.titleI=="Tipos")
        {
            intent = Intent(this, daltonismo_tipos::class.java)
            startActivity(intent)
            finish()
        }
        if(initio.titleI=="Causas")
        {
            intent = Intent(this, daltonismo_causas::class.java)
            startActivity(intent)
            finish()
        }
        if(initio.titleI=="¿Quienes estan en riesgo?")
        {
            intent = Intent(this, daltonismo_en_riesgo::class.java)
            startActivity(intent)
            finish()
        }

    }

    */

    override fun onClick(initio: Initio, valor: Int) {
        val intent = when (initio.titleI) {
            "¿Que es?" -> Intent(this, DefinicionActivity::class.java)
            "Tipos" -> Intent(this, daltonismo_tipos::class.java)
            "Causas" -> Intent(this, daltonismo_causas::class.java)
            "¿Quienes estan en riesgo?" -> Intent(this, daltonismo_en_riesgo::class.java)
            else -> null
        }

        intent?.let {
            startActivity(it)
            finish()
        }
    }

    private fun setUpFireStore() {
        val inicios = mutableListOf<Initio>()
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if (value == null || error != null) {
                val errorMessage = error?.message ?: "Unknown error"
                Toast.makeText(this, "Error fetching data: $errorMessage", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA", value.toObjects(Initio::class.java).toString())
            inicios.clear()
            inicios.addAll(value.toObjects(Initio::class.java))
            InicioAdapter.notifyDataSetChanged()
        }

    }
}