package com.example.daracademyadmin.graphics.screens.navigationScreens.addFormation.bottomSheet

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademyadmin.R
import com.example.daracademyadmin.model.data.dataClasses.Course
import com.example.daracademyadmin.model.data.variables.dayImg
import com.example.daracademyadmin.model.data.variables.firaSansFamily
import com.example.daracademyadmin.model.data.variables.josefinSansFamily
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.color3
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite4
import com.example.daracademyadmin.ui.theme.customWhite5

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchedulerBottomSheet(
    show        : Boolean,
    onDismiss   : ()->Unit = {},
    onAddClick  : ()->Unit = {},
    onIdetClick : (Int)->Unit = {},
    coursesList : List<Course>,
    modifier   : Modifier = Modifier
) {

    if (show){


        var bottomSheetState = rememberModalBottomSheetState()
        var coroutineScope   = rememberCoroutineScope()

        ModalBottomSheet(
            onDismissRequest = {
                onDismiss()
            },
            sheetState = bottomSheetState,
            containerColor = customWhite0,
            modifier = Modifier
        ){

            Text(
                text = "Scheduler",
                style = NormalTextStyles.TextStyleSZ4.copy(color = color1 , fontFamily = firaSansFamily),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .height(1.dp)
                    .background(customWhite5)
                    .align(Alignment.CenterHorizontally))


            Spacer(modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(20.dp))

            val scrollState = rememberScrollState()

//            LaunchedEffect(key1 = scrollState ){
//                scrollState.scrollTo(scrollState.maxValue)
//            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {
                coursesList.forEachIndexed { index, course ->

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(80.dp)
                            .align(Alignment.CenterHorizontally)
                            .border(
                                width = 2.dp,
                                color = customWhite4,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clip(RoundedCornerShape(12.dp))
                            .padding(start = 16.dp, end = 16.dp)
                    ) {

                        Image(painter = painterResource(id = dayImg[course.day]), contentDescription = null , contentScale = ContentScale.FillBounds , modifier = Modifier.size(35.dp) )

                        Spacer(modifier = Modifier.width(20.dp))

                        Text(
                            text = "${course.start} - ${course.end}",
                            style = NormalTextStyles.TextStyleSZ6.copy(fontFamily = josefinSansFamily , textAlign = TextAlign.Center),
                            modifier = Modifier
                                .weight(1f)
                        )

                        Spacer(modifier = Modifier.width(20.dp))

                        Image(
                            painter = painterResource(id = R.drawable.edit_icon),
                            contentDescription = null ,
                            contentScale = ContentScale.FillBounds ,
                            modifier = Modifier.size(25.dp)
                                .clickable {
                                    onIdetClick(index)
                                }
                        )


                    }

                    Spacer(modifier = Modifier.height(26.dp))

                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(80.dp)
                        .align(Alignment.CenterHorizontally)
                        .border(
                            width = 2.dp,
                            color = customWhite4,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            onAddClick()
                        }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_icon),
                        contentDescription = null,
                        tint = color1,
                        modifier = Modifier
                            .size(50.dp)
                    )

                }
            }

            Spacer(modifier = Modifier.height(25.dp))


        }

    }

}

@Preview
@Composable
fun SchedulerBottomSheet_preview() {
    SchedulerBottomSheet(
        show = true,
        coursesList = emptyList()
    )
}