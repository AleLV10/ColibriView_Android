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


    }
}