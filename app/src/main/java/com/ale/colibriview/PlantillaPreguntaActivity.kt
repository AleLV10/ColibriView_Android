package com.ale.colibriview

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ale.colibriview.Adapter.OptionAdapter
import com.ale.colibriview.databinding.ActivityPlantillaPreguntaBinding
import com.ale.colibriview.models.Question

class PlantillaPreguntaActivity : AppCompatActivity(), OnClickListenerQuestion {
    private lateinit var mBinding:ActivityPlantillaPreguntaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityPlantillaPreguntaBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        bindViews()
    }

    private fun bindViews() {
        val question = Question(
            "Pregunta",
            "Opcion 1",
            "Opcion 2",
            "Opcion 3",
            "Opcion 2"
        )

        mBinding.Imagen.setImageDrawable(Drawable.createFromPath(question.Imagen))
        val optionAdapter = OptionAdapter(this,question)
        mBinding.optionList.layoutManager = LinearLayoutManager(this)
        mBinding.optionList.adapter= optionAdapter
        mBinding.optionList.setHasFixedSize(true)
    }

    override fun onClick(question: Question, valor: Int) {

    }
}