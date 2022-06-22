package com.example.arid.Model

data class Post (
    val question:String = "",
    val option1:String = "",
    val option2:String = "",
    val option3:String = "",
    val option4:String = "",



    val createdBy:User = User(),                 //Here we have created who have created the post
    val createdAt:Long= 0L,
    val polledBy:ArrayList<String> = ArrayList(),
    val optionOne:ArrayList<String> = ArrayList(),
    val optionTwo:ArrayList<String> = ArrayList(),
    val optionThree:ArrayList<String> = ArrayList(),
    val optionFour:ArrayList<String> = ArrayList()
)