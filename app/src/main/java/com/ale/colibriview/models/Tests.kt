package com.ale.colibriview.models

data class Tests(
    var id : String="",
    var title: String="",
    var description:String="",
    var questions: MutableMap<String,Question> = mutableMapOf()

)