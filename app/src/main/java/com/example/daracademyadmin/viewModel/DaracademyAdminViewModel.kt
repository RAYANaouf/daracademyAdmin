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
import com.airbnb.lottie.compose.LottieClipSpec
import com.example.daracademy.model.data.dataClasses.Message
import com.example.daracademy.model.data.dataClasses.MessageBox
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademyadmin.model.dataClasses.Company
import com.example.daracademyadmin.model.dataClasses.Course
import com.example.daracademyadmin.model.dataClasses.Lesson
import com.example.daracademyadmin.model.dataClasses.Matiere
import com.example.daracademyadmin.model.dataClasses.Teacher
import com.example.daracademyadmin.model.dataClasses.apis.progress.ProgressUpload
import com.example.daracademyadmin.repo.DaracademyRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
class DaracademyAdminViewModel : ViewModel{


    var appScreen by mutableStateOf("")


    var boxMessages by mutableStateOf<List<MessageBox>>(emptyList())
        private set

    private val repo : DaracademyRepository


    var matieres : List<Matiere>by mutableStateOf(emptyList())


    init {
        this.repo = DaracademyRepository()
        repo.isSignIn{signIn->
            if(signIn){
                setAppScreen(Screens.HomeScreen())
            }
            else{
                setAppScreen(Screens.SignInScreen())
            }
        }
    }

    constructor(context : Context){

    }

    fun setAppScreen(newScreen : Screens){
        appScreen = newScreen.root
    }

    fun signIn(email : String , password : String , onSuccessCallBack : ()->Unit = {}  ,  onFailureCallBack : (ex : Exception)->Unit = {}){

        this.repo.signIn(email = email, password = password , onSuccessCallBack = onSuccessCallBack , onFailureCallBack = onFailureCallBack)

    }


    fun addPost( name : String , desc : String , images : List<Uri> ,  onSuccessCallBack: () -> Unit = {} , onFailureCallBack: (ex : Exception) -> Unit = {}){

        this.repo.addPost(name , desc, images, onSuccessCallBack , onFailureCallBack)

    }

    fun addTeacher( name : String , email: String , number : String , type : String , photo : Uri? ,  formation : List<String> , support : List<String> ,  onSuccessCallBack: () -> Unit = {} , onFailureCallBack: (exp : Exception) -> Unit = {}){


        this.repo.addTeacher(name, email, number, type, photo, formation, support, onSuccessCallBack, onFailureCallBack)


    }

    fun addStudent( name : String , email: String , number : String , type : String , photo : Uri?  ,  onSuccessCallBack: () -> Unit = {} , onFailureCallBack: (exp : Exception) -> Unit = {}){


        this.repo.addStudent(name, email, number, type, photo, onSuccessCallBack, onFailureCallBack)


    }


    fun addFormation(name: String, desc: String, images: List<Uri>, companies : List<Company> , lessons : List<Lesson>, teacher : String, onSuccessCallBack: () -> Unit, onFailureCallBack: (exp: Exception) -> Unit){
        this.repo.addFormation(name = name , desc = desc , images = images , companies = companies , lessons = lessons , teacher = teacher , onSuccessCallBack = onSuccessCallBack, onFailureCallBack = onFailureCallBack  )
    }

    fun getAllTeachers() : List<Teacher>{
        return repo.getAllTeachers()
    }


    fun getAllMessageBoxs(onSuccessCallBack: (List<MessageBox>  , Int) -> Unit = {_,_->}, onFailureCallBack: (ex : Exception) -> Unit = {} ){
        repo.getAllMessageBoxs(
            onSuccessCallBack = {boxs , size ->
                onSuccessCallBack(boxs , size)
                this.boxMessages = boxs
            },
            onFailureCallBack = {
                onFailureCallBack(it)
            }
        )
    }

    fun getAllMessage(id : String, onSuccessCallBack: (List<Message>) -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}  ){
        repo.getAllMessage(id, onSuccessCallBack, onFailureCallBack)
    }

    fun sendMsg(id : String , newMassage : Message , onSuccessCallBack: () -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}  ){
        repo.sendMsg(id, newMassage, onSuccessCallBack, onFailureCallBack)
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

    fun addCourses(phase : String, annee : String, matiere : String, course : Course, onSuccessCallBack: () -> Unit, onFailureCallBack: (ex: Exception) -> Unit) {
        this.repo.addCourses(phase,annee, matiere, course, onSuccessCallBack, onFailureCallBack)
    }

    fun getProgress(): List<ProgressUpload>{
        return repo.progresses
    }


    }


