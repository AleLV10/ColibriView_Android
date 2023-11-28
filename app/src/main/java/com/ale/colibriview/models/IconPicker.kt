package com.ale.colibriview.models

import com.ale.colibriview.R

object IconPicker{
    val icons= arrayOf(
        R.drawable.seis,
        R.drawable.iconpd,
        R.drawable.icontr,
        R.drawable.icontl
    )
    var currentIcon=0
    fun getIcon():Int{
        currentIcon %= icons.size
        return icons[currentIcon]
    }
}