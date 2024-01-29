package com.example.daracademyadmin.view.screens.navigationScreens.addCours

import android.app.Activity
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.bigsam.grafic.material.loadingEffect.loadingLottieAnimation
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademyadmin.R
import com.example.daracademyadmin.model.dataClasses.Company
import com.example.daracademyadmin.model.dataClasses.Course
import com.example.daracademyadmin.model.dataClasses.Lesson
import com.example.daracademyadmin.model.dataClasses.Teacher
import com.example.daracademyadmin.model.variables.dayImg
import com.example.daracademyadmin.model.variables.firaSansFamily
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite3
import com.example.daracademyadmin.ui.theme.customWhite4
import com.example.daracademyadmin.view.common.AddTeacherBottomSheet
import com.example.daracademyadmin.view.common.SchedulerBottomSheet
import com.example.daracademyadmin.view.material.alphaBottomBar.AlphaAdjustableBottomBar
import com.example.daracademyadmin.view.screens.navigationScreens.addFormation.dialog.PickDayDialog
import com.example.daracademyadmin.viewModel.DaracademyAdminViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddCoursScreen(
    viewModel  : DaracademyAdminViewModel ,
    matiereId    : String,
    modifier   : Modifier = Modifier
) {

    val context = LocalContext.current

    val window = LocalView.current.context as Activity

    LaunchedEffect(key1 = window){
        window.window.apply {
            navigationBarColor = Color.White.toArgb()
        }
    }

    var loading by rememberSaveable {
        mutableStateOf(false)
    }

    var lessons : List<Lesson> by rememberSaveable {
        mutableStateOf(emptyList())
    }

    var selectedTeacher  : List<Teacher> by rememberSaveable {
        mutableStateOf(emptyList())
    }

    var companies  : List<Company> by rememberSaveable {
        mutableStateOf(emptyList())
    }

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


    var index  by remember{
        mutableStateOf(0)
    }


    SchedulerBottomSheet(
        show        = show_schedulerBottomSheet ,
        onDismiss   = { show_schedulerBottomSheet  = false },
        onAddClick  = { show_pickDateDialog        = true },
        onIdetClick = { index -> show_pickDateDialogForIdet = true },
        shape       = RoundedCornerShape(16.dp) ,
        coursesList = lessons,
        modifier    = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .offset(x = 0.dp, y = -20.dp)
            .heightIn(max = 500.dp)
    )



    PickDayDialog(
        show = show_pickDateDialog,
        onDismiss = {
            show_pickDateDialog = false
        },
        onDoneClick = {
            var newCourse = lessons.toMutableList()
            newCourse.add(it)
            lessons = newCourse
        }
    )


    PickDayDialog(
        show = show_pickDateDialogForIdet,
        onDismiss = {
            show_pickDateDialogForIdet = false
        },
        onDoneClick = {
            var newCourse = lessons.toMutableList()
            newCourse.removeAt(index)
            newCourse.add(index , it)
            lessons = newCourse
        }
    )

    AddTeacherBottomSheet(
        show = show_addTeacherBottomSheet,
        onDismiss = {
            show_addTeacherBottomSheet = false
        },
        teachers          = viewModel.getAllTeachers(),
        selectedTeachers  = selectedTeacher,
        onTeacherSelected = {
            val newTeachers = selectedTeacher.toMutableList()
            if(newTeachers.contains(it)){

            }
            else{
                if (newTeachers.isEmpty())
                    newTeachers.add(it)
                else
                    newTeachers[0] = it
            }
            selectedTeacher = newTeachers
        },
        shape       = RoundedCornerShape(16.dp) ,
        modifier    = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .offset(x = 0.dp, y = -20.dp)
            .heightIn(max = 500.dp)
    )







    Column(
      modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            if (!selectedTeacher.isEmpty()){
                Item(
                    teacher = selectedTeacher[0] ,
                    lessons = lessons ,
                    onEditClick = {itemIndex->
                        index = itemIndex
                        show_pickDateDialogForIdet = true
                    }
                )
            }
        }


        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color1)
        )

        AlphaAdjustableBottomBar(
            modifier = Modifier
                .height(40.dp)
                .background(customWhite0)
        ) {


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


            //scheduler
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(65.dp)
                    .clickable {
                        if (selectedTeacher.isEmpty()) {
                            Toast
                                .makeText(
                                    context,
                                    "select a teacher for the course",
                                    Toast.LENGTH_LONG
                                )
                                .show()
                            return@clickable
                        }
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


            Spacer(modifier = Modifier.weight(1f))

            //send
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(50.dp)
                    .clickable {
                        loading = true
                        viewModel.addCourses(
                            course = Course(courseId = "" , teacherId = selectedTeacher.get(0).id , matiereId = matiereId ,  lessons =  lessons ) ,
                            onSuccessCallBack = {
                                loading = false
                                viewModel.screenRepo.navigate_to_screen(Screens.HomeScreen().root)
                            },
                            onFailureCallBack = {
                                loading = false
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


@Composable
fun Item(teacher : Teacher , lessons : List<Lesson> , modifier: Modifier = Modifier , onEditClick : (Int)->Unit = {}) {
    
    Column(
        modifier = Modifier
    ) {

        Spacer(modifier = Modifier.height(26.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
        ){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxHeight()
            ){
                Image(
                    painter = rememberAsyncImagePainter(model = teacher.photo) ,
                    contentDescription = null  ,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(55.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Text(
                text = teacher.name,
                style = NormalTextStyles.TextStyleSZ6.copy(fontFamily = firaSansFamily),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        
        
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            lessons.forEachIndexed { index, lesson ->

                LessonCard(
                    lineColor = color1,
                    num = index,
                    lesson = lesson,
                    onEditClick = onEditClick
                )

            }
        }
    }
}


@Composable
fun LessonCard(
    lineColor   : Color    = Color.Blue,
    num         : Int      = 0,
    lesson      : Lesson   ,
    onEditClick : (Int)->Unit = {},
    elevation   : Dp       = 6.dp,
    shape       : Shape    = RoundedCornerShape(12.dp),
    modifier    : Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(80.dp)
                .height(120.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp)
                    .background(lineColor)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(customWhite0)
                    .border(
                        width = 2.dp,
                        color = lineColor,
                        shape = CircleShape
                    )
            ) {
                Text(
                    text = "${num+1}",
                    style = NormalTextStyles.TextStyleSZ8.copy(color = lineColor , fontFamily = firaSansFamily)
                )
            }
        }
        
        Spacer(modifier = Modifier.width(6.dp))

        Surface(
            shadowElevation = elevation,
            shape = shape,
            modifier = Modifier
                .height(80.dp)
                .weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Spacer(modifier = Modifier.width(12.dp))

                Image(
                    painter = painterResource(id = dayImg[lesson.day]) ,
                    contentDescription = null ,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(36.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = lesson.start + "-" + lesson.end,
                    style = NormalTextStyles.TextStyleSZ8.copy(color = customWhite4 , fontFamily = firaSansFamily)
                )


                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.edit_icon) ,
                    contentDescription = null ,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(26.dp)
                        .clickable {
                            onEditClick(num)
                        }
                )

                Spacer(modifier = Modifier.width(12.dp))
            }
        }

        Spacer(modifier = Modifier.width(26.dp))


    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AddCoursScreen_preview() {
    val context = LocalContext.current
    val navController = rememberNavController()
    AddCoursScreen(
        viewModel = viewModel(
            factory = object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(DaracademyAdminViewModel::class.java))
                        return DaracademyAdminViewModel(context , navController) as T
                    else
                        throw IllegalArgumentException("can't create DaracademyAdminViewModel (addCoursScreen)")
                }
            }
        ),
        matiereId = ""
    )
}