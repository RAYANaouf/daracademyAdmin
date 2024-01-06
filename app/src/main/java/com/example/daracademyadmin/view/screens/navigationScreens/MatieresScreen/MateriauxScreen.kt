package com.example.daracademyadmin.view.screens.navigationScreens.MatieresScreen

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademyadmin.R
import com.example.daracademyadmin.model.dataClasses.Matiere
import com.example.daracademyadmin.model.variables.les_annees_d_etude.matieres_primaire_premiere_annee
import com.example.daracademyadmin.ui.theme.backgroundLight
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite4
import com.example.daracademyadmin.view.screens.navigationScreens.MatieresScreen.AddMatieresDialog.AddMatieresDialog
import com.example.daracademyadmin.viewModel.DaracademyAdminViewModel
import com.vanpra.composematerialdialogs.rememberMaterialDialogState


@Composable
fun MatieresScreen(
    viewModel    : DaracademyAdminViewModel = viewModel(),
    phase        : String,
    annee        : String,
    kind         : String = "",
    modifier     : Modifier = Modifier
) {

//    val context = LocalContext.current

    val window = LocalView.current.context as Activity

    LaunchedEffect(key1 = window){
        window.window.apply {
            navigationBarColor = backgroundLight.toArgb()
        }
    }



    var state_addMatiereDialog = rememberMaterialDialogState()
    viewModel.getAllMatieres(phase = phase , annee = annee , onSuccessCallBack = {} , onFailureCallBack = {})

    //dialogs
    AddMatieresDialog(
        phase = phase,
        annee = annee,
        viewModel = viewModel,
        state = state_addMatiereDialog,
        onDismiss = {
            state_addMatiereDialog.hide()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(customWhite0)
            .clip(RoundedCornerShape(16.dp))
    )

    
    Column {

        LazyVerticalGrid(
            horizontalArrangement = Arrangement.spacedBy(26.dp),
            verticalArrangement = Arrangement.spacedBy(26.dp),
            contentPadding = PaddingValues(top = 26.dp , bottom = 26.dp),
            columns = GridCells.Fixed(2),
            modifier = modifier
                .padding( start = 16.dp , end = 16.dp)
        ){
            items(viewModel.matieres){
                MatieresScreenItem(
                    matiere = it,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .clickable {
                            if (kind == "view"){

                                viewModel.screenRepo.navigate_to_screen(Screens.CoursesScreen().root )


                            }
                            else if(kind == "edit"){
                                viewModel.screenRepo.navigate_to_screen(Screens.AddCoursScreen().root , phase , annee , it.name )

                            }

                        }
                )
            }

            item(){
                val context = LocalContext.current
                AddMatieresItem(
                    onClick = {
                        state_addMatiereDialog.show()
                    },
                    background = customWhite0
                )
            }
        }
    }
    
}


@Composable
fun MatieresScreenItem(
    matiere  : Matiere,
    selected : Boolean = false,
    modifier : Modifier = Modifier
) {

    Surface(
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 2.dp,
            color = if(!selected) customWhite4 else color1,
        ),
        color = customWhite0,
        modifier = modifier
            .aspectRatio(1f)
            .heightIn(min = 100.dp)
            .clip(RoundedCornerShape(16.dp))

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
        ) {
            Image(
                painter = if (matiere.img>=0) painterResource(id = matieres_primaire_premiere_annee[matiere.img].img) else rememberAsyncImagePainter(model = matiere.imgUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = matiere.name,
                style = NormalTextStyles.TextStyleSZ9,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}


@Composable
fun AddMatieresItem(
    elevation  : Dp       = 4.dp,
    shape      : Shape = RoundedCornerShape(16.dp),
    background : Color = Color.White,
    onClick    : ()        -> Unit = {},
    modifier   : Modifier = Modifier
) {

    Surface(
        shadowElevation = elevation,
        shape           = shape    ,
        modifier        = modifier
            .aspectRatio(1f)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape)
                .clickable {
                    onClick()
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add_icon),
                contentDescription = null ,
                modifier = Modifier
                    .size(36.dp)
            )
        }
    }

}


@Preview
@Composable
fun MatieresScreen_preview() {
    MatieresScreen(
        phase = "",
        annee = ""
    )
}


@Preview
@Composable
fun MatieresScreenItem_preview() {
    MatieresScreenItem(
        Matiere(img = R.drawable.student_icon , name = "math")
    )
}
