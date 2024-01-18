package com.example.daracademyadmin.view.screens.navigationScreens.addTeacher

import android.app.Activity
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
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
import androidx.navigation.compose.rememberNavController
import com.example.alphaspace.screens.common.dropDownMenu.AlphaDropDownMenu
import com.example.bigsam.grafic.material.loadingEffect.loadingLottieAnimation
import com.example.daracademyadmin.model.variables.josefinSansFamily
import com.example.daracademyadmin.ui.theme.backgroundLight
import com.example.daracademyadmin.view.screens.navigationScreens.addTeacher.components.header.Header
import com.example.daracademyadmin.view.screens.navigationScreens.addTeacher.components.informationFields.informationFields

@Composable
fun AddTeacherScreen(
    viewModel: DaracademyAdminViewModel,
    modifier: Modifier = Modifier
) {


    val window = LocalView.current.context as Activity

    LaunchedEffect(key1 = window){
        window.window.apply {
            navigationBarColor = backgroundLight.toArgb()
        }
    }


    var context = LocalContext.current

    var name by rememberSaveable {
        mutableStateOf("")
    }

    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }

    var number by rememberSaveable {
        mutableStateOf("")
    }


    var numberType by remember {
        mutableStateOf("07")
    }

    var photo_uri : Uri? by rememberSaveable {
        mutableStateOf(null)
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

            Header(
                photo_uri = photo_uri,
                onImageChange = {uri ->
                    photo_uri = uri
                }
            )

            informationFields(
                name       = name,
                email      = email,
                numberType = numberType,
                number     = number,
                password   = password,
                onChange   = {type, txt ->
                    when(type){
                        "name"->{
                            name = txt
                        }
                        "email"->{
                            email = txt
                        }
                        "numberType"->{
                            numberType = txt
                        }
                        "number"->{
                            if (txt.length <= 8){
                                number = txt.filter { it.isDigit() }
                            }
                        }
                        "password"->{
                            password = txt
                        }

                    }
                }
            )




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
                            password = password,
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

                        viewModel.screenRepo.navigate_to_screen(Screens.HomeScreen().root)

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
    val navController = rememberNavController()

    AddTeacherScreen(
        viewModel = viewModel(
            factory = object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(DaracademyAdminViewModel::class.java)){
                        return DaracademyAdminViewModel(context , navController) as T
                    }
                    else
                        throw IllegalArgumentException("cant create DaracademyAdminViewModel (addTeacherScreen)")
                }
            }
        )
    )
}