package com.ale.colibriview

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ale.colibriview.adapter.ResultAdapter
import com.ale.colibriview.databinding.ActivityResultadosBinding
import com.ale.colibriview.models.OnClickListenerResultados
import com.ale.colibriview.models.Resultados
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ResultadosActivity : AppCompatActivity(), OnClickListenerResultados {
    private lateinit var mBinding: ActivityResultadosBinding
    private var resultad: MutableList<Resultados>? = null
    private lateinit var lista: MutableList<String>
    private lateinit var uid: String


    //var index=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityResultadosBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val recyclerView = mBinding.RecyclerView
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        val spinner: Spinner = mBinding.spinner
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.Tests,
            R.layout.spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                cargarResultados(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                setUpFireStore()
            }
        }
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
        mBinding.mnuBarraDefinicion.menuResultados.setImageResource(R.drawable.ic_result_morado)
    }

    private fun startNewActivity(activityClass: Class<*>) {
        mBinding.mnuBarraDefinicion.root.animate().alpha(0f).withEndAction {
            val intent = Intent(this, activityClass)
            startActivity(intent)
            finish()
            mBinding.mnuBarraDefinicion.root.animate().alpha(1f)
        }
    }

    private fun bindViews() {
        resultad?.let {
            val optionAdapter = ResultAdapter(this, resultad!!, this, lista, resources)
            mBinding.RecyclerView.layoutManager = LinearLayoutManager(this)
            mBinding.RecyclerView.adapter = optionAdapter
            mBinding.RecyclerView.setHasFixedSize(true)
        }
    }

    private fun setUpFireStore() {
        getIDUser()
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        lista = mutableListOf()
        firestore.collection("users").whereEqualTo("ID_usuario", uid)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    lista.add(document.id)
                }
                resultad = result.toObjects(Resultados::class.java)
                bindViews()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    this,
                    "Error al obtener los documentos: $exception",
                    Toast.LENGTH_SHORT
                ).show()

            }
    }
    private fun consultaNormal(test:String) {
        getIDUser()
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        lista = mutableListOf()

        // Consulta Firestore filtrando por ID_usuario y test
        firestore.collection("users")
            .whereEqualTo("ID_usuario", uid)
            .whereEqualTo("Test", test)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    lista.add(document.id)
                }
                resultad = result.toObjects(Resultados::class.java)
                bindViews()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    this,
                    "Error al obtener los documentos: $exception",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
    private fun cargarResultados(opcionSeleccionada: Int) {
        val filtro: String = when (opcionSeleccionada) {
            0 -> ""
            1 -> resources.getString(R.string.nomtest)
            2 -> resources.getString(R.string.nomtest_PD)
            3 -> resources.getString(R.string.nomtest_Tr)
            4 -> resources.getString(R.string.nomtest_Tl)
            else -> ""
        }
        if(filtro=="")
            setUpFireStore()
        else
            consultaNormal(filtro)
    }

    private fun getIDUser() {
        val user = Firebase.auth.currentUser
        user?.let {
            uid = it.uid
        }
    }

    override fun onClick(resultados: Resultados, valor: Int) {
        val dialogView = layoutInflater.inflate(R.layout.activity_usuarios, null)
        mostInformationUsr(dialogView, resultados)

        MaterialAlertDialogBuilder(this)
            .setView(dialogView)
            .show()
    }

    private fun mostInformationUsr(dialogView: View?, resultados: Resultados) {
        val poss: Int = when (resultados.Test) {
            resources.getString(R.string.nomtest) -> 0
            resources.getString(R.string.nomtest_PD) -> 1
            resources.getString(R.string.nomtest_Tr) -> 2
            resources.getString(R.string.nomtest_Tl) -> 3
            else -> 0
        }
        val str = "Nombre: "
        val fecha = "Fecha: "
        val lugar = "Lugar: "
        val edad = "Edad:  "
        val respuestas = "Respuestas:\n\n"
        dialogView?.findViewById<TextView>(R.id.nombre)!!.text = str.plus(resultados.Nombre)
        dialogView.findViewById<TextView>(R.id.nomtest).text = resultados.Test
        dialogView.findViewById<TextView>(R.id.fecha).text = fecha.plus(resultados.Fecha)
        dialogView.findViewById<TextView>(R.id.Lugar).text = lugar.plus(resultados.Lugar)
        dialogView.findViewById<TextView>(R.id.Anos).text = edad.plus(resultados.Edad)
        dialogView.findViewById<TextView>(R.id.correctas).text = resultados.Resultados_correctos
        dialogView.findViewById<TextView>(R.id.diag).text = resultados.Resultados
        dialogView.findViewById<TextView>(R.id.Respuestas).text = respuestas.plus(resultados.Completos)
        if(resultados.Test==resources.getString(R.string.nomtest_Tr))
        {
            if(resultados.Resultados=="Tritanomalía ó Tritanopía extremo")
                dialogView.findViewById<ImageView>(R.id.imagenTritan).setImageResource(R.drawable.extremo)
            else
                if(resultados.Resultados=="Tritanomalía severo")
                    dialogView.findViewById<ImageView>(R.id.imagenTritan).setImageResource(R.drawable.severa)
                else
                    if(resultados.Resultados=="Tritanomalía moderado")
                        dialogView.findViewById<ImageView>(R.id.imagenTritan).setImageResource(R.drawable.moderada)
                    else
                        if(resultados.Resultados=="Tritanomalía leve")
                            dialogView.findViewById<ImageView>(R.id.imagenTritan).setImageResource(R.drawable.leve)
                        else
                            if(resultados.Resultados=="Visión normal")
                                dialogView.findViewById<ImageView>(R.id.imagenTritan).setImageResource(R.drawable.normal)

        }
        else
            dialogView.findViewById<ImageView>(R.id.imagenTritan).visibility = View.GONE

        Glide.with(this)
            .load(com.ale.colibriview.models.IconPicker.getIcon(poss))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .circleCrop()
            .into(dialogView.findViewById(R.id.img))
    }
}

