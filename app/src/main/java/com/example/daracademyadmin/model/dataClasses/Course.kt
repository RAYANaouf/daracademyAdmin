package com.example.daracademyadmin.model.dataClasses

data class Course(
    val courseId  : String = "" ,
    val teacherId : String = "" ,
    val matiereId : String = "" ,
    val lessons   : List<Lesson> = emptyList()
)
