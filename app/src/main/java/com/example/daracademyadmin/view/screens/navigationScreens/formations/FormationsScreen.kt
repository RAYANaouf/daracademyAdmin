package com.example.daracademy.view.screens.formations

import android.app.Activity
import android.content.ClipData.Item
import android.widget.Toast
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademyadmin.R
import com.example.daracademyadmin.model.dataClasses.Formation
import com.example.daracademyadmin.model.dataClasses.Teacher
import com.example.daracademyadmin.model.variables.josefinSansFamily
import com.example.daracademyadmin.ui.theme.backgroundLight
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.color2
import com.example.daracademyadmin.ui.theme.customBlack5
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite4
import com.example.daracademyadmin.ui.theme.customWhite7
import com.example.daracademyadmin.view.material.loadingEffect.LottieAnimation_loadingImage
import com.example.daracademyadmin.viewModel.DaracademyAdminViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FormationsScreen(
    viewModel      : DaracademyAdminViewModel,
    modifier       : Modifier = Modifier
) {



    val window = LocalView.current.context as Activity

    LaunchedEffect(key1 = window){
        window.window.apply {
            navigationBarColor = backgroundLight.toArgb()
        }
    }



    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 16.dp , bottom = 26.dp , start = 16.dp , end = 16.dp),
        modifier = modifier
    ){

        if ( viewModel.repo._formations == null){

        }
        else{

            items(
                viewModel.repo._formations,
                key = {
                    "${it.name}_${it.desc}_${it.imgs[0]}"
                }
            ){

                val editAction = SwipeAction(
                    icon = {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .width(150.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.edit_icon) ,
                                contentDescription = null,
                                tint = customWhite0,
                                modifier = Modifier.size(60.dp)
                            )
                        }
                    },
                    background = color2,
                    onSwipe = {

                    }
                )

                val deleteAction = SwipeAction(
                    icon = {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .width(150.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.delete_icon) ,
                                contentDescription = null,
                                tint = customWhite0,
                                modifier = Modifier.size(60.dp)
                            )
                        }
                    },
                    background = Color.Red,
                    onSwipe = {
                        viewModel.viewModelScope.launch {
                            delay(400)
                            viewModel.repo._formations.remove(it)
                        }
                    }
                )



                    SwipeableActionsBox(
                        startActions = listOf(editAction),
                        endActions   = listOf(deleteAction),
                        modifier = Modifier
                            .animateItemPlacement(
                                animationSpec = tween(durationMillis = 800)
                            )
                    ) {
                        Item(
                            formation = it,
                            viewModel = viewModel,
                            elevation = 2.dp,
                            modifier  = Modifier
                                .fillMaxWidth()
                        )
                    }




            }
        }

    }

}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Item(
    formation   : Formation,
    viewModel   : DaracademyAdminViewModel = viewModel(),
    color       : Color    = Color.White,
    shape       : Shape    = RoundedCornerShape(12.dp),
    imageShape  : Shape    = RoundedCornerShape(8.dp),
    elevation   : Dp       = 0.dp,
    imgModifier : Modifier = Modifier,
    modifier    : Modifier = Modifier
) {

    var teacher : Teacher? = null
    var context = LocalContext.current

    LaunchedEffect(key1 = true ){
//        viewModel.getTeacherById(
//            teacherId = formation.teacher,
//            onSuccessCallBack = {
//                teacher = it
//            },
//            onFailureCallBack = {
//                Toast.makeText(context , "fail" , Toast.LENGTH_LONG).show()
//            }
//        )
    }


    Surface(
        color            = color,
        shape            = shape ,
        shadowElevation  = elevation,
        modifier         = modifier
    ) {

        Row(
//            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {


            val context = LocalContext.current

            val img = rememberAsyncImagePainter(
                model = formation.imgs[0] ,
            )

            val state = img.state
            
            
            //img
            Box(
                modifier = imgModifier
                    .size(100.dp)
                    .aspectRatio(1f)
                    .clip(imageShape)
                    .border(
                        width = 1.5.dp,
                        color = customWhite4,
                        shape = imageShape
                    )
            ){

                Image(
                    painter =  img ,
                    contentDescription = formation.name ,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(10f)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    LottieAnimation_loadingImage()
                }


            }


            Spacer(modifier = Modifier.width(10.dp))


            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = formation.name,
                    style = NormalTextStyles.TextStyleSZ6.copy(color = customBlack5 , fontFamily = josefinSansFamily),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.money_icon),
                            contentDescription = null,
                            tint = customWhite7 ,
                            modifier = Modifier
                                .size(20.dp)
                        )

//                        Spacer(modifier = Modifier.width(2.dp))

                        Text(
                            text = "${if (formation.prix == 0) "for free" else formation.prix}",
                            style = NormalTextStyles.TextStyleSZ9.copy(color = customWhite7 , fontFamily = josefinSansFamily , lineHeight = 16.sp),
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    if (formation.certaficate){
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                painter = painterResource(id = R.drawable.certificate),
                                contentDescription = null,
                                tint = customWhite7 ,
                                modifier = Modifier
                                    .size(20.dp)
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = "Certificate",
                                style = NormalTextStyles.TextStyleSZ9.copy(color = customWhite7 , fontFamily = josefinSansFamily , lineHeight = 16.sp),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.teacher_inline),
                            contentDescription = null,
                            tint = customWhite7 ,
                            modifier = Modifier
                                .size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(2.dp))

                        Text(
                            text = if (teacher?.name == null)  "...."  else  "${teacher?.name}",
                            style = NormalTextStyles.TextStyleSZ9.copy(color = customWhite7 , fontFamily = josefinSansFamily , lineHeight = 16.sp),
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }



                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = formation.desc,
                    style = NormalTextStyles.TextStyleSZ9.copy(color = customWhite7 , fontFamily = josefinSansFamily , lineHeight = 16.sp),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }

    }

}


@Preview
@Composable
fun Item_preview() {
    Item(
        Formation(
            "formation name" ,
            "formation descrition bal bal abal bal abal bal abal bal abal bal abal bal abal bal abal bal abal bal abal bal a",
            "rayan aouf",
            1500,
        )
    )
}




@Preview
@Composable
fun FormationsScreen_preview() {
    val context = LocalContext.current
    val navController = rememberNavController()
    FormationsScreen(
        viewModel = viewModel(
            factory = object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(DaracademyAdminViewModel::class.java)){
                        return DaracademyAdminViewModel(context ,  navController ) as T
                    }
                    else
                        throw IllegalArgumentException("creation if daracademyViewModel (formations screen)")
                }
            }
        )
    )
}