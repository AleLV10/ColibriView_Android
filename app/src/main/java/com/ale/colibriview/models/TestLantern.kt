package com.ale.colibriview.models

data class TestLantern(
    var title: String="",
    var questions: MutableMap<String,QuestionLantern> = mutableMapOf(),
)