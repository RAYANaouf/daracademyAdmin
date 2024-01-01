package com.example.daracademyadmin.repo

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.daracademy.model.data.dataClasses.Message
import com.example.daracademy.model.data.dataClasses.MessageBox
import com.example.daracademy.model.data.variables.les_annees_d_etude.annees_de_C_E_M
import com.example.daracademy.model.data.variables.les_annees_d_etude.annees_de_lycee
import com.example.daracademy.model.data.variables.les_annees_d_etude.annees_de_primaire
import com.example.daracademyadmin.model.dataClasses.Company
import com.example.daracademyadmin.model.dataClasses.Course
import com.example.daracademyadmin.model.dataClasses.Formation
import com.example.daracademyadmin.model.dataClasses.Lesson
import com.example.daracademyadmin.model.dataClasses.Matiere
import com.example.daracademyadmin.model.dataClasses.Post
import com.example.daracademyadmin.model.dataClasses.Student
import com.example.daracademyadmin.model.dataClasses.Teacher
import com.example.daracademyadmin.model.dataClasses.apis.progress.PrograssType
import com.example.daracademyadmin.model.dataClasses.apis.progress.ProgressUpload
import com.example.daracademyadmin.model.sealedClasses.phaseDesEtudes.PhaseDesEtudes
import com.example.daracademyadmin.repo.dataStore.DataStoreRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class DaracademyRepository {


    private val context  : Context
    private val auth: FirebaseAuth by mutableStateOf(Firebase.auth)
    private val firebaseFirestore  by mutableStateOf(Firebase.firestore)
    private val storageRef         by mutableStateOf(Firebase.storage.reference)



    /******************************** live info ********************************************/
    var teachers         : List<Teacher>?  = null
        private set
    var posts            : List<Post>?  = null
        private set
    var formations       : List<Formation>?  = null
        private set
    var students         : List<Student>?  = null
        private set
    var loaded by mutableStateOf(false)

    var chatBoxs         by mutableStateOf<List<MessageBox>>(emptyList())
        private set


    var chatListener      :  ListenerRegistration? = null
    var chatBoxsListener  :  ListenerRegistration? = null


    /***************************************************************************************/

    /******************************** in porogree ******************************************/


    var progresses  = mutableStateListOf<ProgressUpload>()


    /***************************************************************************************/

    var matieres : HashMap<String , HashMap< String , List<Matiere>? > > =
        hashMapOf(
            PhaseDesEtudes.Primaire().phase to hashMapOf(
                annees_de_primaire[0].id     to null,
                annees_de_primaire[1].id     to null,
                annees_de_primaire[2].id     to null,
                annees_de_primaire[3].id     to null,
                annees_de_primaire[4].id     to null,
                annees_de_primaire[5].id     to null,
                annees_de_primaire[6].id     to null,
            ),
            PhaseDesEtudes.CEM().phase      to hashMapOf(
                annees_de_C_E_M[0].id        to null,
                annees_de_C_E_M[1].id        to null,
                annees_de_C_E_M[2].id        to null,
                annees_de_C_E_M[3].id        to null,
                annees_de_C_E_M[4].id        to null,
            ),
            PhaseDesEtudes.Lycee().phase    to hashMapOf(
                annees_de_lycee[0].id        to null,
                annees_de_lycee[1].id        to null,
                annees_de_lycee[2].id        to null,
                annees_de_lycee[3].id        to null,
            )

        )







    constructor(context: Context){
        this.context = context


        listenToTeachers()
        listenToPosts()
        listenToformations()
        listenToStudents()
    }

    fun isSignIn(result : (Boolean)-> Unit = {}){
        auth.addAuthStateListener { firebaseAuth ->
            val currentUser = firebaseAuth.currentUser
            if (currentUser != null) {
                result(true)
            } else {
                result(false)
            }
        }
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

    //listeners
    fun listenToTeachers( onFailureCallBack : (ex : Exception)->Unit = {}){
        firebaseFirestore.collection("teachers")
            .addSnapshotListener { snapshot, error ->
                if (error != null){
                    onFailureCallBack(error)
                    return@addSnapshotListener
                }
                if (snapshot==null || snapshot.isEmpty ){
                    return@addSnapshotListener
                }

                teachers = snapshot.documents.mapNotNull { documentSnapshot ->
                    documentSnapshot.toObject(Teacher::class.java)
                }

            }
    }
    fun listenToStudents( onFailureCallBack : (ex : Exception)->Unit = {}){
        firebaseFirestore.collection("students")
            .addSnapshotListener { snapshot, error ->
                if (error != null){
                    onFailureCallBack(error)
                    return@addSnapshotListener
                }
                if (snapshot==null || snapshot.isEmpty ){
                    return@addSnapshotListener
                }

                students = snapshot.documents.mapNotNull { documentSnapshot ->
                    documentSnapshot.toObject(Student::class.java)
                }

            }
    }
    fun listenToPosts( onFailureCallBack : (ex : Exception)->Unit = {}){
        firebaseFirestore.collection("posts")
            .addSnapshotListener { snapshot, error ->
                if (error != null){
                    onFailureCallBack(error)
                    return@addSnapshotListener
                }
                if (snapshot==null || snapshot.isEmpty ){
                    return@addSnapshotListener
                }

                posts = snapshot.documents.mapNotNull { documentSnapshot ->
                    documentSnapshot.toObject(Post::class.java)
                }

            }
    }
    fun listenToformations( onFailureCallBack : (ex : Exception)->Unit = {}){
        firebaseFirestore.collection("formations")
            .addSnapshotListener { snapshot, error ->
                if (error != null){
                    onFailureCallBack(error)
                    return@addSnapshotListener
                }
                if (snapshot==null || snapshot.isEmpty ){
                    return@addSnapshotListener
                }

                formations = snapshot.documents.mapNotNull { documentSnapshot ->
                    documentSnapshot.toObject(Formation::class.java)
                }

            }
    }

    fun listenToChatBoxs( onSuccessCallBack: (List<MessageBox>) -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}){

        chatBoxsListener?.remove()

        chatBoxsListener = firebaseFirestore.collection("chats")
            .addSnapshotListener { snapshot, error ->
                if (error != null){
                    onFailureCallBack(error)
                    return@addSnapshotListener
                }

                if (snapshot == null || snapshot.isEmpty){
                    return@addSnapshotListener
                }

                val newChatBoxs = snapshot.documents.mapNotNull { msg->
                    val messageBox = msg.toObject(MessageBox::class.java)
                    messageBox?.copy(productId = msg.id.split("_")[1])

                }.toList()

                chatBoxs = newChatBoxs

                onSuccessCallBack(chatBoxs)
            }
    }



    fun addPost(name : String, desc : String, images : List<Uri>,  onSuccessCallBack: () -> Unit = {}, onFailureCallBack: (ex : Exception) -> Unit = {}){

        //add ref
        val postsRef = storageRef.child("posts")
        val postId   = System.currentTimeMillis().toString()


       //progresses
        val progressPostIndex = progresses.size
        progresses.add(
            ProgressUpload(
                id = postId ,
                progress = emptyList(),
                type = PrograssType.Post()
            )
        )
        var imgProg = ArrayList<Float>()



        val imgUris = ArrayList<String>()



        for ( i in images.indices){
            imgUris.add("")

        }

        var success = 0


        val myPostRef = postsRef.child("${name}_Post_${System.currentTimeMillis()}")


        images.forEachIndexed { index, uri ->
            imgProg.add(0f)

            val imageUriRef = myPostRef.child("/img_$index")

            imageUriRef
                .putFile(uri)
                .addOnProgressListener {

                    imgProg[index] = (  (100f * it.bytesTransferred) / it.totalByteCount  )
                    val item = this.progresses[progressPostIndex]
                    this.progresses.removeAt(progressPostIndex)

                    //op
                    item.progress =  imgProg

                    this.progresses.add(progressPostIndex , item )

                }
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
                    var item = progresses[progressPostIndex]
                    progresses.removeAt(progressPostIndex)

                    //do something
                    item = item.copy(failed = true)

                    progresses.add(progressPostIndex , item)
                    onFailureCallBack(it)
                }



        }






    }

    fun addTeacher( name : String , email: String , number : String , type : String , photo : Uri? ,  formation : List<String> , support : List<String> ,  onSuccessCallBack: () -> Unit = {} , onFailureCallBack: (exp : Exception) -> Unit = {}){


        val teachersRef = storageRef.child("teachers")
        val id          = System.currentTimeMillis()

        //progresses
        val progressPostIndex = progresses.size
        progresses.add(
            ProgressUpload(
                id = id.toString() ,
                progress = emptyList(),
                type = PrograssType.Teacher()
            )
        )
        var imgProg = ArrayList<Float>()


        if (photo != null){
            val photoRef = teachersRef.child("${name}_${id}/photo")

            imgProg.add(0f)

            photoRef.putFile(photo)
                .addOnProgressListener {

                    imgProg[0] = 100f * it.bytesTransferred / it.totalByteCount
                    var item = progresses[progressPostIndex]
                    progresses.removeAt(progressPostIndex)

                    //do
                    item = item.copy(progress = imgProg )

                    progresses.add(progressPostIndex , item)

                }
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
                .addOnFailureListener{

                    var item = progresses[progressPostIndex]
                    progresses.removeAt(progressPostIndex)

                    //do
                    item = item.copy(failed = true )

                    progresses.add(progressPostIndex , item)

                    onFailureCallBack(it)
                }

        }
        else{
            imgProg.add(100f)

            var item = progresses[progressPostIndex]
            progresses.removeAt(progressPostIndex)

            //do
            item = item.copy(progress = imgProg )

            progresses.add(progressPostIndex , item)

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

    fun addFormation(name : String, desc : String, images : List<Uri>, lessons : List<Lesson> , companies : List<Company>  , teacher : String ,   onSuccessCallBack: () -> Unit = {}, onFailureCallBack: (ex : Exception) -> Unit = {}){

        val formationsRef = storageRef.child("formations")
        val id  = System.currentTimeMillis()

        //progresses
        val progressFormationIndex = progresses.size
        progresses.add(
            ProgressUpload(
                id = id.toString() ,
                progress = emptyList(),
                type = PrograssType.Formation()
            )
        )
        var imgForm = ArrayList<Float>()


        val _images    =  ArrayList<String>()



        images.forEach { uri ->
            _images.add("")
        }




        var success = 0


        val myFormationRef = formationsRef.child("${name}_formation_${id}")

        if (images.size>0){
            images.forEachIndexed { index, uri ->
                imgForm.add(0f)
                val imageUriRef = myFormationRef.child("/img_$index")

                imageUriRef
                    .putFile(uri)
                    .addOnProgressListener {
                        imgForm[index] = (  (100f * it.bytesTransferred) / it.totalByteCount  )
                        val item = this.progresses[progressFormationIndex]
                        this.progresses.removeAt(progressFormationIndex)

                        //op
                        item.progress =  imgForm

                        this.progresses.add(progressFormationIndex , item )
                    }
                    .addOnSuccessListener{

                        imageUriRef.downloadUrl
                            .addOnSuccessListener {
                                _images[index] = it.toString()
                                success++

                                if (success == images.size){

                                    firebaseFirestore.collection("formations")
                                        .add(
                                            hashMapOf(
                                                "name"      to name,
                                                "desc"      to desc,
                                                "imgs"      to _images,
                                                "teacher"   to teacher,
                                                "lessons"   to lessons,
                                                "companies" to companies
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
                            .addOnFailureListener {
                                onFailureCallBack(it)
                            }



                    }
                    .addOnFailureListener(){
                        var item = progresses[progressFormationIndex]
                        progresses.removeAt(progressFormationIndex)

                        //do something
                        item = item.copy(failed = true)

                        progresses.add(progressFormationIndex , item)
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
                        "imgs"     to emptyList<String>(),
                        "teachers" to teacher,
                        "lessons"  to lessons
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



    //geters
    fun getAllTeachers( ) : List<Teacher>?{
        if (this.teachers == null)
            listenToTeachers()

        return this.teachers
    }
    fun getAllStudents( ) : List<Student>?{
        if (this.students == null)
            listenToformations()
        return this.students
    }
    fun getAllPosts( ) : List<Post>?{
        if (this.posts == null)
            listenToPosts()
        return this.posts
    }
    fun getAllFormation( ) : List<Formation>?{
        if (this.formations == null)
            listenToformations()
        return this.formations
    }

    fun getAllMessageBoxs( onSuccessCallBack: (List<MessageBox> ) -> Unit = {} , onFailureCallBack: (exp : Exception) -> Unit = {}  ){


        chatBoxsListener?.remove()

        chatBoxsListener = firebaseFirestore.collection("chats")
            .orderBy("timestamp" , Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null){
                    onFailureCallBack(error)
                    return@addSnapshotListener
                }
                if (snapshot != null && !snapshot.isEmpty){
                    val chatBoxs = snapshot.documents.mapNotNull { doc ->
                        val ids = doc.id.split("_")
                        doc.toObject(MessageBox::class.java)?.copy(userId = ids[0] , productId = ids[1])
                    }
                    onSuccessCallBack(chatBoxs)
                }
            }

    }


    fun getAllMessage(userId : String , productId : String , onSuccessCallBack: (List<Message>) -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}  ){


        chatListener?.remove()

        chatListener = firebaseFirestore.collection("chats").document("${userId}_${productId}")
            .collection("messages")
            .orderBy("timestamp")
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

    fun sendMsg(userId : String , productId : String , newMassage : Message , onSuccessCallBack: () -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}  ){


        val chatBoxRef = firebaseFirestore.collection("chats").document("${userId}_${productId}")
        val messagesRef = chatBoxRef.collection("messages")

        val id = System.currentTimeMillis().toString()

        if (newMassage.photo != "") {
            val imageRef = storageRef.child("messagePhotos/user_$userId/product_$productId/$id")

            imageRef.putFile(Uri.parse(newMassage.photo))
                .addOnFailureListener(onFailureCallBack)
                .addOnSuccessListener {
                    imageRef.downloadUrl
                        .addOnFailureListener { onFailureCallBack(it) }
                        .addOnSuccessListener { storageUri ->

                            messagesRef
                                .document()
                                .set(
                                    hashMapOf(
                                        "msg" to newMassage.msg,
                                        "photo" to (storageUri?.toString() ?: ""),
                                        "person_msg" to newMassage.person_msg,
                                        "timestamp" to FieldValue.serverTimestamp(),
                                    )
                                )
                                .addOnSuccessListener() {
                                    //lastMessage
                                    chatBoxRef.set(
                                        mapOf(
                                            "lastMessage" to newMassage.msg,
                                            "photo" to "photo",
                                            "person_msg" to newMassage.person_msg,
                                            "timestamp" to FieldValue.serverTimestamp()
                                        ),
                                        SetOptions.merge()
                                    )
                                        .addOnFailureListener {
                                            onFailureCallBack(it)
                                        }
                                        .addOnSuccessListener {
                                            onSuccessCallBack()
                                        }

                                }
                                .addOnFailureListener(onFailureCallBack)
                        }
                }
        }
        else{
            messagesRef
                .document()
                .set(
                    hashMapOf(
                        "msg" to newMassage.msg,
                        "person_msg" to newMassage.person_msg,
                        "timestamp" to FieldValue.serverTimestamp(),
                    )
                )
                .addOnFailureListener(onFailureCallBack)
                .addOnSuccessListener() {
                    //lastMessage
                    chatBoxRef.set(
                        mapOf(
                            "lastMessage" to newMassage.msg,
                            "person_msg" to newMassage.person_msg,
                            "timestamp" to FieldValue.serverTimestamp()
                        ),
                        SetOptions.merge()
                    )
                        .addOnFailureListener(onFailureCallBack)
                        .addOnSuccessListener {
                            onSuccessCallBack()
                        }

                }
        }

    }



    fun addMatiere( phase : String , annee : String , matiere : Matiere , onSuccessCallBack: (List<Matiere>) -> Unit , onFailureCallBack: (ex: Exception) -> Unit){

        //add to firebase

        var id = System.currentTimeMillis().toString()

        if (matiere.imgUrl != ""){
            val imgRef = storageRef.child("matieres/$phase/$annee/${matiere.name}/$id")
            imgRef.putFile(Uri.parse(matiere.imgUrl))
                .addOnSuccessListener {
                    imgRef.downloadUrl
                        .addOnSuccessListener {storageUri->

                        firebaseFirestore.collection("phases")
                            .document(phase)
                            .collection("annees")
                            .document(annee)
                            .collection("matieres")
                            .document(matiere.name)
                            .set(
                                hashMapOf(
                                    "name"   to matiere.name,
                                    "img"    to matiere.img,
                                    "imgUrl" to storageUri
                                )
                            )
                            .addOnSuccessListener {
                                var list = matieres.get(phase)?.get(annee)?.toMutableList()
                                list?.add(matiere)
                                matieres.get(phase)?.set(annee , list ?: emptyList())

                                onSuccessCallBack(
                                    list ?: emptyList()
                                )
                            }
                            .addOnFailureListener {
                                onFailureCallBack(it)
                            }

                        }
                        .addOnFailureListener(onFailureCallBack)
                }
                .addOnFailureListener(onFailureCallBack)
        }
        else{
            firebaseFirestore.collection("phases")
                .document(phase)
                .collection("annees")
                .document(annee)
                .collection("matieres")
                .document(matiere.name)
                .set(
                    hashMapOf(
                        "name"   to matiere.name,
                        "img"    to matiere.img,
                    )
                )
                .addOnSuccessListener {
                    var list = matieres.get(phase)?.get(annee)?.toMutableList()
                    list?.add(matiere)
                    matieres.get(phase)?.set(annee , list ?: emptyList())

                    onSuccessCallBack(
                        list ?: emptyList()
                    )
                }
                .addOnFailureListener {
                    onFailureCallBack(it)
                }

        }







    }

    fun getAllMatieres(phase : String , annee : String , onSuccessCallBack: (List<Matiere>) -> Unit , onFailureCallBack: (ex: Exception) -> Unit) {


        if (matieres.get(phase)?.get(annee) != null){
            onSuccessCallBack(matieres.get(phase)?.get(annee)!!)
        }
        else{
            firebaseFirestore.collection("phases")
                .document(phase)
                .collection("annees")
                .document(annee)
                .collection("matieres")
                .get()
                .addOnSuccessListener { docs->
                    var new_matieres = ArrayList<Matiere>()
                    for (doc in docs){
                        new_matieres.add(doc.toObject(Matiere::class.java))
                    }

                    matieres.get(phase)?.set(annee , new_matieres)

                    onSuccessCallBack(new_matieres)
                }
                .addOnFailureListener {
                    onFailureCallBack(it)
                }
        }


    }


    fun addCourses( phase : String , annee : String , matiere : String , course : Course , onSuccessCallBack: () -> Unit , onFailureCallBack: (ex: Exception) -> Unit){

        val courseId  = System.currentTimeMillis().toString()


        firebaseFirestore.collection("phases")
            .document(phase)
            .collection("annees")
            .document(annee)
            .collection("matieres")
            .document(matiere)
            .collection("courses")
            .document(courseId)
            .set(course.copy(courseId = courseId))
            .addOnSuccessListener {
                onSuccessCallBack()
            }
            .addOnFailureListener {
                onFailureCallBack(it)
            }
    }


    fun getAllStudents(onSuccessCallBack: (List<Student>) -> Unit , onFailureCallBack: (ex: Exception) -> Unit){

        firebaseFirestore.collection("students")
            .get()
            .addOnFailureListener(onFailureCallBack)
            .addOnSuccessListener { result->
                var students  = arrayListOf<Student>()

                for (student in result){
                    students.add(student.toObject(Student::class.java))
                }

                onSuccessCallBack(students)

            }

    }

    fun getAllTeachers(onSuccessCallBack: (List<Teacher>) -> Unit , onFailureCallBack: (ex: Exception) -> Unit){

        firebaseFirestore.collection("teachers")
            .get()
            .addOnFailureListener(onFailureCallBack)
            .addOnSuccessListener { result->
                var teachers  = arrayListOf<Teacher>()

                for (student in result){
                    teachers.add(student.toObject(Teacher::class.java))
                }

                onSuccessCallBack(teachers)

            }

    }




}