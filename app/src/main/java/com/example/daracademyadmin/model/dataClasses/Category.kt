package com.example.daracademyadmin.model.dataClasses

import com.example.daracademy.model.data.sealedClasses.screens.Screens

data class Category(
    val name : String,
    val img  : Int,
    val screen : String = ""
)
