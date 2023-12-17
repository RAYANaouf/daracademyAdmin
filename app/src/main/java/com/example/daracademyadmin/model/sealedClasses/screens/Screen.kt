package com.example.daracademy.model.data.sealedClasses.screens

import com.example.daracademy.model.data.objects.screen.screens

sealed class Screens(val root : String){



    class HomeScreen                 : Screens(root = screens.homeScreen)
    class SignInScreen               : Screens(root = screens.signInScreen)
    class AddPostScreen              : Screens(root = screens.addPostScreen)
    class AddTeacherScreen           : Screens(root = screens.addTeacherScreen)
    class AddFormationScreen         : Screens(root = screens.addFormationScreen)
//    class AddSupportCourseScreen     : Screens(root = screens.add)
    class AddStudentScreen           : Screens(root = screens.addStudentScreen)

    class ChatScreen                 : Screens(root = screens.chatScreen)
    class ChatBoxsScreen             : Screens(root = screens.chatBoxsScreen)
    class StageScreen                : Screens(root = screens.stageScreen)
    class AnneesScreen               : Screens(root = screens.anneesScreen)
    class AddCoursScreen             : Screens(root = screens.addCoursScreen)
    class MatieresScreen             : Screens(root = screens.matieresScreen)
}
