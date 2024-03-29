package com.example.daracademyadmin

import android.content.Context
import android.graphics.Paint.Align
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.core.view.WindowCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bigsam.grafic.material.topBar.AlphaTopBar2
import com.example.bigsam.model.data.`object`.NormalTextStyles
import com.example.daracademy.model.data.dataClasses.MessageBox
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademy.view.screens.annees_de_etude_Screen.AnneesDesEtudesScreen
import com.example.daracademy.view.screens.chatBox.ChatScreen
import com.example.daracademy.view.screens.chatScreen.ChatBoxsScreen
import com.example.daracademyadmin.model.dataClasses.apis.progress.ProgressUpload
import com.example.daracademyadmin.model.sealedClasses.phaseDesEtudes.PhaseDesEtudes
import com.example.daracademyadmin.view.screens.fullScreen.SignInScreen
import com.example.daracademyadmin.view.material.alphaTopBar.AlphaTopAppBar3
import com.example.daracademyadmin.view.screens.navigationScreens.addFormation.AddFormationScreen
import com.example.daracademyadmin.view.screens.navigationScreens.addPost.AddPostScreen
import com.example.daracademyadmin.view.screens.navigationScreens.addStudent.AddStudentScreen
import com.example.daracademyadmin.view.screens.navigationScreens.addTeacher.AddTeacherScreen
import com.example.daracademyadmin.view.screens.navigationScreens.homeScreen.HomeScreen
import com.example.daracademyadmin.model.variables.firaSansFamily
import com.example.daracademyadmin.viewModel.DaracademyAdminViewModel
import com.example.daracademyadmin.ui.theme.DaracademyAdminTheme
import com.example.daracademyadmin.ui.theme.backgroundLight
import com.example.daracademyadmin.ui.theme.color1
import com.example.daracademyadmin.ui.theme.color2
import com.example.daracademyadmin.ui.theme.color3
import com.example.daracademyadmin.ui.theme.customWhite0
import com.example.daracademyadmin.ui.theme.customWhite7
import com.example.daracademyadmin.view.screens.navigationScreens.Courses.CoursesScreen
import com.example.daracademyadmin.view.screens.navigationScreens.MatieresScreen.MatieresScreen
import com.example.daracademyadmin.view.screens.navigationScreens.Posts.PostsScreen
import com.example.daracademyadmin.view.screens.navigationScreens.addCours.AddCoursScreen
import com.example.daracademyadmin.view.screens.navigationScreens.stageScreen.StageScreen
import com.example.daracademyadmin.view.screens.navigationScreens.statistics.StatisticsScreen
import com.example.daracademyadmin.view.screens.navigationScreens.students.StudentsScreen
import com.example.daracademyadmin.view.screens.navigationScreens.uploadScreen.UploadScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window , false)



        setContent {

        val navController = rememberNavController()

            val viewModel : DaracademyAdminViewModel = viewModel(
                factory = object : ViewModelProvider.Factory{
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        if (modelClass.isAssignableFrom(DaracademyAdminViewModel::class.java))
                            return DaracademyAdminViewModel(this@MainActivity , navController) as T
                        else
                            throw IllegalArgumentException("can't create DaracademyAdminViewModel (MainActivity)")
                    }
                }
            )

            LaunchedEffect(key1 = true ){
                viewModel.repo.listenToChatBoxs()
            }



            DaracademyAdminTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {



                    if (viewModel.isSignIn){
                        MainScreen(
                            viewModel = viewModel
                        )
                    }
                    else{
                        SignInScreen(
                            viewModel = viewModel
                        )
                    }
                }
            }


        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    viewModel     : DaracademyAdminViewModel,
    ) {

    val context = LocalContext.current

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()




    ModalNavigationDrawer(
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .widthIn(max = 450.dp)
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

                                    viewModel.screenRepo.navigate_to_screen(screen = Screens.StageScreen().root , params = arrayOf("view"))
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

                                    viewModel.screenRepo.navigate_to_screen(screen = Screens.FormationsScreen().root)

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

                                    viewModel.screenRepo.navigate_to_screen(screen = Screens.PostsScreen().root)

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

                                viewModel.screenRepo.navigate_to_screen(screen = Screens.StatisticsScreen().root)

                            }
                            .padding(top = 6.dp, bottom = 6.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.statistique),
                            contentDescription = null,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .size(26.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = "statistique",
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
                                viewModel.screenRepo.navigate_to_screen(Screens.ChatBoxsScreen().root)
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

                                viewModel.screenRepo.navigate_to_screen(screen = Screens.UploadsScreen().root)

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
                            painter = painterResource(id = R.drawable.upload),
                            contentDescription = null,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .size(26.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = "Uploads",
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
        modifier = Modifier
            .background(customWhite0)
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) {


        Box(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing)
        ) {


            Scaffold(
                topBar = {
                    if ( viewModel.screenRepo.app_screen == Screens.HomeScreen().root )
                    {
                        AlphaTopBar2(
                            img = painterResource(id = R.drawable.daracademy),
                            onImgClick = {
                                coroutineScope.launch {
                                    drawerState.apply {
                                        open()
                                    }
                                }
                            },
                            txt = "Daracademy",
                            modifier = Modifier
                        )
                    }
                    else {

                        val img by remember(viewModel.screenRepo.app_screen) {
                            derivedStateOf {
                                when(viewModel.screenRepo.app_screen){
                                    Screens.AddPostScreen().root-> R.drawable.post
                                    Screens.AddTeacherScreen().root->R.drawable.teacher
                                    Screens.AddStudentScreen().root->R.drawable.student_icon
                                    Screens.AddFormationScreen().root->R.drawable.formation
                                    else ->-1
                                }
                            }
                        }

                        val translate_img by remember(viewModel.screenRepo.app_screen) {
                            derivedStateOf {
                                when(viewModel.screenRepo.app_screen){
                                    Screens.AddPostScreen().root-> R.drawable.translation_inline
                                    Screens.AddFormationScreen().root-> R.drawable.translation_inline
                                    else -> -1
                                }
                            }
                        }

                        val title = viewModel.screenRepo.app_screen


                        AlphaTopAppBar3(
                            elevation = if (viewModel.screenRepo.app_screen == Screens.StudentsScreen().root) 0.dp else 8.dp,
                            onCloseClicked = {
                                viewModel.screenRepo.navigate_to_screen(Screens.HomeScreen().root)

                            },
                            centralTitle = {

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                ) {
                                    if (img >0){
                                        Image(
                                            painter = painterResource(id = img),
                                            contentDescription = null,
                                            contentScale = ContentScale.Inside,
                                            modifier = Modifier
                                                .size(26.dp)
                                                .clickable {

                                                }
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(10.dp))

                                    if (title != ""){
                                        Text(
                                            text  = buildAnnotatedString {
                                                withStyle(
                                                    style = SpanStyle(fontSize = NormalTextStyles.TextStyleSZ3.fontSize , color = color1)
                                                ){
                                                    append(title[0])
                                                }
                                                withStyle(
                                                    style = SpanStyle(fontSize = NormalTextStyles.TextStyleSZ5.fontSize , color = color3)
                                                ){
                                                    append(title[1])
                                                }
                                                withStyle(
                                                    style = SpanStyle(fontSize = NormalTextStyles.TextStyleSZ5.fontSize , color = color2)
                                                ){
                                                    for (i in 2..<title.length){
                                                        append(title[i])
                                                    }
                                                }
                                            } ,
                                            style = NormalTextStyles.TextStyleSZ5.copy(fontFamily = firaSansFamily , fontWeight = FontWeight.SemiBold , color = color1),
                                        )
                                    }
                                }


                            },
                            trailingActions = {
                                if (translate_img > 0){
                                    Icon(
                                        painter = painterResource(id = translate_img),
                                        contentDescription = null,
                                        tint = color1,
                                        modifier = Modifier
                                            .size(26.dp)
                                            .clickable {

                                            }
                                            .align(Alignment.CenterEnd)
                                    )
                                }

                            },
                        )
                    }
                },
                modifier = Modifier
                    .background(customWhite0)
                    .windowInsetsPadding(WindowInsets.statusBars)
            ) {paddingValues ->
                NavHost(
                    navController = viewModel.screenRepo.navController,
                    startDestination = Screens.HomeScreen().root,
                    modifier = Modifier
                ) {

                    composable(route = Screens.HomeScreen().root) {
                        HomeScreen(
                            viewModel = viewModel,
                            modifier = Modifier
                                .background(backgroundLight)
                                .padding(
                                    top = paddingValues.calculateTopPadding(),
                                )
                        )
                    }

                    composable(route = Screens.PostsScreen().root) {
                        PostsScreen(
                            viewModel = viewModel,
                            modifier = Modifier
                                .background(backgroundLight)
                                .padding(
                                    top = paddingValues.calculateTopPadding(),
                                )
                        )
                    }


                    composable(route = Screens.CoursesScreen().root) {
                        CoursesScreen(
                            viewModel = viewModel,
                            modifier = Modifier
                                .background(backgroundLight)
                                .padding(
                                    top = paddingValues.calculateTopPadding(),
                                )
                        )
                    }



                    composable(route = Screens.AddPostScreen().root){
                        AddPostScreen(
                            viewModel = viewModel,
                            onNavigate = {
                                viewModel.screenRepo.navigate_to_screen(Screens.HomeScreen().root)
                            },
                            modifier = Modifier
                                .background(backgroundLight)
                                .padding(
                                    top = paddingValues.calculateTopPadding(),
                                )
                        )
                    }

                    composable(route = Screens.AddTeacherScreen().root){
                        AddTeacherScreen(
                            viewModel = viewModel,
                            modifier = Modifier
                                .background(backgroundLight)
                                .padding(top = paddingValues.calculateTopPadding())
                        )
                    }

                    composable(route = Screens.AddFormationScreen().root){
                        AddFormationScreen(
                        viewModel = viewModel,
                            modifier = Modifier
                                .background(backgroundLight)
                                .padding(top = paddingValues.calculateTopPadding())
                        )
                    }

                    composable(route = Screens.AddStudentScreen().root){
                        AddStudentScreen(
                            viewModel = viewModel,
                            modifier = Modifier
                                .background(backgroundLight)
                                .padding(top = paddingValues.calculateTopPadding())
                        )
                    }

                    composable(route = Screens.ChatBoxsScreen().root){
                        ChatBoxsScreen(
                            viewModel = viewModel,
                            modifier = Modifier
                                .background(backgroundLight)
                                .padding(
                                    top = paddingValues.calculateTopPadding()
                                )
                        )
                    }

                    composable(
                        route = "${Screens.ChatScreen().root}/{userId}/{productId}/{name}",
                        arguments = listOf(
                            navArgument(name = "userId"){
                                type = NavType.StringType
                            },
                            navArgument(name = "productId"){
                                type = NavType.StringType
                            },
                            navArgument(name = "name"){
                                type = NavType.StringType
                            }
                        )
                    ){navBackStackEntry->
                        ChatScreen(
                            viewModel    = viewModel,
                            userId       = navBackStackEntry.arguments?.getString("userId")    ?: "",
                            productId    = navBackStackEntry.arguments?.getString("productId") ?: "",
                            name         = navBackStackEntry.arguments?.getString("name")      ?: "",
                            modifier     = Modifier
                                .background(backgroundLight)
                                .padding(
                                    top = paddingValues.calculateTopPadding()
                                )
                        )
                    }

                    composable(
                        route = "${Screens.StageScreen().root}/{kind}",
                        arguments = listOf(
                            navArgument("kind"){
                                type = NavType.StringType
                            }
                        )
                    ){navBackStackEntry->
                        StageScreen(
                            viewModel = viewModel,
                            kind      = navBackStackEntry.arguments?.getString("kind") ?: "",
                            modifier  = Modifier
                                .background(backgroundLight)
                                .padding(
                                    top = paddingValues.calculateTopPadding()
                                )
                        )
                    }

                    composable(
                        route = "${Screens.AnneesScreen().root}/{phase}/{kind}",
                        arguments = listOf(
                            navArgument(name = "phase"){
                                type = NavType.StringType
                            },
                            navArgument(name = "kind"){
                                type = NavType.StringType
                            }
                        )
                    ){navBackStackEntry->

                        AnneesDesEtudesScreen(
                            viewModel = viewModel,
                            phase     = navBackStackEntry.arguments?.getString("phase") ?: "",
                            kind      = navBackStackEntry.arguments?.getString("kind") ?: "",
                            modifier  = Modifier
                                .background(backgroundLight)
                                .fillMaxSize()
                                .padding(
                                    top = paddingValues.calculateTopPadding()
                                )
                        )
                    }

                    composable(
                        route = "${Screens.MatieresScreen().root}/{phase}/{annee}/{kind}",
                        arguments = listOf(
                            navArgument("phase"){
                                type = NavType.StringType
                            },
                            navArgument("annee"){
                                type = NavType.StringType
                            },
                            navArgument("kind"){
                                type = NavType.StringType
                            }
                        )
                    ){navBackStackEntry->
                        MatieresScreen(
                            viewModel = viewModel,
                            phase     = navBackStackEntry.arguments?.getString("phase") ?: "",
                            annee     = navBackStackEntry.arguments?.getString("annee") ?: "",
                            kind      = navBackStackEntry.arguments?.getString("kind") ?: "",
                            modifier     = Modifier
                                .background(backgroundLight)
                                .padding(
                                    top = paddingValues.calculateTopPadding()
                                )
                        )
                    }



                    composable(
                        route = "${Screens.AddCoursScreen().root}/{matiereId}",
                        arguments = listOf(
                            navArgument("matiereId"){
                                type = NavType.StringType
                            }
                        )
                    ){navBackStackEntry->
                        AddCoursScreen(
                            matiereId  = navBackStackEntry.arguments?.getString("matiereId") ?: "",
                            viewModel  = viewModel,
                            modifier   = Modifier
                                .background(backgroundLight)
                                .padding(
                                    top = paddingValues.calculateTopPadding()
                                )
                        )
                    }


                    composable(
                        route = "${Screens.StatisticsScreen().root}"
                    ){navBackStackEntry->


                        StatisticsScreen(
                            viewModel     = viewModel,
                            modifier      = Modifier
                                .background(backgroundLight)
                                .padding(top = paddingValues.calculateTopPadding())
                        )


                    }

                    composable(
                        route = "${Screens.StudentsScreen().root}"
                    ){navBackStackEntry->

                        StudentsScreen(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(backgroundLight)
                                .padding(top = paddingValues.calculateTopPadding())
                        )

                    }




                    composable(
                        route = "${Screens.UploadsScreen().root}"
                    ){navBackStackEntry->

                        UploadScreen(
                            progresses = viewModel.getProgress(),
                            modifier = Modifier
                                .background(backgroundLight)
                                .padding(top = paddingValues.calculateTopPadding())
                        )

                    }

                    composable(
                        route = "${Screens.FormationsScreen().root}"
                    ){navBackStackEntry->
                        com.example.daracademy.view.screens.formations.FormationsScreen(
                            viewModel = viewModel,
                            modifier = Modifier
                                .background(Color(android.graphics.Color.parseColor("#f9f9f9")))
                                .padding(top = paddingValues.calculateTopPadding())
                                .windowInsetsPadding(WindowInsets.navigationBars)
                        )
                    }

                }
            }




        }



    }

}



@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun MainScreen_preview() {
    MainScreen(
        viewModel = viewModel()
    )
}


