package com.example.daracademyadmin.view.screens.navigationScreens.addTeacher

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberAsyncImagePainter
import com.example.alphaspace.screens.common.textFields.AlphaTextField
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademyadmin.R
import com.example.daracademyadmin.viewModel.DaracademyAdminViewModel
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.color3
import com.example.daracademyadmin.ui.theme.customWhite0
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alphaspace.screens.common.dropDownMenu.AlphaDropDownMenu
import com.example.bigsam.grafic.material.loadingEffect.loadingLottieAnimation
import com.example.daracademyadmin.model.variables.josefinSansFamily

@Composable
fun AddTeacherScreen(
    onNavigate : (Screens)->Unit = {},
    viewModel: DaracademyAdminViewModel,
    modifier: Modifier = Modifier
) {

    var context = LocalContext.current

    var loading by rememberSaveable {
        mutableStateOf(false)
    }

    var name by rememberSaveable {
        mutableStateOf("")
    }

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var number by rememberSaveable {
        mutableStateOf("")
    }

    var expanded by remember{
        mutableStateOf(false)
    }

    var numberType by remember {
        mutableStateOf("07")
    }

    var photo_uri : Uri? by rememberSaveable {
        mutableStateOf(null)
    }


    var launcher_img = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()){uri->
        if (uri != null){
            photo_uri = uri
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(android.graphics.Color.parseColor("#F9F9F9")))
    ) {


        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
            ){
                Image(
                    painter = rememberAsyncImagePainter(model = if (photo_uri == null) R.drawable.teacher else photo_uri),
                    contentDescription = null,
                    contentScale = if (photo_uri==null) ContentScale.Inside else ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            launcher_img.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }
                        .clip(if (photo_uri == null) RoundedCornerShape(0.dp) else CircleShape)
                        .border(
                            width = 1.5.dp,
                            color = if (photo_uri == null) Color.Transparent else color1,
                            shape = CircleShape
                        )
                )
                Box(
                    modifier = Modifier
                        .size(90.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.camera_icon ),
                        contentDescription = null,
                        tint = color1,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(20.dp)
                    )
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                AlphaTextField(
                    text = name,
                    textFieldStyle = NormalTextStyles.TextStyleSZ7,
                    onValueChange = {
                        name = it
                    },
                    hint = "Teacher name",
                    hintStyle = NormalTextStyles.TextStyleSZ7,
                    cursorColor = color1,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                )
            }

            Spacer(
                modifier = Modifier
                    .height(24.dp)
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                AlphaTextField(
                    text = email,
                    textFieldStyle = NormalTextStyles.TextStyleSZ7,
                    onValueChange = {
                        email = it
                    },
                    hint = "Teacher email",
                    hintStyle = NormalTextStyles.TextStyleSZ7,
                    cursorColor = color1,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                )
            }

            Spacer(
                modifier = Modifier
                    .height(24.dp)
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 16.dp, end = 16.dp)
            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            width = 1.dp,
                            color = color1,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            expanded = true
                        }
                ) {

                    AlphaDropDownMenu(
                        expanded = expanded,
                        items = listOf("05","06","07"),
                        onClick = {type-> numberType = type},
                        onDismissRequest = { expanded = false })

                    Text(
                        text = numberType,
                        style = NormalTextStyles.TextStyleSZ6.copy(color = color1 , fontFamily = josefinSansFamily),
                        modifier = Modifier
                            .offset(x = 0.dp , y = (-3).dp)
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))

                AlphaTextField(
                    text = number,
                    textFieldStyle = NormalTextStyles.TextStyleSZ7,
                    onValueChange = {txt->

                        if (txt.length <= 8){
                            number = txt.filter { it.isDigit() }
                        }

//                        var format = "**_**_**_**"
//
//                        if (txt.length <= format.length){
//
//                            val newNumber = buildString {
//
//                                var inputIndex  = 0
//
//
//                                while (inputIndex < txt.length){
//
//                                    if(format[inputIndex] == '_' && txt[inputIndex] != '_'){
//                                        append("_${txt[inputIndex]}")
//
//                                    }
//                                    else{
//                                        append(txt[inputIndex])
//                                    }
//
//
//
//                                    inputIndex++
//                                }
//
//                            }
//
//                            number = newNumber
//                        }




                    },
                    hint = "Phone number",
                    hintStyle = NormalTextStyles.TextStyleSZ7,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Phone
                    ),
                    cursorColor = color1,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(color3)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.phone_fulfilled_icon),
                        contentDescription = null,
                        tint = color1,
                        modifier = Modifier
                            .fillMaxSize(0.55f)
                    )
                }

            }


        }



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .align(Alignment.BottomEnd)
        ) {

            Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        viewModel.addTeacher(
                            name = name,
                            email = email,
                            number     = number,
                            type       = numberType,
                            photo      = photo_uri ,
                            formation = listOf(),
                            support = listOf(),
                            onFailureCallBack = {
                                Toast.makeText(context , "Failed : ${it.message}" , Toast.LENGTH_LONG).show()
                            },
                            onSuccessCallBack = {
                                Toast.makeText(context , "the teacher has been added" , Toast.LENGTH_LONG).show()
                            }
                        )

                        onNavigate(Screens.HomeScreen())

                    },
                    colors = ButtonDefaults.buttonColors(containerColor = color1),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                ) {
                    Text(
                        text = "Done",
                        style = NormalTextStyles.TextStyleSZ7.copy(color = customWhite0)
                    )
                }
            


            Spacer(modifier = Modifier.width(16.dp))


        }

    }

}


@Preview
@Composable
fun AddTeacherScreen_preview() {

    val context = LocalContext.current

    AddTeacherScreen(
        viewModel = viewModel(
            factory = object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(DaracademyAdminViewModel::class.java)){
                        return DaracademyAdminViewModel(context) as T
                    }
                    else
                        throw IllegalArgumentException("cant create DaracademyAdminViewModel (addTeacherScreen)")
                }
            }
        )
    )
}