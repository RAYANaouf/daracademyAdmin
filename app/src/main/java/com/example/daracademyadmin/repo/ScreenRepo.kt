package com.example.daracademyadmin.repo

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.daracademy.model.data.sealedClasses.screens.Screens

class ScreenRepo {

    val navController : NavHostController

    var app_screen by mutableStateOf("")
        private set

    constructor(navController: NavHostController){
        this.navController = navController

        navController.addOnDestinationChangedListener{_,destinations,_->
            destinations.route.let {
                if (it != null){
                    app_screen = it.substringBefore('/')
                }
            }
        }

    }


    fun navigate_to_screen( screen : String , vararg params : String ){

        var newScreen = screen
        params.forEach {param->
            newScreen += "/$param"
        }

        navController.navigate(newScreen)
    }



}