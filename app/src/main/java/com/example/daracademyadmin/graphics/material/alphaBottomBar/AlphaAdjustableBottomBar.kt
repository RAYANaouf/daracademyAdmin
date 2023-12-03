package com.example.daracademyadmin.graphics.material.alphaBottomBar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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