package com.example.daracademyadmin.graphics.screens.navigationScreens.addFormation.bottomSheet

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademyadmin.R
import com.example.daracademyadmin.model.data.dataClasses.Course
import com.example.daracademyadmin.model.data.dataClasses.Teacher
import com.example.daracademyadmin.model.data.variables.dayImg
import com.example.daracademyadmin.model.data.variables.firaSansFamily
import com.example.daracademyadmin.model.data.variables.josefinSansFamily
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite4
import com.example.daracademyadmin.ui.theme.customWhite5



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTeacherBottomSheet(
    show       : Boolean,
    onDismiss  : ()->Unit = {},
    teachers    : List<Teacher>,
    modifier   : Modifier = Modifier
) {


    if (show){

        var state            = rememberModalBottomSheetState()
        var coroutineScope   = rememberCoroutineScope()
        var context          = LocalContext.current

        ModalBottomSheet(
            onDismissRequest = {
                onDismiss()
            },
            sheetState = state,
            containerColor = customWhite0,
            modifier = Modifier
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
                    .verticalScroll(rememberScrollState())
            ) {
                teachers.forEachIndexed { index , item ->

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier
                            .height(120.dp)
                            .width(80.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = Uri.parse(item.photo)),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(color1)
                                .clickable {
                                    Toast
                                        .makeText(context, item.photo, Toast.LENGTH_LONG)
                                        .show()
                                }
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            text  = item.name,
                            style = NormalTextStyles.TextStyleSZ8.copy(fontFamily = firaSansFamily)
                        )
                    }

                    if (index == teachers.size-1)
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }



            Spacer(modifier = Modifier.height(25.dp))


        }



    }

}