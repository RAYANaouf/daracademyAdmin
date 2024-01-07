package com.example.daracademyadmin.view.screens.navigationScreens.students

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademyadmin.model.dataClasses.Student
import com.example.daracademyadmin.model.variables.firaSansFamily
import com.example.daracademyadmin.model.variables.josefinSansFamily
import com.example.daracademyadmin.ui.theme.backgroundLight
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.color3
import com.example.daracademyadmin.ui.theme.customBlack7
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite4
import com.example.daracademyadmin.view.screens.navigationScreens.students.components.StudentList
import com.example.daracademyadmin.view.screens.navigationScreens.students.components.Tabs
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StudentsScreen(
    modifier: Modifier = Modifier
) {


    val window = LocalView.current.context as Activity

    LaunchedEffect(key1 = window){
        window.window.apply {
            navigationBarColor = backgroundLight.toArgb()
        }
    }

    val pagerState = rememberPagerState(
        initialPage = 0
    )
    val coroutineScope = rememberCoroutineScope()

    var page by rememberSaveable {
        mutableStateOf(0)
    }

    Column(
        modifier = modifier
    ) {

        Tabs(
            page       = pagerState.currentPage,
            onTabClick = {_page ->
                coroutineScope.launch {
                    pagerState
                        .animateScrollToPage(_page)
                }
            },
            modifier = Modifier
                .height(55.dp)
        )



        HorizontalPager(
            pageCount =  3,
            state     = pagerState,
            modifier = Modifier
                .weight(1f)
        ) {page->

            val students = when(page){
                0-> listOf<Student>(Student(name = "rayan aouf" , paid = true),Student(name = "faress aouf" , paid = true),Student(name = "sammi flutter" , paid = false),Student(name = "abdullah chahri" , paid = true),Student(name = "achraf" , paid = false),Student(name = "rayan aouf" , paid = true),Student(name = "faress aouf" , paid = true),Student(name = "sammi flutter" , paid = false),Student(name = "abdullah chahri" , paid = true),Student(name = "achraf" , paid = false),Student(name = "rayan aouf" , paid = true),Student(name = "faress aouf" , paid = true),Student(name = "sammi flutter" , paid = false),Student(name = "abdullah chahri" , paid = true),Student(name = "achraf" , paid = false))
                1->listOf<Student>(Student(name = "sammi flutter" , paid = false),Student(name = "abdullah chahri" , paid = false),Student(name = "achraf" , paid = false),Student(name = "rayan aouf" , paid = false),Student(name = "sammi flutter" , paid = false))
                2->listOf<Student>(Student(name = "sammi flutter" , paid = true),Student(name = "abdullah chahri" , paid = true),Student(name = "achraf" , paid = true),Student(name = "rayan aouf" , paid = true),Student(name = "sammi flutter" , paid = true))
                else -> emptyList()
            }

            StudentList(
                studentList = students,
                modifier = Modifier
                    .fillMaxSize()
            )

        }

    }

}



@Preview
@Composable
fun Students_preview() {
    StudentsScreen()
}