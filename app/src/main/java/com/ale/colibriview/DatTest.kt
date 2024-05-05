package com.ale.colibriview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityDatosTestIshiharaBinding
import java.util.Calendar


class DatTest : AppCompatActivity() {
    private lateinit var binding:  ActivityDatosTestIshiharaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDatosTestIshiharaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val preferences = getSharedPreferences("Ishihara", Activity.MODE_PRIVATE)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = (c.get(Calendar.MONTH)+1)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val date = resources.getString(R.string.Fecha)
        val test = intent.getStringExtra("nom_variable")
        val calendar = "$day-$month-$year"
        binding.date.text = date.plus(" ").plus(calendar)
        binding.title.text = test

        binding.ContinuarTest.setOnClickListener {
            with(preferences.edit()){
                putString("Test", test).apply()
                putString("Nombre", binding.name1.text.toString()).apply()
                putString("Lugar", binding.radicas.text.toString()).apply()
                putString("Fecha", calendar).apply()
                putString("Edad", binding.edad.text.toString()).apply()

            }
            if(binding.name1.text.toString()!=""||binding.radicas.text.toString()!=""||binding.edad.text.toString()!="") {
                val intent = Intent(this, Instructions::class.java)
                intent.putExtra("Instructions", test)
                startActivity(intent)
                finish()

            }
        }
        binding.CancelarTest.setOnClickListener {
            val intent = Intent(this, PagingInitio::class.java)
            startActivity(intent)
            finish()
        }

    }

}