package com.example.daracademyadmin.model.data.variables

import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademyadmin.R
import com.example.daracademyadmin.model.data.dataClasses.Category

val categories = listOf(
    Category(name = "Supports"     , img = R.drawable.support_inline , screen = Screens.AddPostScreen().root),
    Category(name = "Formations"   , img = R.drawable.formation_inline , screen = Screens.AddFormationScreen().root),
    Category(name = "Posts"        , img = R.drawable.post_inline , screen = Screens.AddPostScreen().root),
    Category(name = "Enseignants"  , img = R.drawable.teacher_inline , screen = Screens.AddTeacherScreen().root),
    Category(name = "Etudiants"    , img = R.drawable.student_inline , screen = Screens.AddStudentScreen().root),
)