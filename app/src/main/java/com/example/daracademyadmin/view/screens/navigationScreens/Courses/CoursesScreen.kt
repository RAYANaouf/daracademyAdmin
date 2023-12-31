package com.example.daracademyadmin.view.screens.navigationScreens.Courses

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import com.example.daracademyadmin.ui.theme.backgroundLight
import com.example.daracademyadmin.view.material.lottie.LottieAnimation_empty
import com.example.daracademyadmin.viewModel.DaracademyAdminViewModel

@Composable
fun CoursesScreen(
    viewModel : DaracademyAdminViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    modifier  : Modifier = Modifier
) {


    val window = LocalView.current.context as Activity

    LaunchedEffect(key1 = window){
        window.window.apply {
            navigationBarColor = backgroundLight.toArgb()
        }
    }


    LottieAnimation_empty()

}

@Preview
@Composable
fun CoursesScreen_preview() {
    CoursesScreen()
}