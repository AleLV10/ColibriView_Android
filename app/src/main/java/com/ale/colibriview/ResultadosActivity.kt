package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.ale.colibriview.Adapter.ResultadoAdapter
import com.ale.colibriview.databinding.ActivityResultadosBinding
import com.ale.colibriview.models.ResultadoUser
import com.ale.colibriview.models.Resultados
import com.google.firebase.firestore.FirebaseFirestore

class ResultadosActivity : AppCompatActivity(),OnClickListenerResultados{
    private lateinit var mBinding:ActivityResultadosBinding
    private var res :MutableList<ResultadoUser>? = null
    private var resultados:MutableMap<String,Resultados>?= null
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var ResultadoAdapter: ResultadoAdapter
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
        mBinding.RecyclerView.apply {
            layoutManager=linearLayoutManager
            adapter=ResultadoAdapter
        }

    }

    private fun setUpFireStore() {

        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        firestore.collection("quizzes").whereEqualTo("title","Test de Ishihara")
            .get()
            .addOnSuccessListener {
                if(it != null && !it.isEmpty)
                {
                    res= it.toObjects(ResultadoUser::class.java)
                    resultados=res!![0].resultado
                    bindViews()
                }
            }
    }

    override fun onClick(resultados: Resultados, valor: Int) {
        TODO("Not yet implemented")
    }
}