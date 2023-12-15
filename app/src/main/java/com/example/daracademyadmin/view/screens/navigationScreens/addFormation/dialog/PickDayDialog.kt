package com.example.daracademyadmin.view.screens.navigationScreens.addFormation.dialog

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bigsam.grafic.material.indicator.AlphaIndicator
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademyadmin.R
import com.example.daracademyadmin.model.dataClasses.Course
import com.example.daracademyadmin.model.variables.days
import com.example.daracademyadmin.model.variables.firaSansFamily
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.color2_a44
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite1
import com.example.daracademyadmin.ui.theme.customWhite3
import com.example.daracademyadmin.ui.theme.customWhite4
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PickDayDialog(
    show : Boolean,
    onDoneClick : (Course)->Unit = {},
    onDismiss : ()->Unit = {}
) {




    if (show){

        var pagerState = rememberPagerState(
            initialPage = 0
        )
        var coroutineScope = rememberCoroutineScope()
        
        var selectedDay by rememberSaveable {
            mutableStateOf(0)
        }

        var startTime by rememberSaveable {
            mutableStateOf(LocalTime.of(19,0))
        }

        val formattedStartTime by remember{
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("hh:mm")
                    .format(startTime)
            }
        }

        var endTime by rememberSaveable {
            mutableStateOf(LocalTime.of(19,0))
        }

        val formattedEndTime by remember{
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("hh:mm")
                    .format(endTime)
            }
        }


        var startTimePickerstate = rememberMaterialDialogState()

        var endTimePickerState = rememberMaterialDialogState()

        TimePicker(state = startTimePickerstate , onTimeChange = {time-> startTime = time } )
        TimePicker(state = endTimePickerState   , onTimeChange = {time-> endTime   = time })


        AlertDialog(
            onDismissRequest = { onDismiss() },
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(customWhite0)
        ) {

            Column(
                modifier = Modifier
                    .height(350.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(50.dp)
                        .align(Alignment.CenterHorizontally)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.week_icon),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(30.dp)
                    )
                    
                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "Pick a day",
                        style = NormalTextStyles.TextStyleSZ4.copy(fontFamily = firaSansFamily , color = color1),
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))


                HorizontalPager(
                    pageCount = 2,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {i->
                    if (i==0){
                        DaysPage(
                            onPickDay = {
                                selectedDay = it
                            },
                            selectedItem =  selectedDay,
                        )
                    }
                    else if(i == 1){
                        TimeSet(
                            startTime = formattedStartTime,
                            endTime   = formattedEndTime,
                            onStartTimeSet = {
                                startTimePickerstate.show()
                            },
                            onEndTimeSet = {
                                endTimePickerState.show()
                            }
                        )
                    }
                }


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                ) {

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(40.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(customWhite4)
                            .clickable {
                                if (pagerState.currentPage == 0) {
                                    onDismiss()
                                }
                                coroutineScope.launch {
                                    pagerState.scrollToPage(pagerState.currentPage - 1)
                                }
                            }
                            .width(80.dp)
                            .padding(start = 4.dp, end = 4.dp)
                    ){
                        Text(text = if (pagerState.currentPage ==0) "Cancel" else "Back" , style = NormalTextStyles.TextStyleSZ8.copy(color = customWhite0 , fontFamily = firaSansFamily))
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    AlphaIndicator(
                        count = 2,
                        position = pagerState.currentPage+1,
                        selectedItemColor = color1,
                        unselectedItemColor = customWhite3,
                        selectedItemSize = 14.dp,
                        unselectedItemSize = 10.dp
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(40.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(color1)
                            .clickable {
                                if (pagerState.currentPage == 1) {
                                    onDoneClick(Course(start = formattedStartTime , end = formattedEndTime , day = selectedDay ))
                                    onDismiss()
                                }
                                coroutineScope.launch {
                                    pagerState.scrollToPage(pagerState.currentPage + 1)
                                }
                            }
                            .width(80.dp)
                            .padding(start = 4.dp, end = 4.dp)
                    ){
                        Text(text = if (pagerState.currentPage == (2-1)) "Done" else "Next" , style = NormalTextStyles.TextStyleSZ8.copy(color = customWhite0 , fontFamily = firaSansFamily))
                    }

                }

            }
            


        }

    }

}

@Composable
private fun DaysPage(
    onPickDay: (Int)->Unit = {},
    selectedItem : Int,
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        days.forEachIndexed{ i, d ->

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clickable {
                        onPickDay(i)
                    }
                    .background(if (selectedItem == i) customWhite1 else Color.Transparent)
            ){
                
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(70.dp)
                ) {
                    if (i == selectedItem){
                        Image(
                            painter = painterResource(id = R.drawable.done_icon),
                            contentDescription = null ,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.size(45.dp)
                        )
                    }
                }
                
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()

                ) {
                    
                    Text(
                        text = d,
                        style = NormalTextStyles.TextStyleSZ7.copy(fontFamily = firaSansFamily , textAlign = TextAlign.Left),
                        modifier = Modifier
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(70.dp)
                ) {

                }

            }
        }


    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun TimeSet(
    modifier: Modifier = Modifier,
    startTime : String,
    endTime   : String,
    onStartTimeSet : ()->Unit = {},
    onEndTimeSet   : ()->Unit = {}
) {



    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "Course time",
            style = NormalTextStyles.TextStyleSZ6.copy(fontFamily = firaSansFamily),
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(45.dp)
                    .width(80.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 1.5.dp,
                        color = customWhite4,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable {
                        onStartTimeSet()
                    }
            ) {
                Text(text = startTime)
            }


            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(45.dp)
                    .width(80.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 1.5.dp,
                        color = customWhite4,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable {
                        onEndTimeSet()
                    }
            ) {
                Text(text = endTime)
            }
        }


    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimePicker(
    modifier: Modifier = Modifier,
    onDismiss : ()->Unit = {},
    initialTime : LocalTime = LocalTime.of(1 , 15),
    state     : MaterialDialogState,
    onTimeChange : (LocalTime)->Unit = {}
) {


    MaterialDialog(
        dialogState = state,
        backgroundColor = customWhite0,
        shape = RoundedCornerShape(16.dp),
        buttons = {
            this.positiveButton(
                text = "Done",
                textStyle = NormalTextStyles.TextStyleSZ9.copy(color = color1 , fontFamily = firaSansFamily)
            )
            this.negativeButton(
                text = "Cancel",
                textStyle = NormalTextStyles.TextStyleSZ9.copy(color = customWhite4 , fontFamily = firaSansFamily)
            )
        }
    ) {
        timepicker(
            initialTime =  initialTime,
            onTimeChange = {
                onTimeChange(it)
            },
            colors = TimePickerDefaults.colors(activeBackgroundColor = color1 , inactiveBackgroundColor = color2_a44  , selectorColor = color1)
        )
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PickDayDialog_preview() {
    PickDayDialog(true)
}