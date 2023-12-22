package com.example.daracademyadmin.model.dataClasses.apis.progress

data class ProgressUpload(
    val id       : String = "",
    val type     : PrograssType,
    val failed   : Boolean = false,
    var progress : List<Float>  = emptyList()
)
