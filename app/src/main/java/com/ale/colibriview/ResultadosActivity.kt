package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ale.colibriview.adapter.ResultAdapter
import com.ale.colibriview.databinding.ActivityResultadosBinding
import com.ale.colibriview.models.OnClickListenerResultados
import com.ale.colibriview.models.Resultados
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ResultadosActivity : AppCompatActivity(), OnClickListenerResultados {
    private lateinit var mBinding:ActivityResultadosBinding
    private var resultad :MutableList<Resultados>? = null
    private lateinit var lista :MutableList<String>
    private lateinit var uid: String


    //var index=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityResultadosBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val recyclerView = mBinding.RecyclerView
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        mBinding.mnuBarraDefinicion.home.setOnClickListener {
            startNewActivity(PagingInitio::class.java)
        }

        mBinding.mnuBarraDefinicion.menutest.setOnClickListener {
            startNewActivity(CardsTest::class.java)
        }

        mBinding.mnuBarraDefinicion.menuResultados.setOnClickListener {
            startNewActivity(ResultadosActivity::class.java)
        }

        mBinding.mnuBarraDefinicion.usuario.setOnClickListener {
            startNewActivity(PerilUser::class.java)
        }
        setUpFireStore()
    }
    private fun startNewActivity(activityClass: Class<*>) {
        // Ocultar la barra de menú antes de iniciar la nueva actividad
        mBinding.mnuBarraDefinicion.root.animate().alpha(0f).withEndAction {
            // Iniciar la nueva actividad después de ocultar la barra de menú
            val intent = Intent(this, activityClass)
            startActivity(intent)
            finish()
            // Mostrar la barra de menú después de que se complete la transición
            mBinding.mnuBarraDefinicion.root.animate().alpha(1f)
        }
    }
    private fun bindViews() {
        resultad?.let {
            val optionAdapter = ResultAdapter(this, resultad!!,this,lista)
            mBinding.RecyclerView.layoutManager = LinearLayoutManager(this)
            mBinding.RecyclerView.adapter= optionAdapter
            mBinding.RecyclerView.setHasFixedSize(true)
        }
    }

    private fun setUpFireStore() {
        getIDUser()
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        lista = mutableListOf()
        Log.i("ID_usuario",uid)
        firestore.collection("users").whereEqualTo("ID_usuario",uid)
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    lista.add(document.id)
                    }
                Log.i("lista", lista.toString())
                resultad=result.toObjects(Resultados::class.java)
                bindViews()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this,"Error al obtener los documentos: $exception" , Toast.LENGTH_SHORT).show()

            }
    }
    private fun getIDUser() {
        val user = Firebase.auth.currentUser
        user?.let {
            uid = it.uid
        }
    }
    override fun onClick(resultados: Resultados, valor: Int) {
        val dialogView=layoutInflater.inflate(R.layout.activity_usuarios,null)
        mostInformationUsr(dialogView,resultados)

        MaterialAlertDialogBuilder(this)
            .setView(dialogView)
            .show()
    }

    private fun mostInformationUsr(dialogView: View?, resultados: Resultados) {
        val str = "Detalle del Test de: "
        dialogView?.findViewById<TextView>(R.id.nombre)!!.text= str.plus(resultados.Nombre)
        dialogView.findViewById<TextView>(R.id.nomtest).text=resultados.Test
        dialogView.findViewById<TextView>(R.id.fecha).text=resultados.Fecha
        dialogView.findViewById<TextView>(R.id.Lugar).text=resultados.Lugar
        dialogView.findViewById<TextView>(R.id.Anos).text=resultados.Edad
        dialogView.findViewById<TextView>(R.id.correctas).text=resultados.ResultadosCorrects
        dialogView.findViewById<TextView>(R.id.diag).text=resultados.Resultados
        dialogView.findViewById<TextView>(R.id.Respuestas).text=resultados.Completos
    }
}