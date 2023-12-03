package com.example.daracademyadmin.graphics.screens.navigationScreens.addSupportCourse

import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AddSupportCourseScreen(
    modifier : Modifier = Modifier
) {

    Column(
        modifier = modifier
            .background(Color(parseColor("#f9f9f9")))
    ) {

    }

}

@Preview
@Composable
fun AddSupportCourseScreen_preview() {
    AddSupportCourseScreen()
}