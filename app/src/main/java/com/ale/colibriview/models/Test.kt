package com.ale.colibriview.models

data class Test(
    var title: String="",
    var description:String="",
    var questions: MutableMap<String,Question> = mutableMapOf()

)