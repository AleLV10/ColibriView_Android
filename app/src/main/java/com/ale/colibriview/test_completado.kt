package com.ale.colibriview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityTestCompletadoBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class test_completado : AppCompatActivity() {
    private lateinit var binding: ActivityTestCompletadoBinding
    private var bandera:Boolean=false
    private val TAG = "test_completado"
    private var contador =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestCompletadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getSharedPreferences("Ishihara", Activity.MODE_PRIVATE)
        //Toast.makeText(this,"${preferences.getInt("IR1",0)}", Toast.LENGTH_SHORT).show()

        binding.boton22.setOnClickListener {
            // Do something in response to button click
            if(bandera){
                binding.prueba.setText("")
                bandera=false
            }
            else
            {
                for(i in 1..38){
                    binding.prueba.append("Respuesta $i: ${preferences.getString("Respuesta$i", "Default")}\n")
                }/*
                binding.prueba.append("Respuesta 1: ${preferences.getInt("IR1", 0)}\n")
                binding.prueba.append("Respuesta 2: ${preferences.getInt("IR2", 0)}\n")
                binding.prueba.append("Respuesta 3: ${preferences.getInt("IR3", 0)}\n")
                binding.prueba.append("Respuesta 4: ${preferences.getInt("IR4", 0)}\n")
                binding.prueba.append("Respuesta 5: ${preferences.getInt("IR5", 0)}\n")
                binding.prueba.append("Respuesta 6: ${preferences.getInt("IR6", 0)}\n")
                binding.prueba.append("Respuesta 7: ${preferences.getInt("IR7", 0)}\n")
                binding.prueba.append("Respuesta 8: ${preferences.getInt("IR8", 0)}\n")
                binding.prueba.append("Respuesta 9: ${preferences.getInt("IR9", 0)}\n")
                binding.prueba.append("Respuesta 10: ${preferences.getInt("IR10", 0)}\n")
                bandera=true*/
            }
            for(i in 1..38){
                if(preferences.getString("Correcta$i", "Default")=="Correcto")
                    contador+=1
            }

            with(preferences.edit()) {
                putString("Resultados correctos","$contador/48").apply()
                if(contador<20)
                    putString("Resultados","Daltonico").apply()
                else
                    putString("Resultados","NO Daltonico").apply()
            }
            binding.continuar.setOnClickListener {
                // Do something in response to button click
                addAdaLovelace()
                val intent = Intent(this, ResultadosActivity::class.java)
                startActivity(intent)
                finish()
            }


        }

/*
        binding.mnuBarraCompleto.home.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }

        binding.mnuBarraCompleto.menutest.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, tipos::class.java)
            startActivity(intent)
            finish()
        }
        binding.mnuBarraCompleto.menuResultados.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Ussuarios::class.java)
            startActivity(intent)
            finish()
        }*/


    }
    private fun addAdaLovelace() {
        // [START add_ada_lovelace]
        // Create a new user with a first and last name


        val preferences = getSharedPreferences("Ishihara", Activity.MODE_PRIVATE)
        for(i in 1..38)
            preferences.getString("Correcta$i", "Default")
        val db = Firebase.firestore
        val user = hashMapOf(
            "Test" to preferences.getString("Test", "Test de Ishihara"),
            "Nombre" to preferences.getString("Nombre", "Default"),
            "Edad" to preferences.getString("Edad", "Default"),
            "Lugar" to preferences.getString("Lugar", "Default"),
            "Fecha" to preferences.getString("Fecha", "Default"),
            "Resultados correctos" to preferences.getString("Resultados correctos", "Default"),
            "Resultados" to preferences.getString("Resultados", "Default"),
        )
        val r2use = hashMapOf(
            "Resultados correctos" to "Ada",
            "last" to user,
            "born" to 1815,
        )

        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
        // [END add_ada_lovelace]
    }
}