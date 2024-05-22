package com.ale.colibriview.models

import com.ale.colibriview.R

object IconPicker{
    private val icons= arrayOf(
        R.drawable.lamina_num1,
        R.drawable.protan1,
        R.drawable.tritan1,
        R.drawable.icontl,
    )

    fun getIcon(position:Int):Int{
        return icons[position]
    }
}