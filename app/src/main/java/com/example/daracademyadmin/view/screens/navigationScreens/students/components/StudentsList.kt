package com.example.daracademyadmin.view.screens.navigationScreens.students.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.daracademyadmin.model.dataClasses.Student
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite4

@Composable
fun StudentList(
    studentList: List<Student> = emptyList() ,
    modifier : Modifier = Modifier
) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 16.dp , end = 16.dp , top = 20.dp , bottom = 20.dp),
        modifier = modifier
    ){

        items(studentList){
            Item(
                studentName = it.name,
                paid = it.paid
            )
        }

    }

}


@Composable
fun Item(
    studentName  : String   = "",
    paid         : Boolean  = true,
    modifier     : Modifier = Modifier
) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.5.dp,
                color = customWhite4,
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = customWhite0
            )
            .clickable {

            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(50.dp)
        ){

            Spacer(modifier = Modifier.width(10.dp))

            Image(
                painter            = painterResource(id = R.drawable.student_icon) ,
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .size(30.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = studentName,
                style = NormalTextStyles.TextStyleSZ8
            )

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .border(
                        width = 1.5.dp,
                        color = if (paid) Color.Green else Color.Red,
                        shape = CircleShape
                    )
                    .size(20.dp)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(if (paid) Color.Green else Color.Red)
                
            )

            Spacer(modifier = Modifier.width(10.dp))
        }
    }

}


@Preview
@Composable
fun Item_preview() {
    Item()
}

@Preview
@Composable
fun StudentList_preview() {
    
}


