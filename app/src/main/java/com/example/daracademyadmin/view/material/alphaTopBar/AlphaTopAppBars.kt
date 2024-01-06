package com.example.daracademyadmin.view.material.alphaTopBar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.daracademyadmin.R
import com.example.daracademyadmin.ui.theme.customBlack7
import com.example.daracademyadmin.ui.theme.customWhite0


@Composable
fun AlphaTopAppBar3(
    onCloseClicked : ()->Unit = {},
    centralTitle : @Composable() (BoxScope.()->Unit) = {},
    trailingActions : @Composable() (BoxScope.()->Unit) = {},
    elevation : Dp = 8.dp,
    modifier : Modifier = Modifier
) {

    Surface(
        shadowElevation = elevation,
        color = customWhite0,
        modifier = modifier
            .height(55.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp , end = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.close_icon),
                contentDescription = null,
                tint = customBlack7 ,
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .clickable {
                        onCloseClicked()
                    }
                    .padding(8.dp)
                    .align(Alignment.CenterStart)
            )


            centralTitle()

            trailingActions()
        }
    }

}


@Preview
@Composable
fun CustomAlphaTopAppBar_preview() {
    AlphaTopAppBar3()
}