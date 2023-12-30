package com.example.daracademy.model.data.dataClasses

import com.google.firebase.Timestamp

data class Message(
    val id         : String     = "",
    val msg        : String     = "",
    val photo      : String     = "",
    val file       : String     = "",
    val person_msg : Boolean    = true,
    val timestamp  : Timestamp? = null // Change this to Timestamp

)
