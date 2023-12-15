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
import com.example.daracademy.model.data.dataClasses.Message
import com.example.daracademy.model.data.dataClasses.MessageBox
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademyadmin.model.dataClasses.Teacher
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

    val repo : DaracademyRepository

    private val auth: FirebaseAuth by mutableStateOf(Firebase.auth)
    private val firebaseFirestore  by mutableStateOf(Firebase.firestore)
    private val storageRef         by mutableStateOf(Firebase.storage.reference)

    var user : FirebaseUser?  by mutableStateOf(auth.currentUser)

    init {
        auth.addAuthStateListener { firebaseAuth ->
            val currentUser = firebaseAuth.currentUser
            if (currentUser != null) {
                setAppScreen(Screens.HomeScreen())
            } else {
                setAppScreen(Screens.SignInScreen())
            }
        }
    }

    constructor(context : Context){
        this.repo = DaracademyRepository()
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


    fun addFormation(name: String, desc: String, images: List<Uri>, hashtags : List<String>, teachers : List<String>, onSuccessCallBack: () -> Unit, onFailureCallBack: (exp: Exception) -> Unit){
        this.repo.addFormation(name = name , desc = desc , images = images , hashtags = hashtags , teachers = teachers , onSuccessCallBack, onFailureCallBack  )
    }

    fun getAllTeachers(onSuccessCallBack: (List<Teacher>) -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}){

        firebaseFirestore.collection("teachers")
            .get()
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    var teacherList = arrayListOf<Teacher>()
                    for (doc in task.result.documents){
                        val teacher = doc.toObject(Teacher::class.java)
                        if (teacher != null){
                            teacherList.add(teacher)
                        }
                    }
                 onSuccessCallBack(teacherList)
                }
            }
            .addOnFailureListener {
                onFailureCallBack(it)
            }

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

    fun get_matiere( phase : String , annee : String ,  onSuccessCallBack: (List<String>) -> Unit = {} , onFailureCallBack: () -> Unit = {}){



        firebaseFirestore.collection("phases_d_etudes")
            .document(phase)
            .collection("annees")
            .document(annee)
            .collection("matiere")
            .orderBy("id")
            .get()
            .addOnCompleteListener {task->
                if (task.isSuccessful){

                    val matieres = ArrayList<String>()

                    for (doc in  task.result){
                        matieres.add(doc.id)
                    }

                    onSuccessCallBack(matieres)
                }
                else{
                    onFailureCallBack()
                }
            }


    }
}


