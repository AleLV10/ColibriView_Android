package com.ale.colibriview.models

data class TestProtan(
    var title: String="",
    var questions: MutableMap<String,QuestionProtan> = mutableMapOf(),
)