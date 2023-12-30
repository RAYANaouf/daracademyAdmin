package com.example.daracademyadmin.view.screens.navigationScreens.Posts

import android.widget.Toast
import androidx.compose.foundation.LocalIndication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.daracademy.view.material.lottie.LottieAnimation_loading
import com.example.daracademyadmin.view.material.lottie.LottieAnimation_empty
import com.example.daracademyadmin.viewModel.DaracademyAdminViewModel

@Composable
fun PostsScreen(
    viewModel: DaracademyAdminViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    modifier: Modifier = Modifier
) {

    if (viewModel.getAllPosts() == null){
        LottieAnimation_loading()
        return@PostsScreen
    }
    else if(viewModel.getAllPosts()?.size == 0 ){
        LottieAnimation_empty()
        return@PostsScreen
    }
    else{
        val context = LocalContext.current
        LaunchedEffect(key1 = true ){
            Toast.makeText(context , "${viewModel.getAllPosts()?.size}" , Toast.LENGTH_LONG ).show()
        }
    }



}

@Preview
@Composable
fun PostsScreen_preview() {
    PostsScreen()
}