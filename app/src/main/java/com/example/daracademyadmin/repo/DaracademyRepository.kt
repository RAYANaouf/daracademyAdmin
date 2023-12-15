package com.example.daracademyadmin.repo

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.daracademy.model.data.dataClasses.Message
import com.example.daracademy.model.data.dataClasses.MessageBox
import com.example.daracademy.model.data.sealedClasses.screens.Screens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class DaracademyRepository {



    private val auth: FirebaseAuth by mutableStateOf(Firebase.auth)
    private val firebaseFirestore  by mutableStateOf(Firebase.firestore)
    private val storageRef         by mutableStateOf(Firebase.storage.reference)

    var user : FirebaseUser?  by mutableStateOf(auth.currentUser)



    constructor(){


    }


    fun signIn(email : String , password : String , onSuccessCallBack : ()->Unit = {}  ,  onFailureCallBack : (ex : Exception)->Unit = {}){
        auth.signInWithEmailAndPassword(email , password)
            .addOnSuccessListener {
                onSuccessCallBack()
            }
            .addOnFailureListener{
                onFailureCallBack(it)
            }
    }

    fun addPost(name : String, desc : String, images : List<Uri>,  onSuccessCallBack: () -> Unit = {}, onFailureCallBack: (ex : Exception) -> Unit = {}){

        val postsRef = storageRef.child("posts")
        val postId   = System.currentTimeMillis().toString()


        val imgUris = ArrayList<String>()



        for ( i in images.indices){
            imgUris.add("")

        }

        var success = 0


        val myPostRef = postsRef.child("${name}_Post_${System.currentTimeMillis()}")

        images.forEachIndexed { index, uri ->

            val imageUriRef = myPostRef.child("/img_$index")

            imageUriRef
                .putFile(uri)
                .addOnSuccessListener{

                    imageUriRef.downloadUrl.addOnSuccessListener {
                        imgUris[index] = it.toString()
                        success++

                        if (success == images.size){

                            firebaseFirestore.collection("posts")
                                .document(postId)
                                .set(
                                    hashMapOf(
                                        "postId"  to postId,
                                        "title"   to name,
                                        "desc"    to desc,
                                        "imgs"    to imgUris
                                    )
                                )
                                .addOnSuccessListener {
                                    onSuccessCallBack()
                                }
                                .addOnFailureListener{
                                    onFailureCallBack(it)
                                }

                        }

                    }



                }
                .addOnFailureListener(){
                    onFailureCallBack(it)
                }



        }






    }

    fun addTeacher( name : String , email: String , number : String , type : String , photo : Uri? ,  formation : List<String> , support : List<String> ,  onSuccessCallBack: () -> Unit = {} , onFailureCallBack: (exp : Exception) -> Unit = {}){


        val teachersRef = storageRef.child("teachers")
        val id          = System.currentTimeMillis()

        if (photo != null){
            val photoRef = teachersRef.child("${name}_${id}/photo")

            photoRef.putFile(photo)
                .addOnSuccessListener { _->
                    //get the uri
                    photoRef.downloadUrl
                        .addOnSuccessListener { downloadUri->
                            firebaseFirestore.collection("teachers")
                                .document("$id")
                                .set(
                                    hashMapOf(
                                        "id"         to id.toString(),
                                        "name"       to name,
                                        "email"      to email,
                                        "phone"      to hashMapOf("type" to type , "number" to number),
                                        "formations" to formation,
                                        "supports"   to support,
                                        "photo"      to downloadUri.toString()
                                    )
                                )
                                .addOnSuccessListener { onSuccessCallBack() }
                                .addOnFailureListener { onFailureCallBack(it) }
                        }
                        .addOnFailureListener { onFailureCallBack(it) }
                }
                .addOnFailureListener{ onFailureCallBack(it) }

        }
        else{
            firebaseFirestore.collection("teachers")
                .document("$id")
                .set(
                    hashMapOf(
                        "name"       to name,
                        "email"      to email,
                        "phone"      to hashMapOf("type" to type , "number" to number),
                        "formations" to formation,
                        "supports"   to support,
                        "photo"      to ""
                    )
                )
                .addOnSuccessListener { onSuccessCallBack() }
                .addOnFailureListener { onFailureCallBack(it) }
        }


    }

    fun addStudent( name : String , email: String , number : String , type : String , photo : Uri?  ,  onSuccessCallBack: () -> Unit = {} , onFailureCallBack: (exp : Exception) -> Unit = {}){


        val studentsRef = storageRef.child("students")
        val id          = System.currentTimeMillis()

        if (photo != null){
            val photoRef = studentsRef.child("${name}_${id}/photo")

            photoRef.putFile(photo)
                .addOnSuccessListener { _->
                    //get the uri
                    photoRef.downloadUrl
                        .addOnSuccessListener { downloadUri->
                            firebaseFirestore.collection("students")
                                .document("$id")
                                .set(
                                    hashMapOf(
                                        "name"       to name,
                                        "email"      to email,
                                        "phone"      to hashMapOf("type" to type , "number" to number),
                                        "photo"      to downloadUri.toString()
                                    )
                                )
                                .addOnSuccessListener { onSuccessCallBack() }
                                .addOnFailureListener { onFailureCallBack(it) }
                        }
                        .addOnFailureListener { onFailureCallBack(it) }
                }
                .addOnFailureListener{ onFailureCallBack(it) }

        }
        else{
            firebaseFirestore.collection("teachers")
                .document("$id")
                .set(
                    hashMapOf(
                        "name"       to name,
                        "email"      to email,
                        "phone"      to hashMapOf("type" to type , "number" to number),
                        "photo"      to ""
                    )
                )
                .addOnSuccessListener { onSuccessCallBack() }
                .addOnFailureListener { onFailureCallBack(it) }
        }




    }

    fun addFormation(name : String, desc : String, images : List<Uri>, hashtags : List<String>  , teachers : List<String> ,   onSuccessCallBack: () -> Unit = {}, onFailureCallBack: (ex : Exception) -> Unit = {}){

        val formationsRef = storageRef.child("formations")
        val id  = System.currentTimeMillis()


        val _images    = ArrayList<String>()
        val _hashtags  =  ArrayList<String>()
        val _teachers  =  ArrayList<String>()



        images.forEach { uri ->
            _images.add("")
        }

        hashtags.forEach { s ->
            _hashtags.add(s)
        }

        teachers.forEach { s ->
            _teachers.add(s)
        }



        var success = 0


        val myFormationRef = formationsRef.child("${name}_formation_${id}")

        if (images.size>0){
            images.forEachIndexed { index, uri ->

                val imageUriRef = myFormationRef.child("/img_$index")

                imageUriRef
                    .putFile(uri)
                    .addOnSuccessListener{

                        imageUriRef.downloadUrl.addOnSuccessListener {
                            _images[index] = it.toString()
                            success++

                            if (success == images.size){

                                firebaseFirestore.collection("formations")
                                    .add(
                                        hashMapOf(
                                            "name"     to name,
                                            "desc"     to desc,
                                            "imgs"     to _images,
                                            "teachers" to teachers,
                                            "hashtags" to hashtags
                                        )
                                    )
                                    .addOnSuccessListener {
                                        onSuccessCallBack()
                                    }
                                    .addOnFailureListener{
                                        onFailureCallBack(it)
                                    }

                            }

                        }



                    }
                    .addOnFailureListener(){
                        onFailureCallBack(it)
                    }



            }
        }
        else{

            firebaseFirestore.collection("formations")
                .add(
                    hashMapOf(
                        "name"     to name,
                        "desc"     to desc,
                        "imgs"     to _images,
                        "teachers" to _teachers,
                        "hashtags" to _hashtags
                    )
                )
                .addOnSuccessListener {
                    onSuccessCallBack()
                }
                .addOnFailureListener{
                    onFailureCallBack(it)
                }
        }



    }

    fun getAllMessageBoxs(onSuccessCallBack: (List<MessageBox> , Int) -> Unit = {_,_->}, onFailureCallBack: (exp : Exception) -> Unit = {}  ){



        firebaseFirestore.collection("chats")
            .get()
            .addOnSuccessListener { result->

                val messageBoxs = ArrayList<MessageBox>()

                for (doc in result){

                    messageBoxs.add(MessageBox(id = doc.id))

                }

                onSuccessCallBack(messageBoxs , result.documents.size)

            }
            .addOnFailureListener {
                onFailureCallBack(it)
            }

    }


    fun getAllMessage(id : String, onSuccessCallBack: (List<Message>) -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}  ){

        firebaseFirestore.collection("chats")
            .document(id)
            .collection("messages")
            .addSnapshotListener { snapshot, error ->
                if (error != null){
                    onFailureCallBack(error)
                    return@addSnapshotListener
                }

                if (snapshot != null && !snapshot.isEmpty){
                    val messages = snapshot.documents.mapNotNull {doc->
                        doc.toObject(Message::class.java)
                    }
                    onSuccessCallBack(messages)
                }
            }

    }

    fun sendMsg(id : String , newMassage : Message , onSuccessCallBack: () -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}  ){

        val chatBoxRef = firebaseFirestore.collection("chats").document(id).collection("messages")

        chatBoxRef
            .document(System.currentTimeMillis().toString())
            .set(
                hashMapOf("msg" to newMassage.msg , "person_msg" to  newMassage.person_msg)
            )
            .addOnSuccessListener(){
                onSuccessCallBack()
            }
            .addOnFailureListener(onFailureCallBack)


    }
}