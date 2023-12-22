package com.example.daracademyadmin.view.screens.navigationScreens.uploadScreen

import android.graphics.Color.parseColor
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademyadmin.R
import com.example.daracademyadmin.model.dataClasses.apis.progress.PrograssType
import com.example.daracademyadmin.model.dataClasses.apis.progress.ProgressUpload
import com.example.daracademyadmin.model.variables.firaSansFamily
import com.example.daracademyadmin.model.variables.josefinSansFamily
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.color2
import com.example.daracademyadmin.ui.theme.color3
import com.example.daracademyadmin.ui.theme.customBlack1
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite1
import com.example.daracademyadmin.ui.theme.customWhite2
import com.example.daracademyadmin.ui.theme.customWhite4

@Composable
fun UploadScreen(
    progresses : List<ProgressUpload> = emptyList(),
    modifier : Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        progresses.forEach {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        width = 2.dp,
                        color = customWhite4,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .background(customWhite0)
            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(70.dp)
                ){
                    Image(
                        painter =  painterResource(id = if(it.type is PrograssType.Post) R.drawable.post else if(it.type is PrograssType.Formation) R.drawable.formation  else if(it.type is PrograssType.Teacher) R.drawable.teacher else R.drawable.post )  ,
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .size(50.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {

                    Row(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(color = customWhite1)
                            .height(30.dp)
                            .fillMaxWidth()
                            .border(
                                width = 1.5.dp,
                                color = if (it.failed) Color.Red else Color(parseColor("#0B357D")),
                                shape = CircleShape
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(if (it.failed) 0f else ((somme(it.progress) / it.progress.size) / 100))
                                .clip(CircleShape)
                                .background(color1)
//                                .background(Color(parseColor("#083889")))
                        )
                    }

                    Box(
                        contentAlignment = Alignment.CenterEnd,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = if (it.failed) "Canceled" else "Prog : ${(somme(it.progress))/it.progress.size} %",
                            style = NormalTextStyles.TextStyleSZ9.copy(color = if (it.failed) Color.Red else color1  , fontFamily = josefinSansFamily)
                        )
                    }

                }

                Spacer(modifier = Modifier.width(16.dp))
            }


            Spacer(modifier = Modifier.height(20.dp))

        }


        Spacer(modifier = Modifier.height(30.dp))

    }

}

private fun somme(l : List<Float>):Float{
    var result = 0.0f
    l.forEach {
        result += it
    }

    return result
}


@Preview
@Composable
fun UploadScreen_preview() {
    UploadScreen()
}