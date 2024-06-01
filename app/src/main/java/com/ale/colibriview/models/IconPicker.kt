package com.ale.colibriview.models

import com.ale.colibriview.R

object IconPicker{
    private val icons= arrayOf(
        R.drawable.ishihara,
        R.drawable.protan,
        R.drawable.tritan,
        R.drawable.lantern,
    )

    fun getIcon(position:Int):Int{
        return icons[position]
    }
}