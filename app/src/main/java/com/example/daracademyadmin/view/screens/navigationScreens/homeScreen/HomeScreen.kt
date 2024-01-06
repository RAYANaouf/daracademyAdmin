package com.example.daracademyadmin.view.screens.navigationScreens.homeScreen

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademyadmin.R
import com.example.daracademyadmin.model.dataClasses.Category
import com.example.daracademyadmin.model.variables.categories
import com.example.daracademyadmin.model.variables.firaSansFamily
import com.example.daracademyadmin.ui.theme.backgroundLight
import com.example.daracademyadmin.viewModel.DaracademyAdminViewModel
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.color2
import com.example.daracademyadmin.ui.theme.color3
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite3


@Composable
fun HomeScreen(
    viewModel : DaracademyAdminViewModel,
    modifier: Modifier = Modifier
) {


    val window = LocalView.current.context as Activity

    LaunchedEffect(key1 = window){
        window.window.apply {
            navigationBarColor = backgroundLight.toArgb()
        }
    }



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Spacer(modifier = Modifier.height(36.dp))

        categories.forEachIndexed { index, category ->

            var color = if ((index+1) % 3 == 0) color3 else if ((index+1) % 2 == 0) color2 else color1

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth(0.8f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color)
                    .clickable {

                        if (category.screen == Screens.AddTeacherScreen().root) {
                            viewModel.screenRepo.navigate_to_screen(Screens.AddTeacherScreen().root)
                        }
                        else if (category.screen == Screens.AddPostScreen().root) {
                            viewModel.screenRepo.navigate_to_screen(Screens.AddPostScreen().root)
                        }
                        else if (category.screen == Screens.AddFormationScreen().root) {
                            viewModel.screenRepo.navigate_to_screen(Screens.AddFormationScreen().root)
                        }
                        else if (category.screen == Screens.AddStudentScreen().root) {
                            viewModel.screenRepo.navigate_to_screen(Screens.AddStudentScreen().root)
                        }
                        else if (category.screen == Screens.StageScreen().root) {
                            viewModel.screenRepo.navigate_to_screen(Screens.StageScreen().root , params = arrayOf("edit"))
                        }
                        else {
                            viewModel.screenRepo.navigate_to_screen(Screens.HomeScreen().root)
                        }

                    }
                    .padding(4.dp)

            ) {

                Spacer(modifier = Modifier.width(16.dp))

                Icon(
                    painter = painterResource(id = category.img) ,
                    contentDescription = null,
                    tint = customWhite0,
                    modifier = Modifier
                        .size(45.dp)
                )


                Spacer(modifier = Modifier.width(16.dp))


                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = category.name,
                        style = NormalTextStyles.TextStyleSZ7.copy(color = customWhite0),
                        fontFamily = firaSansFamily,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Icon(
                    painter = painterResource(id = R.drawable.add_file_inline) ,
                    contentDescription = null,
                    tint = customWhite0,
                    modifier = Modifier
                        .size(35.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

            }

            Spacer(modifier = Modifier.height(25.dp))
        }


    }


}




@Preview
@Composable
fun HomeScreen_preview() {

    val context = LocalContext.current
    val navController = rememberNavController()

    HomeScreen(
        viewModel = viewModel(
            factory = object  : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(DaracademyAdminViewModel::class.java))
                        return DaracademyAdminViewModel(context , navController) as T
                    throw IllegalArgumentException("can't create DaracademyAdminViewModel (MainActivity)")
                }
            }
        )
    )
}


