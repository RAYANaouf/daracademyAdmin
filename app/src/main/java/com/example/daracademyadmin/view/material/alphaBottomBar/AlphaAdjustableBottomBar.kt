package com.example.daracademyadmin.view.material.alphaBottomBar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AlphaAdjustableBottomBar(
    modifier: Modifier = Modifier,
    content : @Composable RowScope.()->Unit = {}
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {


        content()


    }

}


@Preview
@Composable
fun AlphaAdjustableBottomBar_preview() {
    AlphaAdjustableBottomBar()
}