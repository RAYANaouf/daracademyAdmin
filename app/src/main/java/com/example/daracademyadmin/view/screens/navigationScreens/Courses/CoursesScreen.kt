package com.example.daracademyadmin.view.screens.navigationScreens.Courses

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.daracademyadmin.view.material.lottie.LottieAnimation_empty
import com.example.daracademyadmin.viewModel.DaracademyAdminViewModel

@Composable
fun CoursesScreen(
    viewModel : DaracademyAdminViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    modifier  : Modifier = Modifier
) {

    LottieAnimation_empty()

}

@Preview
@Composable
fun CoursesScreen_preview() {
    CoursesScreen()
}