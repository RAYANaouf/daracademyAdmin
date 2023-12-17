package com.example.daracademyadmin.model.dataClasses

data class Formation (
    val name     : String = "",
    val desc     : String = "",
    val imgs     : List<String> = emptyList(),
    val teachers : List<String> = emptyList(),
    val hashtags : List<String> = emptyList()
)