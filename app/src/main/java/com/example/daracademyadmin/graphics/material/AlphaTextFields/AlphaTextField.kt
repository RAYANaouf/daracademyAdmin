package com.example.alphaspace.screens.common.textFields

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.customWhite0

@Composable
fun AlphaTextField(
    modifier : Modifier = Modifier,
    shape : Shape = RoundedCornerShape(12.dp),
    borderStroke: BorderStroke = BorderStroke(1.dp , color1),
    background : Color = customWhite0,
    text : String,
    onValueChange : (String)->Unit,
    textFieldStyle: TextStyle,
    hint : String ,
    hintStyle : TextStyle,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation : VisualTransformation = VisualTransformation.None,
    cursorColor : Color = Color.Black
) {



    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .clip(shape)
            .border(
                border = borderStroke,
                shape = shape
            )
            .background(background)
            .padding(start = 10.dp, end = 10.dp, top = 6.dp, bottom = 6.dp)
    ) {

        BasicTextField(
            value = text,
            onValueChange = {
                onValueChange(it)
            },
            textStyle = textFieldStyle,
            maxLines = 1,
            cursorBrush = SolidColor(cursorColor),
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            modifier = Modifier.fillMaxWidth()
        )

        if (text == ""){
            Text(
                text = hint ,
                style = hintStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}


@Preview
@Composable
fun AlphaTextField_preview() {
    AlphaTextField(
        text = "",
        onValueChange = {

        },
        hint = "hint",
        hintStyle = TextStyle(),
        textFieldStyle = TextStyle(),
    )
}