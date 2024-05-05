package com.ale.colibriview.models

import com.ale.colibriview.R

object IconPicker{
    private val icons= arrayOf(
        R.drawable.seis,
        R.drawable.iconpd,
        R.drawable.icontr,
        R.drawable.icontl,
    )

    fun getIcon(position:Int):Int{
        return icons[position]
    }
}