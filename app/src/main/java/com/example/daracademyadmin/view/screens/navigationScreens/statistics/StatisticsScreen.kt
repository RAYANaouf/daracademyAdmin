package com.example.daracademyadmin.view.screens.navigationScreens.statistics

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.daracademyadmin.R
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite4

@Composable
fun StatisticsScreen(
    modifier : Modifier = Modifier
) {

    val context = LocalContext.current


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {

        Spacer(modifier = Modifier.height(36.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(100.dp)
        ) {



            Box(
                modifier = Modifier
                    .weight(4f)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 1.5.dp,
                        color = customWhite4,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(customWhite0)
                    .clickable { }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3f)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.teacher),
                            contentDescription = null ,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .fillMaxSize(0.7f)
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f)
                    ) {
                        Text(text = "15")
                    }

                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .weight(4f)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 1.5.dp,
                        color = customWhite4,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(customWhite0)
                    .clickable { }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3f)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.student_icon),
                            contentDescription = null ,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .fillMaxSize(0.7f)
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f)
                    ) {
                        Text(text = "15")
                    }

                }
            }




        }

        Spacer(modifier = Modifier.height(36.dp))

        Box {

        }

    }

}

@Preview
@Composable
fun StatisticsScreen_preview() {
    StatisticsScreen()
}