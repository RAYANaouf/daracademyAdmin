package com.example.daracademyadmin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bigsam.grafic.material.topBar.AlphaTopBar2
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademyadmin.graphics.screens.fullScreen.SignInScreen
import com.example.daracademyadmin.graphics.material.alphaTopBar.AlphaTopAppBar3
import com.example.daracademyadmin.graphics.screens.navigationScreens.addFormation.AddFormationScreen
import com.example.daracademyadmin.graphics.screens.navigationScreens.addPost.AddPostScreen
import com.example.daracademyadmin.graphics.screens.navigationScreens.addSupportCourse.AddSupportCourseScreen
import com.example.daracademyadmin.graphics.screens.navigationScreens.addTeacher.AddTeacherScreen
import com.example.daracademyadmin.graphics.screens.navigationScreens.homeScreen.HomeScreen
import com.example.daracademyadmin.model.data.variables.firaSansFamily
import com.example.daracademyadmin.model.viewModel.DaracademyAdminViewModel
import com.example.daracademyadmin.ui.theme.DaracademyAdminTheme
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.color3
import com.example.daracademyadmin.ui.theme.customWhite7
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val viewModel : DaracademyAdminViewModel = viewModel(
                factory = object : ViewModelProvider.Factory{
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        if (modelClass.isAssignableFrom(DaracademyAdminViewModel::class.java))
                            return DaracademyAdminViewModel(this@MainActivity) as T
                        else
                            throw IllegalArgumentException("can't create DaracademyAdminViewModel (MainActivity)")
                    }
                }
            )



            DaracademyAdminTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    if (viewModel.appScreen == Screens.SignInScreen().root) {
                        SignInScreen(
                            viewModel = viewModel
                        )
                    }
                    else{
                        MainScreen(
                            viewModel = viewModel
                        )
                    }
                }
            }


        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel     : DaracademyAdminViewModel,
    ) {

    val context = LocalContext.current

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()





    ModalNavigationDrawer(
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.75f)
                    .background(Color.White)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
                        .drawBehind {
                            drawLine(
                                color = customWhite7,
                                strokeWidth = 1f,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height)
                            )
                        }
                        .clickable {
                            coroutineScope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.daracademy),
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .width(85.dp)
                            .height(110.dp)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(8f)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(65.dp)
                            .padding(start = 16.dp, end = 16.dp)
                            .clickable {
                                coroutineScope.launch {
                                    drawerState.apply {
                                        close()
                                    }
                                }
                            }
                            .padding(top = 6.dp, bottom = 6.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.support),
                            contentDescription = null,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .size(26.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = "Support",
                            style = NormalTextStyles.TextStyleSZ7
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(65.dp)
                            .padding(start = 16.dp, end = 16.dp)
                            .clickable {
                                coroutineScope.launch {
                                    drawerState.apply {
                                        close()
                                    }
                                }
                            }
                            .padding(top = 6.dp, bottom = 6.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.formation),
                            contentDescription = null,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .size(26.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = "Formations",
                            style = NormalTextStyles.TextStyleSZ7
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(65.dp)
                            .padding(start = 16.dp, end = 16.dp)
                            .clickable {
                                coroutineScope.launch {
                                    drawerState.apply {
                                        close()
                                    }
                                }
                            }
                            .padding(top = 6.dp, bottom = 6.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.post),
                            contentDescription = null,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .size(26.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = "Posts",
                            style = NormalTextStyles.TextStyleSZ7
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(65.dp)
                            .padding(start = 16.dp, end = 16.dp)
                            .clickable {
                                coroutineScope.launch {
                                    drawerState.apply {
                                        close()
                                    }
                                }
                            }
                            .padding(top = 6.dp, bottom = 6.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.teacher),
                            contentDescription = null,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .size(26.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = "Etudiants",
                            style = NormalTextStyles.TextStyleSZ7
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(top = 16.dp, bottom = 16.dp)
                            .height(1.dp)
                            .background(customWhite7)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(65.dp)
                            .padding(start = 16.dp, end = 16.dp)
                            .clickable {
//                                    viewModel.setAppScreen(Screens.chatScreen())
                                coroutineScope.launch {
                                    drawerState.apply {
                                        close()
                                    }
                                }
                            }
                            .padding(top = 6.dp, bottom = 6.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.live_chat),
                            contentDescription = null,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .size(26.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = "Chats",
                            style = NormalTextStyles.TextStyleSZ7
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(top = 16.dp, bottom = 16.dp)
                            .height(1.dp)
                            .background(customWhite7)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(65.dp)
                            .padding(start = 16.dp, end = 16.dp)
                            .clickable {
                                coroutineScope.launch {
                                    drawerState.apply {
                                        close()
                                    }
                                }
                            }
                            .padding(top = 6.dp, bottom = 6.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.about_us),
                            contentDescription = null,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .size(26.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = "About us",
                            style = NormalTextStyles.TextStyleSZ7
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(65.dp)
                            .padding(start = 16.dp, end = 16.dp)
                            .clickable {
                                coroutineScope.launch {
                                    drawerState.apply {
                                        close()
                                    }
                                }
                            }
                            .padding(top = 6.dp, bottom = 6.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.development),
                            contentDescription = null,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .size(26.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = "developpeur",
                            style = NormalTextStyles.TextStyleSZ7
                        )
                    }

                }
            }
        },
        drawerState = drawerState,
    ) {

        Scaffold(
            topBar = {

                if ( navBackStackEntry?.destination?.route == Screens.HomeScreen().root ){
                    AlphaTopBar2(
                        img = painterResource(id = R.drawable.daracademy),
                        onImgClick = {
                            coroutineScope.launch {
                                Toast.makeText(context , "${navBackStackEntry?.destination?.route}" , Toast.LENGTH_LONG).show()
                                drawerState.apply {
                                    open()
                                }
                            }
                        },
                        txt = "Daracademy"
                    )
                }
                else {

                    val img = remember {
                        if(navBackStackEntry?.destination?.route == Screens.AddPostScreen().root){
                            R.drawable.post
                        }
                        else if(navBackStackEntry?.destination?.route == Screens.AddTeacherScreen().root){
                            R.drawable.teacher
                        }
                        else if(navBackStackEntry?.destination?.route == Screens.AddStudentScreen().root){
                            R.drawable.teacher
                        }
                        else if(navBackStackEntry?.destination?.route == Screens.AddFormationScreen().root){
                            R.drawable.formation
                        }
                        else{
                            R.drawable.about_us
                        }
                    }

                    val title = remember {
                        if(navBackStackEntry?.destination?.route == Screens.AddPostScreen().root){
                            "Add post"
                        }
                        else if(navBackStackEntry?.destination?.route == Screens.AddTeacherScreen().root){
                            "Add teacher"
                        }
                        else if(navBackStackEntry?.destination?.route == Screens.AddStudentScreen().root){
                            "Add student"
                        }
                        else if(navBackStackEntry?.destination?.route == Screens.AddFormationScreen().root){
                            "Add formation"
                        }
                        else{
                            "I dont know"
                        }
                    }


                    AlphaTopAppBar3(
                        onCloseClicked = {
                            viewModel.setAppScreen(Screens.HomeScreen())
                            navController.navigate(Screens.HomeScreen().root)
                        },
                        centralTitle = {
                            if(navBackStackEntry?.destination?.route != Screens.AddPostScreen().root){

                            }

                            Image(
                                painter = painterResource(id = img),
                                contentDescription = null,
                                contentScale = ContentScale.Inside,
                                modifier = Modifier
                                    .size(26.dp)
                                    .clickable {
                                        Toast
                                            .makeText(
                                                context,
                                                "${navBackStackEntry?.destination?.route == Screens.AddPostScreen().root} ${Screens.AddPostScreen().root} ",
                                                Toast.LENGTH_LONG
                                            )
                                            .show()
                                    }
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = title ,
                                style = NormalTextStyles.TextStyleSZ5.copy(fontFamily = firaSansFamily , fontWeight = FontWeight.SemiBold , color = color1),
                            )

                        },
                        trailingActions = {
                            Icon(
                                painter = painterResource(id = R.drawable.translation_inline),
                                contentDescription = null,
                                tint = color1,
                                modifier = Modifier
                                    .size(26.dp)
                                    .clickable {

                                    }
                            )
                        }
                    )
                }


            },
            bottomBar = {
//                if(navBackStackEntry?.destination?.route == Screens.AddPostScreen().root){
//
//                }
            }
        ) { paddingValues ->

            NavHost(
                navController = navController,
                startDestination = Screens.HomeScreen().root
            ) {

                composable(route = Screens.HomeScreen().root) {
                    HomeScreen(
                        viewModel = viewModel,
                        onNavigate = {
                            viewModel.setAppScreen(it)
                            navController.navigate(it.root)
                        },
                        modifier = Modifier
                            .padding(top = paddingValues.calculateTopPadding() , bottom = paddingValues.calculateBottomPadding() )
                    )
                }

                composable(route = Screens.AddPostScreen().root){
                    AddPostScreen(
                        viewModel = viewModel,
                        onNavigate = {
                            navController.navigate(Screens.HomeScreen().root)
                            viewModel.setAppScreen(Screens.HomeScreen())
                        },
                        modifier = Modifier
                            .padding(top = paddingValues.calculateTopPadding() , bottom = paddingValues.calculateBottomPadding() )
                    )
                }

                composable(route = Screens.AddTeacherScreen().root){
                    AddTeacherScreen(
                        viewModel = viewModel,
                        onNavigate = {
                            navController.navigate(it.root)
                            viewModel.setAppScreen(it)
                        },
                        modifier = Modifier
                            .padding(top = paddingValues.calculateTopPadding() , bottom = paddingValues.calculateBottomPadding() )
                    )
                }

                composable(route = Screens.AddFormationScreen().root){
                    AddFormationScreen(
//                        viewModel = viewModel,
//                        onNavigate = {
//                            navController.navigate(it.root)
//                            viewModel.setAppScreen(it)
//                        },
                        modifier = Modifier
                            .background(color3)
                            .padding(top = paddingValues.calculateTopPadding() , bottom = paddingValues.calculateBottomPadding() )
                    )
                }

//                composable(route = Screens.AddSupportCourseScreen().root){
//                    AddSupportCourseScreen(
////                        viewModel = viewModel,
////                        onNavigate = {
////                            navController.navigate(it.root)
////                            viewModel.setAppScreen(it)
////                        },
//                        modifier = Modifier
//                            .padding(
//                                top = paddingValues.calculateTopPadding(),
//                                bottom = paddingValues.calculateBottomPadding()
//                            )
//                    )
//                }


            }

        }

    }

}


@Preview
@Composable
fun MainScreen_preview() {
    MainScreen(
        viewModel = viewModel()
    )
}


