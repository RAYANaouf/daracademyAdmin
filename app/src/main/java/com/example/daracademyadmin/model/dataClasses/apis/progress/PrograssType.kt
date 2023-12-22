package com.example.daracademyadmin.model.dataClasses.apis.progress

sealed class PrograssType{

    class Formation : PrograssType()
    class Post      : PrograssType()
    class Teacher      : PrograssType()


}
