package com.ale.colibriview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.Adapter.InicioAdapter
import com.ale.colibriview.databinding.ActivityCardsTestsBinding
import com.ale.colibriview.databinding.ActivityInicioBinding
import com.ale.colibriview.models.Test
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

      /*  InicioAdapter= InicioAdapter(getInicio(),this)

       */
        linearLayoutManager= LinearLayoutManager(this)

        binding.InicioRecyclerView.apply {
            layoutManager=linearLayoutManager
            adapter=InicioAdapter
        }

        binding.mnuBarraInicio.home.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
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

 /*   private fun getInicio(): MutableList<Inicio>{
       val Inicios= mutableListOf<Inicio>()

        val Que_es= Inicio("¿Que es?",
            "El daltonismo es una afeccion en la cual no se pueden ver los colores...")
        val Tipos= Inicio("Tipos",
            "El daltonismo puede presentarse en diferentes modalidades...")
        val Causas= Inicio("Causas",
            "La mayoria de las personas que tienen daltonismo nacen con la condicion...")
        val Riesgo= Inicio("¿Quienes estan en riesgo?",
            "Los hombres tienen un riesgo mucho mayor de nacer con esta condicion...")

        Inicios.add(Que_es)
        Inicios.add(Tipos)
        Inicios.add(Causas)
        Inicios.add(Riesgo)


        return Inicios


    }

  */
    override fun onClick(inicio: Inicio,valor:Int) {
        if(inicio.title=="¿Que es?")
        {
            intent = Intent(this, DefinicionActivity::class.java)
            startActivity(intent)
            finish()
        }
        if(inicio.title=="Tipos")
        {
            intent = Intent(this, daltonismo_tipos::class.java)
            startActivity(intent)
            finish()
        }
        if(inicio.title=="Causas")
        {
            intent = Intent(this, daltonismo_causas::class.java)
            startActivity(intent)
            finish()
        }
        if(inicio.title=="¿Quienes estan en riesgo?")
        {
            intent = Intent(this, daltonismo_en_riesgo::class.java)
            startActivity(intent)
            finish()
        }

    }
    private fun setUpFireStore() {
        val inicios = mutableListOf<Inicio>()
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if (value == null || error != null) {
                Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA", value.toObjects(Inicio::class.java).toString())
            inicios.clear()
            inicios.addAll(value.toObjects(Inicio::class.java))
            //TestsAdapterr.notifyDataSetChanged()
        }

    }
}