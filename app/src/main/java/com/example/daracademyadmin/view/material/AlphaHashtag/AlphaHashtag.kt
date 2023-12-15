package com.example.daracademyadmin.view.material.AlphaHashtag

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademyadmin.ui.theme.customWhite4

@Composable
fun AlphaHashtag(
    modifier : Modifier = Modifier,
    txt      : String,
    style    : TextStyle = NormalTextStyles.TextStyleSZ7,
    color    : Color = customWhite4,
    width    : Dp    = 3.dp,
) {


    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .drawBehind {
                drawRoundRect(
                    color = color,
                    cornerRadius = CornerRadius(size.height/2),
                    style = Stroke(
                        width = width.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(8.dp.toPx() , 5.dp.toPx())))
                )
            }
            .padding(4.dp)
    ) {
        Text(
            text = txt,
            style = style
        )
    }

}

@Preview
@Composable
fun AlphaHashtag_preview() {
    AlphaHashtag(txt = "hashtag")
}