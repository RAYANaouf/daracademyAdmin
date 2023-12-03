package com.example.daracademyadmin.model.data.dataClasses

import com.example.daracademy.model.data.sealedClasses.screens.Screens

data class Category(
    val name : String,
    val img  : Int,
    val screen : String = ""
)
