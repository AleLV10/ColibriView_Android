package com.ale.colibriview.models

import com.ale.colibriview.R

object IconPicker{
    val icons= arrayOf(
        R.drawable.icontl,
        R.drawable.seis,
        R.drawable.iconpd,
        R.drawable.icontr,
    )
    var currentIcon=0
    fun getIcon():Int{
        currentIcon = (currentIcon+1)% icons.size
        return icons[currentIcon]
    }
}