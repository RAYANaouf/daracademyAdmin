package com.example.daracademyadmin.view.screens.navigationScreens.students.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademyadmin.model.variables.josefinSansFamily
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.customWhite0

@Composable
fun Tabs(
    onTabClick : (Int)->Unit = {},
    page       : Int         = 0,
    modifier   : Modifier = Modifier
) {

    TabRow(
        selectedTabIndex = page,
        containerColor   = customWhite0 ,
        indicator        = {
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(it[page]),
                color = color1 ,
                height = 3.dp
            )
        },
        modifier         = modifier
            .height(55.dp)
    ) {

        Tab(selected = page == 0, onClick = { onTabClick(0) } , modifier = Modifier.height(55.dp) ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "All",
                    style = if( page == 0 )  NormalTextStyles.TextStyleSZ5.copy(fontFamily = josefinSansFamily , color = color1 )  else NormalTextStyles.TextStyleSZ8.copy(fontFamily = josefinSansFamily )
                )
            }
        }

        Tab(selected = page == 1, onClick = { onTabClick(1) }  , modifier = Modifier.height(55.dp)) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Paid",
                    style = if( page == 1 )  NormalTextStyles.TextStyleSZ5.copy(fontFamily = josefinSansFamily , color = color1 )  else NormalTextStyles.TextStyleSZ8.copy(fontFamily = josefinSansFamily )
                )
            }
        }

        Tab(selected = page == 2, onClick = { onTabClick(2) }  , modifier = Modifier.height(55.dp)) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "No paid",
                    style = if( page == 2 )  NormalTextStyles.TextStyleSZ5.copy(fontFamily = josefinSansFamily , color = color1 )  else NormalTextStyles.TextStyleSZ8.copy(fontFamily = josefinSansFamily )
                )
            }
        }

    }

}
