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


    fun addTeacher( name : String , email: String , phone : String , formation : HashMap<String , String> , support : HashMap<String , String> ,  onSuccessCallBack: () -> Unit = {} , onFailureCallBack: (exp : Exception) -> Unit = {}){

        firebaseFirestore.collection("teacher")
            .add(
                hashMapOf(
                    "name"       to name,
                    "email"      to email,
                    "number"     to phone,
                    "formations" to formation,
                    "supports"   to support
                )
            )
            .addOnFailureListener {
                onFailureCallBack(it)
            }
            .addOnSuccessListener {
                onSuccessCallBack()
            }

    }
}


