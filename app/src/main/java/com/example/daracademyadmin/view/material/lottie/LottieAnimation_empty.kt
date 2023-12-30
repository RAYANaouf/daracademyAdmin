package com.example.daracademyadmin.view.material.lottie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.daracademyadmin.R

@Composable
fun LottieAnimation_empty(
    modifier : Modifier = Modifier
) {

    val lottieComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.empty))

    val progress by
    animateLottieCompositionAsState(
        composition = lottieComposition,
        isPlaying = true,
        iterations = Int.MAX_VALUE
    )

    com.airbnb.lottie.compose.LottieAnimation(
        composition = lottieComposition,
        progress = {
            progress
        },
        modifier = modifier
    )

}


@Preview
@Composable
fun LottieAnimation_empty_preview() {
    LottieAnimation_empty()
}