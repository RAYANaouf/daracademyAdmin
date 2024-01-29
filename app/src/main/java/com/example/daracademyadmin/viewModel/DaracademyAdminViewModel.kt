package com.example.daracademyadmin.viewModel

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.util.rangeTo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieClipSpec
import com.example.daracademy.model.data.dataClasses.Message
import com.example.daracademy.model.data.dataClasses.MessageBox
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademyadmin.model.dataClasses.Company
import com.example.daracademyadmin.model.dataClasses.Course
import com.example.daracademyadmin.model.dataClasses.Formation
import com.example.daracademyadmin.model.dataClasses.Lesson
import com.example.daracademyadmin.model.dataClasses.Matiere
import com.example.daracademyadmin.model.dataClasses.Teacher
import com.example.daracademyadmin.model.dataClasses.apis.progress.ProgressUpload
import com.example.daracademyadmin.repo.DaracademyRepository
import com.example.daracademyadmin.repo.ScreenRepo
import com.example.daracademyadmin.repo.dataStore.DataStoreRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.launch

class DaracademyAdminViewModel : ViewModel{


    var isSignIn by mutableStateOf(true )
        private set

    val repo : DaracademyRepository
    val screenRepo : ScreenRepo

    private val dataStoreRepo : DataStoreRepo

    var boxMessages = mutableListOf<MessageBox>()
        private set


    var matieres : List<Matiere>by mutableStateOf(emptyList())



    constructor(context : Context , navController: NavHostController ){
        this.repo = DaracademyRepository  ( context = context )
        this.dataStoreRepo = DataStoreRepo( context = context )
        isSignIn {
            isSignIn = it
        }
        this.screenRepo = ScreenRepo(navController)
    }

    fun isSignIn(onResult: (Boolean)->Unit = {} ){
        viewModelScope.launch {
            onResult(dataStoreRepo.isSignIn())
        }
    }

    fun signIn(email : String , password : String , onSuccessCallBack : ()->Unit = {}  ,  onFailureCallBack : (ex : Exception)->Unit = {}){

        this.repo.signIn(
            email = email,
            password = password ,
            onSuccessCallBack = {
                this.viewModelScope.launch {
                    dataStoreRepo.saveSignIn()
                }
                isSignIn = true
                onSuccessCallBack()
            } ,
            onFailureCallBack = onFailureCallBack
        )

    }
    fun getAllFormation() = this.repo.getAllFormation()
    fun getAllPosts()     = this.repo.getAllPosts()
    fun getAllTeachers()  = this.repo.getAllTeachers()
    fun getAllStudent()   = this.repo.getAllStudents()

    fun addPost( name : String , desc : String , images : List<Uri> ,  onSuccessCallBack: () -> Unit = {} , onFailureCallBack: (ex : Exception) -> Unit = {}){

        this.repo.addPost(name , desc, images, onSuccessCallBack , onFailureCallBack)

    }

    fun addTeacher( name : String , email: String , password : String ,  number : String , type : String , photo : Uri? ,  formation : List<String> , support : List<String> ,  onSuccessCallBack: () -> Unit = {} , onFailureCallBack: (exp : Exception) -> Unit = {}){


        this.repo.addTeacher(name = name, email = email, password =  password , number = number, type =  type, photo = photo, formation  =formation, support = support,  onSuccessCallBack, onFailureCallBack)


    }

    fun addStudent( name : String , email: String , number : String , type : String , photo : Uri?  ,  onSuccessCallBack: () -> Unit = {} , onFailureCallBack: (exp : Exception) -> Unit = {}){


        this.repo.addStudent(name, email, number, type, photo, onSuccessCallBack, onFailureCallBack)


    }


    fun addFormation(name: String, desc: String, images: List<Uri>, companies : List<Company> , lessons : List<Lesson>, teacher : String, onSuccessCallBack: () -> Unit, onFailureCallBack: (exp: Exception) -> Unit){
        this.repo.addFormation(name = name , desc = desc , images = images , companies = companies , lessons = lessons , teacher = teacher , onSuccessCallBack = onSuccessCallBack, onFailureCallBack = onFailureCallBack  )
    }


    fun getAllMessage(userId : String , productId : String , onSuccessCallBack: (List<Message>) -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}  ){
        repo.getAllMessage(userId , productId , onSuccessCallBack, onFailureCallBack)
    }

    fun sendMsg(userId : String , productId : String , newMassage : Message , onSuccessCallBack: () -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}  ){
        repo.sendMsg(userId, productId , newMassage , onSuccessCallBack, onFailureCallBack)
    }

    fun getAllMatieres(phase : String , annee : String , onSuccessCallBack: (List<Matiere>) -> Unit  , onFailureCallBack: (ex: Exception) -> Unit){

        this.matieres = emptyList()

        return repo.getAllMatieres(
            phase = phase ,
            annee = annee ,
            onSuccessCallBack = {
                this.matieres = it
                onSuccessCallBack(it)
            } ,
            onFailureCallBack = onFailureCallBack
        )
    }

    fun addMatieres(phase : String , annee : String , matiere : Matiere, onSuccessCallBack: (List<Matiere>) -> Unit  , onFailureCallBack: (ex: Exception) -> Unit){
        repo.addMatiere(
            phase = phase ,
            annee = annee ,
            matiere = matiere ,
            onSuccessCallBack = {
                onSuccessCallBack(it)
                this.matieres = it
            },
            onFailureCallBack = {
                onFailureCallBack(it)
            }
        )
    }

    fun addCourses(  course : Course, onSuccessCallBack: () -> Unit, onFailureCallBack: (ex: Exception) -> Unit) {
        this.repo.addCourses( course, onSuccessCallBack, onFailureCallBack)
    }

    fun getProgress(): List<ProgressUpload>{
        return repo.progresses
    }


    }


