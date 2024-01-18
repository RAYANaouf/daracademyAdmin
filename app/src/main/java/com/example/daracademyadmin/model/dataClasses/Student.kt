package com.example.daracademyadmin.model.dataClasses

data class Student(
    val name  : String = "",
    val email : String = "",
    val password  : String = "",
    val phone : Phone   = Phone(),
    val photo : String = "",
    val paid  : Boolean = true
)

