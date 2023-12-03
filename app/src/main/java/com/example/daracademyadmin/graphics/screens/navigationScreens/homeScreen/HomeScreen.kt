package com.example.daracademyadmin.graphics.screens.navigationScreens.homeScreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademyadmin.R
import com.example.daracademyadmin.model.data.dataClasses.Category
import com.example.daracademyadmin.model.data.variables.categories
import com.example.daracademyadmin.model.data.variables.firaSansFamily
import com.example.daracademyadmin.model.viewModel.DaracademyAdminViewModel
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.color2
import com.example.daracademyadmin.ui.theme.color3
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite3


@Composable
fun HomeScreen(
    viewModel : DaracademyAdminViewModel,
    onNavigate : (Screens)->Unit = {},
    modifier: Modifier = Modifier
) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .background(Color(android.graphics.Color.parseColor("#F9F9F9")))
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
//                    onNavigate(Screens.AnneesDesEtudes.annees_de_primaire_Screen())
                        onNavigate(
                            if (category.screen==Screens.HomeScreen().root){
                                Screens.HomeScreen()
                            }
                            else if (category.screen==Screens.AddTeacherScreen().root){
                                Screens.AddTeacherScreen()
                            }
                            else if (category.screen==Screens.AddPostScreen().root){
                                Screens.AddPostScreen()
                            }
                            else if (category.screen==Screens.AddFormationScreen().root){
                                Screens.AddFormationScreen()
                            }
//                            else if (category.screen==Screens.AddSupportCourseScreen().root){
//                                Screens.AddSupportCourseScreen()
//                            }
                            else{
                                Screens.HomeScreen()
                            }
                        )
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


@Composable
private fun HomeScreenGridItem(
    modifier : Modifier = Modifier,
    category: Category
) {
    Surface(
        shadowElevation = 4.dp,
        border = BorderStroke(
            width = 2.dp,
            color = customWhite3,
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 8.dp, end = 8.dp)
//                .background(Color.Red)
        ) {
            Icon(
                painter = painterResource(id = category.img),
                contentDescription = null,
                tint     = color1 ,
                modifier = Modifier
                    .size(40.dp)
//                    .background(Color.Yellow)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(35.dp)
//                    .background(Color.Red)
            ) {
                Text(
                    text = category.name,
                    style = NormalTextStyles.TextStyleSZ8.copy(color = color1),
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Preview
@Composable
fun HomeScreen_preview() {

    val context = LocalContext.current

    HomeScreen(
        viewModel = viewModel(
            factory = object  : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(DaracademyAdminViewModel::class.java))
                        return DaracademyAdminViewModel(context) as T
                    throw IllegalArgumentException("can't create DaracademyAdminViewModel (MainActivity)")
                }
            }
        )
    )
}


@Preview
@Composable
fun HomeScreenGridItem_preview() {
    HomeScreenGridItem(
        category = Category(img =  R.drawable.support , name = "Education" )
    )
}



/*
Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 26.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement   = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(top = 16.dp , bottom = 16.dp , start = 20.dp , end = 20.dp),
            modifier = Modifier
        ){

            items(categories){
                HomeScreenGridItem(category = it)
            }

        }
    }

 */

