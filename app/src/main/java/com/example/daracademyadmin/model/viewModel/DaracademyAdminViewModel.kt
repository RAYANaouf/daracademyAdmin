package com.example.daracademyadmin.model.viewModel

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.util.rangeTo
import androidx.lifecycle.ViewModel
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.example.daracademyadmin.model.data.dataClasses.Teacher
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.lang.Exception

class DaracademyAdminViewModel : ViewModel{


    var appScreen by mutableStateOf("")

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

    }

    fun setAppScreen(newScreen : Screens){
        appScreen = newScreen.root
    }

    fun signIn(email : String , password : String , onSuccessCallBack : ()->Unit = {}  ,  onFailureCallBack : ()->Unit = {}){
        auth.signInWithEmailAndPassword(email , password)
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    onSuccessCallBack()
                }
                else{
                    onFailureCallBack()
                }
            }
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

    fun addPost( name : String , desc : String , images : List<Uri> , context: Context ,  onSuccessCallBack: () -> Unit = {} , onFailureCallBack: () -> Unit = {}){



        val postsRef = storageRef.child("posts")



        firebaseFirestore.collection("posts")
            .add(
                hashMapOf(
                "name"   to name,
                "desc"   to desc
                )
            )
            .addOnCompleteListener { task->

                if (task.isSuccessful){

                    val imagesUri = ArrayList<String>()
                    var success   = 0

                    images.forEachIndexed {index , uri->

                        postsRef.child("${name}_${task.result.id}/img_$index")
                            .putFile(uri)
                            .addOnCompleteListener(){postTask->
                                if (postTask.isSuccessful){

                                    imagesUri.add(postTask.result.uploadSessionUri.toString())

                                    if (images.size == imagesUri.size){
                                        imagesUri.forEachIndexed{i , uri ->
                                            firebaseFirestore.collection("posts")
                                                .document("${task.result.id}")
                                                .update(
                                                        "img_$i" , "$uri"

                                                )
                                                .addOnCompleteListener {
                                                    if (it.isSuccessful){
                                                        success++
                                                        if (success == imagesUri.size){
                                                            onSuccessCallBack()
                                                        }
                                                    }
                                                }
                                        }
                                    }


                                }
                            }
                            .addOnFailureListener(){
                                onFailureCallBack()
                            }
                    }

                }

            }
            .addOnFailureListener {
                onFailureCallBack()
            }




    }


    fun addTeacher( name : String , email: String , number : String , type : String , photo : Uri? ,  formation : List<String> , support : List<String> ,  onSuccessCallBack: () -> Unit = {} , onFailureCallBack: (exp : Exception) -> Unit = {}){


        val teachersRef = storageRef.child("teachers")

        firebaseFirestore.collection("teachers")
            .add(
                hashMapOf(
                    "name"       to name,
                    "email"      to email,
                    "phone"      to hashMapOf("type" to type , "number" to number),
                    "formations" to formation,
                    "supports"   to support,
                    "photo"      to ""
                )
            )
            .addOnSuccessListener { documentRef->

                if (photo == null) { onSuccessCallBack() }
                else{
                    val photoRef = teachersRef.child("${name}_${documentRef.id}/photo")
                    photoRef.putFile(photo)
                        .addOnSuccessListener { _->
                            //get the uri
                            photoRef.downloadUrl
                                .addOnSuccessListener { downloadUri->
                                    firebaseFirestore.collection("teachers")
                                        .document(documentRef.id)
                                        .update("photo" , downloadUri)
                                        .addOnSuccessListener { onSuccessCallBack() }
                                        .addOnFailureListener { onFailureCallBack(it) }
                                }
                                .addOnFailureListener { onFailureCallBack(it) }
                        }
                        .addOnFailureListener{ onFailureCallBack(it) }
                }

            }


    }


    fun getAllTeachers(onSuccessCallBack: (List<Teacher>) -> Unit = {} , onFailureCallBack: (exp : Exception) -> Unit = {}){

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
}


