package com.ale.colibriview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityTestCompletadoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class test_completado : AppCompatActivity() {
    private lateinit var binding: ActivityTestCompletadoBinding
    private var bandera:Boolean=false
    private val TAG = "test_completado"
    private lateinit var auth: FirebaseAuth
    private var contador =0
    private  var string:String =""
    private lateinit var uid: String
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
                contador =0
            }
            else
            {
                for(i in 1..38){
                    string += "Respuesta $i: ${preferences.getString("Respuesta$i", preferences.getString("Respuesta$i",""))}/${preferences.getString("Validacion$i", "Default")}\n"
                     if(preferences.getString("Validacion$i", "Default")=="Correcto")
                        contador+=1
                }
                binding.prueba.setText(string)

                bandera=true
            }

            with(preferences.edit()) {
                putString("Resultados_correctos","$contador/38 Respuestas Correctas").apply()
                if(contador<20)
                    putString("Resultados","Persona daltónica").apply()
                else
                    putString("Resultados","Persona no daltónica").apply()
            }
            binding.continuar.setOnClickListener {
                // Do something in response to button click
                addAdaLovelace()
                val intent = Intent(this, ResultadosActivity::class.java)
                startActivity(intent)
                finish()
            }


        }

    }
    private fun getIDUser() {
        val user = Firebase.auth.currentUser
        user?.let {
            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            uid = it.uid
        }
    }
    private fun addAdaLovelace() {
        // [START add_ada_lovelace]
        // Create a new user with a first and last name
        getIDUser()
        auth = Firebase.auth
        val preferences = getSharedPreferences("Ishihara", Activity.MODE_PRIVATE)
        for(i in 1..39)
            preferences.getString("Correcta$i", "Default")
        val db = Firebase.firestore
        val user = hashMapOf(
            "Test" to preferences.getString("Test", "Test de Ishihara"),
            "ID_usuario" to uid,
            "Nombre" to preferences.getString("Nombre", "Default"),
            "Edad" to preferences.getString("Edad", "Default"),
            "Lugar" to preferences.getString("Lugar", "Default"),
            "Fecha" to preferences.getString("Fecha", "Default"),
            "Resultados_correctos" to preferences.getString("Resultados_correctos", "Default"),
            "Resultados" to preferences.getString("Resultados", "Default"),
            "Completos" to string,
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