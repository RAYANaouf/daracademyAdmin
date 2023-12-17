package com.example.daracademyadmin.repo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.daracademyadmin.model.dataClasses.Matiere


object MatieresCollection {

    var primaire_pre        by mutableStateOf<List<Matiere>?>(null)
    var primaire_premiere   by mutableStateOf<List<Matiere>?>(null)
    var primaire_deuxieme   by mutableStateOf<List<Matiere>?>(null)
    var primaire_troisieme  by mutableStateOf<List<Matiere>?>(null)
    var primaire_quatrieme  by mutableStateOf<List<Matiere>?>(null)
    var primaire_cinquieme  by mutableStateOf<List<Matiere>?>(null)
    var primaire_sixieme    by mutableStateOf<List<Matiere>?>(null)

    var cem_premiere   by mutableStateOf<List<Matiere>?>(null)
    var cem_deuxieme   by mutableStateOf<List<Matiere>?>(null)
    var cem_troisieme  by mutableStateOf<List<Matiere>?>(null)
    var cem_quatrieme  by mutableStateOf<List<Matiere>?>(null)
    var cem_bem        by mutableStateOf<List<Matiere>?>(null)


    var lycee_premiere   by mutableStateOf<List<Matiere>?>(null)
    var lycee_deuxieme   by mutableStateOf<List<Matiere>?>(null)
    var lycee_troisieme  by mutableStateOf<List<Matiere>?>(null)
    var lycee_bac        by mutableStateOf<List<Matiere>?>(null)



}