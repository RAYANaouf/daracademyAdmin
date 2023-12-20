package com.example.daracademyadmin.view.common

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademyadmin.R
import com.example.daracademyadmin.model.dataClasses.Teacher
import com.example.daracademyadmin.model.variables.firaSansFamily
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite5



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTeacherBottomSheet(
    show       : Boolean,
    onDismiss  : ()->Unit = {},
    teachers    : List<Teacher>,
    selectedTeachers    : List<Teacher> = emptyList(),
    onTeacherSelected   : (Teacher)->Unit = {},
    shape: Shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp ),
    modifier   : Modifier = Modifier
) {


    if (show){

        var state            = rememberModalBottomSheetState()

        ModalBottomSheet(
            onDismissRequest = {
                onDismiss()
            },
            sheetState = state,
            containerColor = customWhite0,
            shape = shape,
            modifier = modifier
        ){

            Text(
                text = "Teachers",
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
            
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(150.dp)
                    .horizontalScroll(rememberScrollState())
            ) {
                teachers.forEachIndexed { index , item ->

                    Spacer(modifier = Modifier.width(16.dp))

                    Box(

                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .width(80.dp)
                                .clickable {
                                    onTeacherSelected(item)
                                }
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(model = Uri.parse(item.photo)),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(color1)

                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text  = item.name,
                                style = NormalTextStyles.TextStyleSZ9.copy(fontFamily = firaSansFamily)
                            )
                        }

                        if ( selectedTeachers.contains(item) ){
                            Image(
                                painter = painterResource(id = R.drawable.done_icon),
                                contentDescription = null ,
                                modifier = Modifier
                                    .size(16.dp)
                                    .align(Alignment.TopEnd)
                            )
                        }
                    }

                    if (index == teachers.size-1){
                        Spacer(modifier = Modifier.width(16.dp))
                    }

                }
            }



            Spacer(modifier = Modifier.height(25.dp))


        }



    }

}