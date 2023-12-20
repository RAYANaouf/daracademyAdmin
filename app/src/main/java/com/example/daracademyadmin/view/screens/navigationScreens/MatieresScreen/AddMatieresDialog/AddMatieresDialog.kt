package com.example.daracademyadmin.view.screens.navigationScreens.MatieresScreen.AddMatieresDialog

import android.graphics.Color.parseColor
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.alphaspace.screens.common.textFields.AlphaTextField
import com.example.bigsam.grafic.material.loadingEffect.loadingLottieAnimation
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademyadmin.R
import com.example.daracademyadmin.model.dataClasses.Matiere
import com.example.daracademyadmin.model.variables.firaSansFamily
import com.example.daracademyadmin.model.variables.les_annees_d_etude.matieres_primaire_premiere_annee
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.color2
import com.example.daracademyadmin.ui.theme.color3
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite4
import com.example.daracademyadmin.view.screens.navigationScreens.MatieresScreen.AddMatieresItem
import com.example.daracademyadmin.view.screens.navigationScreens.MatieresScreen.MatieresScreenItem
import com.example.daracademyadmin.viewModel.DaracademyAdminViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMatieresDialog(
    viewModel  : DaracademyAdminViewModel,
    phase      : String,
    annee      : String,
    state      : MaterialDialogState,
    onDismiss  : ()->Unit = {},
    background : Color    = Color.White,
    shape      : Shape    = RoundedCornerShape(16.dp),
    modifier   : Modifier = Modifier
) {


    if (!state.showing){
        return@AddMatieresDialog
    }

    var name by remember{
        mutableStateOf("")
    }

    var scene by remember{
        mutableStateOf(1)
    }

    var selected : Matiere? by remember {
        mutableStateOf(null)
    }

    var img : Uri? by remember {
        mutableStateOf(null)
    }

    val laucher_getImg = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent() ){uri->
        if(uri != null){
            img = uri
        }
    }

    var loading by remember {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()


    MaterialDialog(
        autoDismiss     = true,
        dialogState     = state,
        backgroundColor = background,
        shape           = shape,
        onCloseRequest = {
            if (!loading){
                onDismiss()
            }
        },
    ) {


         Column(
             horizontalAlignment = Alignment.CenterHorizontally,
             modifier = modifier
         ) {

             Row(
                 verticalAlignment = Alignment.CenterVertically,
                 modifier = Modifier
                     .fillMaxWidth()
                     .height(45.dp)
                     .drawBehind {
                         drawLine(
                             color = color1,
                             strokeWidth = 1.5f,
                             start = Offset(x = 0f, y = size.height),
                             end = Offset(x = size.width, y = size.height)
                         )
                     }
             ) {
                 Box(
                     contentAlignment = Alignment.Center,
                     modifier = Modifier
                         .weight(1f)
                         .fillMaxHeight()
                         .clickable {
                             scene = 1
                         }
                 ) {
                     Text(
                         text = "new",
                         style = NormalTextStyles.TextStyleSZ8.copy(fontFamily = firaSansFamily , fontWeight = if (scene == 1) FontWeight.SemiBold else FontWeight.Light)
                     )

                 }

                 Spacer(modifier = Modifier
                     .width(1.dp)
                     .fillMaxHeight(0.3f)
                     .background(color1))

                 Box(
                     contentAlignment = Alignment.Center,
                     modifier = Modifier
                         .weight(1f)
                         .fillMaxHeight()
                         .clickable {
                             scene = 2
                         }
                 ) {
                     Text(
                         text = "predefined",
                         style = NormalTextStyles.TextStyleSZ8.copy(fontFamily = firaSansFamily , fontWeight = if (scene == 2) FontWeight.SemiBold else FontWeight.Light)
                     )
                 }
             }


             if(loading == true){
                 loadingLottieAnimation(
                     modifier = Modifier
                         .fillMaxWidth(0.7f)
                         .weight(1f)
                 )
             }
             else if (scene == 1 ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Spacer(modifier = Modifier.height(36.dp))

                        Image(
                            painter = if (img == null) painterResource(id = R.drawable.add_model) else rememberAsyncImagePainter(model = img),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color(parseColor("#f0f0f0")))
                                .clickable {
                                    if (loading)
                                        return@clickable
                                    laucher_getImg.launch("image/*")
                                }
                                .padding(12.dp)
                                .size(100.dp)

                        )

                        Spacer(modifier = Modifier.height(26.dp))

                        AlphaTextField(
                            text = name,
                            onValueChange = {it-> name = it} ,
                            textFieldStyle = NormalTextStyles.TextStyleSZ8,
                            hint = "Name",
                            hintStyle = NormalTextStyles.TextStyleSZ8,
                            cursorColor = color1,
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(50.dp)
                        )


                        Spacer(modifier = Modifier.height(36.dp))
                    }
                }
             else{
                    LazyVerticalGrid(
                        horizontalArrangement = Arrangement.spacedBy(26.dp),
                        verticalArrangement = Arrangement.spacedBy(26.dp),
                        contentPadding = PaddingValues(top = 26.dp , bottom = 26.dp),
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp, end = 16.dp)
                    ){
                        items(matieres_primaire_premiere_annee){
                            MatieresScreenItem(
                                matiere = it.copy(img = matieres_primaire_premiere_annee.indexOf(it)),
                                selected = it.name == selected?.name,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .clickable {
                                        selected = it.copy(img = matieres_primaire_premiere_annee.indexOf(it))
                                    }
                            )
                        }

                    }
                }


             Row(
                 modifier = Modifier
                     .height(55.dp)
             ) {
                 Box(
                     contentAlignment = Alignment.Center,
                     modifier = Modifier
                         .fillMaxHeight()
                         .weight(1f)
                         .clickable {
                             if (loading)
                                 return@clickable

                             onDismiss()
                         }
                 ){

                     Text(
                         text = "Cancel",
                         style = NormalTextStyles.TextStyleSZ7.copy(color = customWhite4 , fontFamily = firaSansFamily , fontWeight = FontWeight.SemiBold)
                     )

                 }

                 val context = LocalContext.current

                 Box(
                     contentAlignment = Alignment.Center,
                     modifier = Modifier
                         .fillMaxHeight()
                         .weight(1f)
                         .clickable {
                             if (loading)
                                 return@clickable

                             loading = true
                             coroutineScope.launch {

                                 if (scene == 1) {
                                     viewModel.addMatieres(
                                         phase = phase ,
                                         annee = annee ,
                                         Matiere(name = name , img = -1, imgUrl = img.toString() ) ,
                                         onSuccessCallBack = {
                                             loading = false
                                             onDismiss()
                                         } ,
                                         onFailureCallBack = {
                                             loading = false
                                             onDismiss()
                                             Toast.makeText(context , "${it.message}" , Toast.LENGTH_LONG).show()
                                         }
                                     )
                                 }
                                 else {
                                     if (selected == null) {
                                         Toast
                                             .makeText(context, "select one ", Toast.LENGTH_LONG)
                                             .show()
                                         loading = false
                                         onDismiss()
                                     } else {
                                         viewModel.addMatieres(
                                             phase = phase ,
                                             annee = annee ,
                                             selected!! ,
                                             onSuccessCallBack = {
                                                 loading = false
                                                 onDismiss()
                                             } ,
                                             onFailureCallBack = {
                                                 loading = false
                                                 onDismiss()
                                             }
                                         )

                                     }
                                 }

                                 name = ""


                             }

                         }
                 ){

                     Text(
                         text = "Done" ,
                         style = NormalTextStyles.TextStyleSZ7.copy(color = color1 , fontFamily = firaSansFamily , fontWeight = FontWeight.SemiBold)
                     )

                 }
             }

         }



    }


}