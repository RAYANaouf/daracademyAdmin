package com.example.bigsam.grafic.material.indicator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AlphaIndicator(
    count : Int,
    position : Int,
    selectedItemColor   : Color = Color.Blue,
    unselectedItemColor : Color = Color.LightGray,
    selectedItemSize    : Dp    = 30.dp,
    unselectedItemSize  : Dp    = 20.dp,
    modifier : Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .height(40.dp)
    ) {

        for ( i in 1..count ){
            Box(
                modifier = Modifier
                    .size(if (i == position) selectedItemSize else unselectedItemSize)
                    .clip(CircleShape)
                    .background(if (i == position) selectedItemColor else unselectedItemColor)
                    .border(
                        BorderStroke(
                            width = if (i == position) 0.dp else 2.dp,
                            color = selectedItemColor
                        ),
                        shape = CircleShape
                    )
            )
            if (i != count ){
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Preview
@Composable
fun Indicator_preview() {
    AlphaIndicator(count = 3 , position = 1)
}


