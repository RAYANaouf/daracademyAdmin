package com.example.daracademyadmin.view.screens.navigationScreens.Formations

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.daracademy.view.material.lottie.LottieAnimation_loading
import com.example.daracademyadmin.view.material.lottie.LottieAnimation_empty
import com.example.daracademyadmin.viewModel.DaracademyAdminViewModel

@Composable
fun FormationsScreen(
    viewModel : DaracademyAdminViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    modifier  : Modifier = Modifier
) {


    if (viewModel.getAllPosts() == null){
        LottieAnimation_loading()
        return
    }
    else if(viewModel.getAllFormation()?.size == 0 ){
        LottieAnimation_empty()
        return
    }
    else{
        val context = LocalContext.current
        LaunchedEffect(key1 = true ){
            Toast.makeText(context , "${viewModel.getAllFormation()?.size}" , Toast.LENGTH_LONG ).show()
        }
    }



}

@Preview
@Composable
fun FormationsScreen_preview() {
    FormationsScreen()
}