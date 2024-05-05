package com.ale.colibriview.models

import com.ale.colibriview.R

object IconInitio{

        private val icons= arrayOf(
            //R.drawable.tipos_img2
            R.drawable.card_quees,
            R.drawable.card_tipos,
            R.drawable.card_causas,
            R.drawable.card_riesgo
        )

        fun getIcon2(currentIcon: Int):Int{
            val index = currentIcon % icons.size
            return icons[index]
        }
}