package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.Firestore

import com.google.firebase.firestore.Exclude

data class Note(val title: String, val description: String, val priority:Int) {
    constructor() : this("", "", 0)

    @Exclude
    val id: String = ""
}