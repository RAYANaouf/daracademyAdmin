package com.example.daracademyadmin.view.screens.navigationScreens.addFormation

import android.graphics.Color.parseColor
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.grafic.material.loadingEffect.loadingLottieAnimation
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademyadmin.R
import com.example.daracademyadmin.model.dataClasses.Lesson
import com.example.daracademyadmin.view.material.AlphaTextFields.AlphaUnderLinedTextField
import com.example.daracademyadmin.view.material.alphaBottomBar.AlphaAdjustableBottomBar
import com.example.daracademyadmin.view.common.AddTeacherBottomSheet
import com.example.daracademyadmin.view.common.SchedulerBottomSheet
import com.example.daracademyadmin.view.screens.navigationScreens.addFormation.dialog.AddHashtagDialog
import com.example.daracademyadmin.view.screens.navigationScreens.addFormation.dialog.PickDayDialog
import com.example.daracademyadmin.model.dataClasses.Teacher
import com.example.daracademyadmin.model.variables.josefinSansFamily
import com.example.daracademyadmin.viewModel.DaracademyAdminViewModel
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.customBlack9
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite1
import com.example.daracademyadmin.ui.theme.customWhite2
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddFormationScreen(
    viewModel: DaracademyAdminViewModel,
    onNavigation : (Screens)->Unit = {},
    modifier : Modifier = Modifier
) {


    var context = LocalContext.current


    var teachers : List<Teacher> by rememberSaveable {
        mutableStateOf(viewModel.getAllTeachers())
    }


    var name by rememberSaveable {
        mutableStateOf("")
    }

    var desc by rememberSaveable {
        mutableStateOf("")
    }

    var photo_uris : List<Uri> by rememberSaveable {
        mutableStateOf(emptyList())
    }

    var hashtags : List<String> by rememberSaveable {
        mutableStateOf(emptyList())
    }

    var courses : List<Lesson> by rememberSaveable {
        mutableStateOf(emptyList())
    }

    var open by remember {
        mutableStateOf(true)
    }

    var loading by rememberSaveable {
        mutableStateOf(false)
    }


    var launcher_imgs = rememberLauncherForActivityResult(contract =  ActivityResultContracts.PickMultipleVisualMedia()){uris->
        if (uris != null){
            var new_photo_uris = ArrayList<Uri>()
            new_photo_uris.addAll(photo_uris)
            uris.forEach{uri->
                new_photo_uris.add(uri)
            }
            photo_uris = new_photo_uris
        }
    }


//    var show_bottomSheet by remember{
//        mutableStateOf(false)
//    }

    var show_schedulerBottomSheet by remember{
        mutableStateOf(false)
    }

    var show_pickDateDialog by remember{
        mutableStateOf(false)
    }

    var show_pickDateDialogForIdet by remember{
        mutableStateOf(false)
    }

    var show_addTeacherBottomSheet by remember{
        mutableStateOf(false)
    }

    var idetIndex by remember{
        mutableStateOf(-1)
    }

    var selectedTeachers : List<Teacher> by rememberSaveable {
        mutableStateOf(emptyList())
    }

    val hashtagDialogState    = rememberMaterialDialogState()




//    BottomSheet(
//        show = show_bottomSheet,
//        onDismiss = {
//            show_bottomSheet = false
//        },
//        hashtagList = hashtag,
//        onAddHashtagClick = {
//            val newHashtagList = ArrayList<String>()
//            newHashtagList.addAll(hashtag)
//            newHashtagList.add(it)
//            hashtag = newHashtagList
//        }
//    )

    SchedulerBottomSheet(
        show = show_schedulerBottomSheet,
        onDismiss = {
            show_schedulerBottomSheet = false
        },
        onAddClick = {
            show_pickDateDialog = true
        },
        onIdetClick = {
            idetIndex = it
            show_pickDateDialogForIdet = true
        },
        coursesList = courses
    )


    PickDayDialog(
        show = show_pickDateDialog,
        onDismiss = {
            show_pickDateDialog = false
        },
        onDoneClick = {
            val newCourses =  ArrayList<Lesson>()
            newCourses.addAll(courses)
            newCourses.add(it)
            courses = newCourses
        }
    )

    PickDayDialog(
        show = show_pickDateDialogForIdet,
        onDismiss = {
            show_pickDateDialogForIdet = false
        },
        onDoneClick = {
            val newCourses =  ArrayList<Lesson>()
            newCourses.addAll(courses)
            newCourses.removeAt(idetIndex)
            newCourses.add(it)
            courses = newCourses
        }
    )

    AddHashtagDialog(
        state = hashtagDialogState,
        hashtags = hashtags,
        onAddHashtag = {
            val newHashtags = ArrayList<String>()
            newHashtags.addAll(hashtags)
            newHashtags.add(it)
            hashtags = newHashtags
        }
    )



    AddTeacherBottomSheet(
        show = show_addTeacherBottomSheet,
        onDismiss = {
            show_addTeacherBottomSheet = false
        },
        teachers = teachers,
        selectedTeachers = selectedTeachers,
        onTeacherSelected = {
            Toast.makeText(context  , "id : ${it.id}" , Toast.LENGTH_SHORT).show()
            val newTeachers = ArrayList<Teacher>()
            newTeachers.addAll(selectedTeachers)
            if(newTeachers.contains(it)){
                newTeachers.remove(it)
            }
            else{
                newTeachers.add(it)
            }

            selectedTeachers = newTeachers
        }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(parseColor("#f9f9f9")))
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth()
            ) {

                Box(
                    modifier = Modifier
                        .size(135.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.formation) ,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(120.dp)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.add_icon),
                        contentDescription = null,
                        tint = color1,
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.BottomEnd)
                            .clip(CircleShape)
                            .background(customWhite1)
                    )
                }

            }

            Spacer(modifier = Modifier.height(16.dp))


            AlphaUnderLinedTextField(
                text = name,
                onValueChange = {
                    name =it
                },
                underLineColor = color1,
                underLineWidth = 1.5f,
                textFieldStyle = NormalTextStyles.TextStyleSZ6.copy(fontFamily = josefinSansFamily ),
                hint = "Formation name",
                hintStyle = NormalTextStyles.TextStyleSZ6.copy(fontFamily = josefinSansFamily ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )


            Spacer(modifier = Modifier.height(26.dp))

            AlphaUnderLinedTextField(
                text = desc,
                onValueChange = {
                    desc =it
                },
                textFieldStyle = NormalTextStyles.TextStyleSZ6.copy(fontFamily = josefinSansFamily , color = customBlack9 , lineHeight = TextUnit(25f , TextUnitType.Sp)),
                hint = "Formation's description",
                hintStyle = NormalTextStyles.TextStyleSZ6.copy(fontFamily = josefinSansFamily , color = customBlack9),
                modifier = Modifier
                    .padding(start = 25.dp, end = 25.dp)
                    .align(Alignment.Start)
            )

        }

        
        
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color1)
        )

       if (photo_uris.isNotEmpty()){
           Row(
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.End,
               modifier = Modifier
                   .fillMaxWidth()
                   .height(20.dp)
                   .background(customWhite0)
           ) {
               Icon(
                   painter = painterResource(id = R.drawable.arrow_down_inline_icon),
                   contentDescription = null , tint = color1 , modifier = Modifier
                       .height(20.dp)
                       .width(35.dp)
                       .rotate(if (open) 0f else 180f)
                       .clickable {
                           open = !open
                       }


               )

           }
       }

        if (photo_uris.isNotEmpty() && open){

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(customWhite0)
                    .horizontalScroll(rememberScrollState())
            ) {

                Spacer(modifier = Modifier.width(12.dp))

                photo_uris.forEachIndexed { index, uri ->
                    Image(
                        painter = rememberAsyncImagePainter(model = uri),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .width(95.dp)
                            .padding(top = 10.dp, bottom = 10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                width = 1.dp,
                                color = customWhite2,
                                shape = RoundedCornerShape(8.dp)
                            )


                    )

                    Spacer(modifier = Modifier.width(12.dp))
                }

            }

        }


        AlphaAdjustableBottomBar(
            modifier = Modifier
                .height(40.dp)
                .background(customWhite0)
        ) {

            //add photo
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(65.dp)
                    .clickable {
                        launcher_imgs.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.photo_icon_inline),
                    contentDescription = null,
                    tint = color1,
                    modifier = Modifier
                        .fillMaxSize(0.55f)
                )
            }


            //hashtag
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(65.dp)
                    .clickable {
//                        show_bottomSheet = true
                        hashtagDialogState.show()
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.hashtag_inline_icon),
                    contentDescription = null,
                    tint = color1,
                    modifier = Modifier
                        .fillMaxSize(0.55f)
                )
            }

            //scheduler
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(65.dp)
                    .clickable {
                        show_schedulerBottomSheet = true
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.schedule_inline_icon),
                    contentDescription = null,
                    tint = color1,
                    modifier = Modifier
                        .fillMaxSize(0.55f)
                )
            }

            //set teacher
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(65.dp)
                    .clickable {
                        show_addTeacherBottomSheet = true
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.teacher_inline),
                    contentDescription = null,
                    tint = color1,
                    modifier = Modifier
                        .fillMaxSize(0.55f)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            //send
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(50.dp)
                    .clickable {

                        loading = true

                        val teachers_id = ArrayList<String>()

                        selectedTeachers.forEach {
                            teachers_id.add(it.id)
                        }

                        viewModel.addFormation(
                            name = name,
                            desc = desc,
                            images = photo_uris,
                            hashtags = hashtags,
                            teachers = teachers_id,
                            onSuccessCallBack = {
                                loading = false

                                onNavigation(Screens.HomeScreen())
                                Toast
                                    .makeText(
                                        context,
                                        "the formation has been added",
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                            },
                            onFailureCallBack = {
                                loading = false

                                Toast
                                    .makeText(
                                        context,
                                        "there is an error\n${it.message}",
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                            }
                        )
                    }
            ) {
                if (!loading){
                    Image(
                        painter = painterResource(id = R.drawable.send_icon),
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .fillMaxSize(0.65f)
                    )
                }
                else{
                    loadingLottieAnimation()
                }
            }


        }


    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AddFormationScreen_preview() {
    val context = LocalContext.current
    AddFormationScreen(
        viewModel = viewModel(
            factory = object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return if (modelClass.isAssignableFrom(DaracademyAdminViewModel::class.java))
                        DaracademyAdminViewModel( context ) as T
                    else
                        throw IllegalArgumentException("cant create DaracademyAdminViewModel (addFormation Screen)")
                }
            }
        )
    )
}