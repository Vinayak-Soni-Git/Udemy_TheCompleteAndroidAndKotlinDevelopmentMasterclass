package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.Firestore

import com.google.firebase.firestore.Exclude

data class Note(val title: String, val description: String) {
    constructor() : this("", "")

    @Exclude
    val id: String = ""
}