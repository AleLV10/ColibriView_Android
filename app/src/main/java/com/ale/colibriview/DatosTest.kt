package com.ale.colibriview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ale.colibriview.databinding.ActivityDatosTestBinding

class DatosTest : AppCompatActivity() {
    private lateinit var binding:  ActivityDatosTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDatosTestBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}