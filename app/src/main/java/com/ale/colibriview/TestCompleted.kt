package com.ale.colibriview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityTestCompletadoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TestCompleted : AppCompatActivity() {
    private lateinit var binding: ActivityTestCompletadoBinding
    private var bandera:Boolean=false
    private val tag = "test_completado"
    private lateinit var auth: FirebaseAuth
    private var contador =0
    private var cont=0
    private  var string:String =""
    private lateinit var uid: String
    private var titulo:String =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestCompletadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Animación para el texto
        val textoFestejoAnim = AnimationUtils.loadAnimation(this, R.anim.texto_festejo_anim)
        binding.completo.startAnimation(textoFestejoAnim)

        // Animación para la imagen
        val imagenFestejoAnim = AnimationUtils.loadAnimation(this, R.anim.imagen_festejo_anim)
        binding.imagen.startAnimation(imagenFestejoAnim)

        titulo = intent.getStringExtra("nom_variable").toString()
        //Toast.makeText(this, intent.getStringExtra("nom_variable").toString(), Toast.LENGTH_SHORT).show()
        val preferences = when (titulo) {
            resources.getString(R.string.nomtest) -> getSharedPreferences("Ishihara", Activity.MODE_PRIVATE)
            resources.getString(R.string.nomtest_PD) -> getSharedPreferences("ProtanDeutan", Activity.MODE_PRIVATE)
            resources.getString(R.string.nomtest_Tr) -> getSharedPreferences("Titan", Activity.MODE_PRIVATE)
            resources.getString(R.string.nomtest_Tl) -> getSharedPreferences("Lantern", Activity.MODE_PRIVATE)
            else -> throw IllegalArgumentException("Invalid test value: $titulo")
        }
        binding.boton22.setOnClickListener {
            // Do something in response to button click
            if(bandera){
                binding.prueba.setText("")
                bandera=false
                contador =0
            }
            else
            {
                string=""
                if(titulo==resources.getString(R.string.nomtest))
                    for(i in 1..38){
                        string += "Respuesta$i: ${preferences.getString("Respuesta$i", preferences.getString("Respuesta$i",""))}/${preferences.getString("Validacion$i", "Default")}\n"
                         if(preferences.getString("Validacion$i", "Default")=="Correcto")
                            contador+=1
                    }
                if(titulo==resources.getString(R.string.nomtest_Tl))
                    for(i in 1..9)
                        string += "Respuesta$i: ${preferences.getString("Respuesta$i", preferences.getString("Respuesta$i",""))}/${preferences.getString("Validacion$i", "Default")}\n"
                if(titulo==resources.getString(R.string.nomtest_PD))
                    string=preferences.getString("Completos", "Default").toString()
                    //for(i in 1..32)
                        //string += "Respuesta$i: ${preferences.getString("Respuesta$i", preferences.getString("Respuesta$i",""))}/${preferences.getString("Validacion$i", "Default")}\n"
                if(titulo==resources.getString(R.string.nomtest_Tr))
                    string=preferences.getString("Completos", "Default").toString()

                cont = contador
                binding.prueba.setText(string)

                bandera=true
            }
            if(titulo==resources.getString(R.string.nomtest)) {
                with(preferences.edit()) {
                    putString("Resultados correctos","$cont/38 Respuestas Correctas").apply()
                }
            }

            //binding.boton22.isEnabled =  false
            //binding.continuar.isEnabled = true
        }
        binding.continuar.setOnClickListener {
            // Do something in response to button click
            addAdaLovelace()
            val intent = Intent(this, resultadoTest::class.java)
            intent.putExtra("Titulo_test", titulo)
            val preferences = when (titulo) {
                resources.getString(R.string.nomtest) -> getSharedPreferences("Ishihara", Activity.MODE_PRIVATE)
                resources.getString(R.string.nomtest_PD) -> getSharedPreferences("ProtanDeutan", Activity.MODE_PRIVATE)
                resources.getString(R.string.nomtest_Tr) -> getSharedPreferences("Titan", Activity.MODE_PRIVATE)
                resources.getString(R.string.nomtest_Tl) -> getSharedPreferences("Lantern", Activity.MODE_PRIVATE)
                else -> throw IllegalArgumentException("Invalid test value: $titulo")
            }
            intent.putExtra("Resultado_test", preferences.getString("Resultados", "Default"))
            startActivity(intent)
            finish()
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
        val db = Firebase.firestore
        string=""
        getIDUser()
        auth = Firebase.auth
        val preferences = when (titulo) {
            resources.getString(R.string.nomtest) -> getSharedPreferences("Ishihara", Activity.MODE_PRIVATE)
            resources.getString(R.string.nomtest_PD) -> getSharedPreferences("ProtanDeutan", Activity.MODE_PRIVATE)
            resources.getString(R.string.nomtest_Tr) -> getSharedPreferences("Titan", Activity.MODE_PRIVATE)
            resources.getString(R.string.nomtest_Tl) -> getSharedPreferences("Lantern", Activity.MODE_PRIVATE)
            else -> throw IllegalArgumentException("Invalid test value: $titulo")
        }
        if(titulo==resources.getString(R.string.nomtest))
        {
            for(i in 1..38)
                preferences.getString("Correcta$i", "Default")

            for(i in 1..38){
                string += "Respuesta$i: ${preferences.getString("Respuesta$i", preferences.getString("Respuesta$i",""))}/${preferences.getString("Validacion$i", "Default")}\n"
            }
        }
        if(titulo==resources.getString(R.string.nomtest_Tl))
            for(i in 1..9)
                string += "Respuesta$i: ${preferences.getString("Respuesta$i", preferences.getString("Respuesta$i",""))}/${preferences.getString("Validacion$i", "Default")}\n"

        if(titulo==resources.getString(R.string.nomtest_PD))
            string=preferences.getString("Completos", "Default").toString()
            //for(i in 1..32)
            //    string += "Respuesta$i: ${preferences.getString("Respuesta$i", preferences.getString("Respuesta$i",""))}/${preferences.getString("Validacion$i", "Default")}\n"
        if(titulo==resources.getString(R.string.nomtest_Tr))
            string=preferences.getString("Completos", "Default").toString()

        val user = hashMapOf(
            "Test" to preferences.getString("Test", titulo),
            "ID_usuario" to uid,
            "Nombre" to preferences.getString("Nombre", "Default"),
            "Edad" to preferences.getString("Edad", "Default"),
            "Lugar" to preferences.getString("Lugar", "Default"),
            "Fecha" to preferences.getString("Fecha", "Default"),
            "Resultados_correctos" to preferences.getString("Resultados correctos", "Default"),
            "Resultados" to preferences.getString("Resultados", "Default"),
            "Completos" to string,
        )


        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(tag, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(tag, "Error adding document", e)
            }
        // [END add_ada_lovelace]
    }
}