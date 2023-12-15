package com.example.daracademyadmin.view.material.alphaBottomBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bigsam.grafic.material.loadingEffect.loadingLottieAnimation
import com.example.daracademyadmin.R
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.customWhite0

@Composable
fun AlphaBottomBar(
    modifier         : Modifier = Modifier,
    onLoad           : Boolean,
    onAddImageClick  : ()->Unit  = {},
    onPostClick      : ()-> Unit = {}
) {


    Row(
        modifier = modifier
            .height(40.dp)
            .background(customWhite0)
            .fillMaxWidth()

    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier  = Modifier
                .fillMaxHeight()
                .width(55.dp)
                .clickable {
                    onAddImageClick()
                }
        ) {
           Icon(
               painter = painterResource(id = R.drawable.photo_icon_inline) ,
               contentDescription = null,
               tint = color1,
               modifier = Modifier
                   .size(20.dp)
           )
        }


        Spacer(modifier = Modifier.weight(1f))

        Box(
            contentAlignment = Alignment.Center,
            modifier  = Modifier
                .fillMaxHeight()
                .width(55.dp)
                .clickable {
                    onPostClick()
                }

        ) {
            if (onLoad){
                loadingLottieAnimation(
                    modifier = Modifier.fillMaxSize()
                        .scale(1.5f)
                )
            }
            else{
                Image(
                    painter = painterResource(id = R.drawable.send_icon) ,
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(20.dp)

                )
            }



        }

    }
}

@Preview
@Composable
fun AlphaBottomBar_preview() {
    AlphaBottomBar(
        onLoad = false
    )
}