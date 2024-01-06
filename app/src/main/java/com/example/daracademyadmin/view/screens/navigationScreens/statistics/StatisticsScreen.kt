package com.example.daracademyadmin.view.screens.navigationScreens.statistics

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademyadmin.R
import com.example.daracademyadmin.model.dataClasses.Student
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite4
import com.example.daracademyadmin.viewModel.DaracademyAdminViewModel

@Composable
fun StatisticsScreen(
    viewModel       : DaracademyAdminViewModel,
    navController   : NavController = rememberNavController(),
    onClick         : ()->Unit ={},
    modifier        : Modifier = Modifier
) {

    val context = LocalContext.current

    var students : Int? by rememberSaveable {
        mutableStateOf(null)
    }
    var teachers : Int? by rememberSaveable {
        mutableStateOf(null)
    }

    LaunchedEffect(key1 = true  ){
        viewModel.repo.getAllStudents(
            onSuccessCallBack = {
                students = it.count()
            },
            onFailureCallBack = {

            }
        )
        viewModel.repo.getAllTeachers(
            onSuccessCallBack = {
                teachers = it.count()
            },
            onFailureCallBack = {

            }
        )
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {

        Spacer(modifier = Modifier.height(36.dp))

        teachersAndStudents(
            teachers = teachers,
            students = students,
            onTeacherClick = {
//                navController.navigate(Screens.TeachersScreen().root){
//                    popUpTo(Screens.HomeScreen().root){
//                        inclusive = true
//                    }
//                }
            },
            onStudentClick = {
//                navController.navigate(Screens.StudentsScreen().root){
//                    popUpTo(Screens.HomeScreen().root){
//                        inclusive = true
//                    }
//                }
                onClick()
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
            )

        Spacer(modifier = Modifier.height(36.dp))

        statisticsBox(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(1f)
                .background(customWhite0)
        )

        Spacer(modifier = Modifier.height(36.dp))

        matieresAndCourses(
            matieres = 150,
            courses  = 10 ,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )

    }

}


@Composable
fun teachersAndStudents(
    teachers  : Int? ,
    students  : Int? ,
    onTeacherClick : ()->Unit = {},
    onStudentClick : ()->Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(100.dp)
    ) {



        Box(
            modifier = Modifier
                .weight(4f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = 1.5.dp,
                    color = customWhite4,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(customWhite0)
                .clickable {
                    onTeacherClick()
                }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.teacher),
                        contentDescription = null ,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .fillMaxSize(0.7f)
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    Text(text = "${teachers ?: "00"}")
                }

            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .weight(4f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = 1.5.dp,
                    color = customWhite4,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(customWhite0)
                .clickable {
                    onStudentClick()
                }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.student_icon),
                        contentDescription = null ,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .fillMaxSize(0.7f)
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    Text(text = "${students ?: "00"}")
                }

            }
        }




    }
}

@Composable
fun matieresAndCourses(
    matieres  : Int? ,
    courses  : Int? ,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(100.dp)
    ) {



        Box(
            modifier = Modifier
                .weight(4f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = 1.5.dp,
                    color = customWhite4,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(customWhite0)
                .clickable { }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.add_model),
                        contentDescription = null ,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .fillMaxSize(0.7f)
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    Text(text = "${matieres ?: "00"}")
                }

            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .weight(4f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = 1.5.dp,
                    color = customWhite4,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(customWhite0)
                .clickable { }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.courses),
                        contentDescription = null ,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .fillMaxSize(0.7f)
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    Text(text = "${courses ?: "00"}")
                }

            }
        }




    }
}

@Composable
fun formationsAndMoney(
    formations : Int? ,
    money      : Int? ,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(100.dp)
    ) {



        Box(
            modifier = Modifier
                .weight(4f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = 1.5.dp,
                    color = customWhite4,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(customWhite0)
                .clickable { }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.formation),
                        contentDescription = null ,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .fillMaxSize(0.7f)
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    Text(text = "${formations ?: "00"}")
                }

            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .weight(4f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = 1.5.dp,
                    color = customWhite4,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(customWhite0)
                .clickable { }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.student_icon),
                        contentDescription = null ,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .fillMaxSize(0.7f)
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    Text(text = "${money ?: "00"}")
                }

            }
        }




    }
}


@Composable
fun statisticsBox(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .border(
                width = 1.5.dp,
                color = customWhite4,
                shape = RoundedCornerShape(12.dp)
            )
    ) {

    }

}

@Preview
@Composable
fun StatisticsScreen_preview() {

    val context = LocalContext.current

    StatisticsScreen(
        viewModel = viewModel(
            factory = object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(DaracademyAdminViewModel::class.java))
                        return DaracademyAdminViewModel(context) as T
                    else
                        throw IllegalArgumentException("cant create viewModel (statistics screen)")
                }
            }
        )
    )

}