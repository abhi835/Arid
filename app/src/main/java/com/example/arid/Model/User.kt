package com.example.arid.Model

data class User (
    val uid: String = "",                  //    coming from it's email details
    val displayName: String? = "",
    val imageUrl: String = ""
)