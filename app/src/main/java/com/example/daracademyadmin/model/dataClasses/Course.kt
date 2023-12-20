package com.example.daracademyadmin.model.dataClasses

data class Course(
    val courseId  : String,
    val teacherId : String ,
    val lessons : List<Lesson>
)
