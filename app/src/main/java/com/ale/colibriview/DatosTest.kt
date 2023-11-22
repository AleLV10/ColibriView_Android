package com.ale.colibriview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class DatosTest : AppCompatActivity() {
    private lateinit var dropdown:Spinner
    private var items:IntRange = 8.rangeTo(80)
    private lateinit var adapter:ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_test)

        val spinner = findViewById<Spinner>(R.id.spinner1)
        val items = 8.rangeTo(80)
        /*val adapter = ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            items
        )*/
        spinner.setAdapter(adapter)
    }
}