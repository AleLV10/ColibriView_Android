package com.ale.colibriview.models

data class Initio(
    var title: String? ="",
    var description:String="",
    var questions: MutableMap<String,QuestionIshihara> = mutableMapOf()
)


