package com.ale.colibriview

import com.ale.colibriview.models.Question

interface OnClickListenerQuestion {
    fun onClick(question: Question, valor: Int)
}