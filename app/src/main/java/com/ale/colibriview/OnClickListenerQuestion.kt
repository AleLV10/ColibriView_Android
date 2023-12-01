package com.ale.colibriview
import com.ale.colibriview.models.Question

interface OnClickListenerQuestion {
    fun onClick(option: Question, valor: Int)
}