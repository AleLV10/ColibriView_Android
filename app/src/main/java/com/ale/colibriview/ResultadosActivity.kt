package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ale.colibriview.Adapter.ResultadoAdapter
import com.ale.colibriview.databinding.ActivityResultadosBinding
import com.ale.colibriview.models.Resultados
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ResultadosActivity : AppCompatActivity(),OnClickListenerResultados{
    private lateinit var mBinding:ActivityResultadosBinding
    private var resultad :MutableList<Resultados>? = null
    private lateinit var lista :MutableList<String>
    private lateinit var uid: String
    //var index=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityResultadosBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


        mBinding.mnuBarraDefinicion.home.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }
        mBinding.mnuBarraDefinicion.menutest.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, Cards_Test::class.java)
            startActivity(intent)
            finish()
        }
        mBinding.mnuBarraDefinicion.menuResultados.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this, ResultadosActivity::class.java)
            startActivity(intent)
            finish()
        }
        mBinding.mnuBarraDefinicion.usuario.setOnClickListener {
            // Do something in response to button click
            val intent = Intent(this,perfil_usuario::class.java)
            startActivity(intent)
            finish()
        }
        setUpFireStore()
    }
    private fun bindViews() {
        resultad?.let {
            val optionAdapter = ResultadoAdapter(this, resultad!!,this,lista)
            mBinding.RecyclerView.layoutManager = LinearLayoutManager(this)
            mBinding.RecyclerView.adapter= optionAdapter
            mBinding.RecyclerView.setHasFixedSize(true)
        }
    }

    private fun setUpFireStore() {
        getIDUser()
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        lista = mutableListOf()
        firestore.collection("users").whereEqualTo("ID_usuario",uid)
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                   // Toast.makeText(this,"${document.id}",Toast.LENGTH_SHORT).show()
                    lista.add(document.id)
                    }

                Log.d("Lista","$lista")
                resultad=result.toObjects(Resultados::class.java)
                bindViews()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this,"Error al obtener los documentos: $exception" , Toast.LENGTH_SHORT).show()

            }
        /*
            .get()
            .addOnSuccessListener {
                Toast.makeText(this,"It ${it}", Toast.LENGTH_SHORT).show()

                if(it != null && !it.isEmpty)
                {
                    resultados= it
                    Toast.makeText(this,"${it.toObjects(ResultadoUser::class.java)}", Toast.LENGTH_SHORT).show()
                    resultados=resultados!![0]
                    bindViews()

                }
            }*/
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
    override fun onClick(resultados: Resultados, valor: Int) {
        //Log.d("LResultados_correctos", resultados.Resultados_correctos)
        val dialogView=layoutInflater.inflate(R.layout.activity_usuarios,null)
        mostrarInformacionUsr(dialogView,resultados)

        MaterialAlertDialogBuilder(this)
            .setView(dialogView)
            .show()

/*
        val preferences = getSharedPreferences("Usuario_muestra", Activity.MODE_PRIVATE)
       with(preferences.edit()) {
            putString("Resultados_correctos", resultados.Resultados_correctos).apply()

       }





        intent.putExtra("LResultados_correctos", resultados.Resultados_correctos)
        //val x = intent.putExtra("LResultados", resultados.Resultados)
       // intent.putExtra("LTest", "${resultados.Test}")
        //intent.putExtra("LNombre", "Dalet")
        //intent.putExtra("LLugar", "${resultados.Lugar}")
       // intent.putExtra("LFecha", "${resultados.Fecha}")
       // intent.putExtra("LEdad", "${resultados.Edad}")
       // intent.putExtra("LCompletos", "${resultados.Completos}")
        val i = Intent(this,UsuariosActivity::class.java)
        startActivity(i)
        //finish()*/
    }

    private fun mostrarInformacionUsr(dialogView: View?, resultados: Resultados) {
        dialogView?.findViewById<TextView>(R.id.nombre)!!.text="Detalle del Test de: ${resultados.Nombre}"
        dialogView.findViewById<TextView>(R.id.nomtest).text=resultados.Test
        dialogView.findViewById<TextView>(R.id.fecha).text=resultados.Fecha
        dialogView.findViewById<TextView>(R.id.Lugar).text=resultados.Lugar
        dialogView.findViewById<TextView>(R.id.Anos).text=resultados.Edad
        dialogView.findViewById<TextView>(R.id.correctas).text=resultados.Resultados_correctos
        dialogView.findViewById<TextView>(R.id.diag).text=resultados.Resultados
        dialogView.findViewById<TextView>(R.id.Respuestas).text=resultados.Completos
    }
}