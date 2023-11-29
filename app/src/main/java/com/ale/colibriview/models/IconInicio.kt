package com.ale.colibriview.models

import com.ale.colibriview.R

object IconInicio {

        val icons= arrayOf(
            R.drawable.tipos_img2
        )
        var currentIcon=0
        fun getIcon2():Int{
            currentIcon %= icons.size
            return icons[currentIcon]
        }
}