package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.Firestore

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.R
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.toObject
import java.security.Key

class MainFirestoreActivity : AppCompatActivity() {
    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var btnSave: Button
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private lateinit var loadButton: Button
    private lateinit var textViewData: TextView
    private lateinit var updateTitleBtn: Button

    private lateinit var deleteDescriptionBtn: Button
    private lateinit var deleteNoteBtn: Button

    private val docRef = db.collection("Notebook").document("First Note")
    private val noteBookRef: CollectionReference = db.collection("Notebook")
    private val KEY_TITLE = "title"
    private val KEY_DESCRIPTION = "description"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_firestore)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etTitle = findViewById(R.id.et_title)
        etDescription = findViewById(R.id.et_description)
        btnSave = findViewById(R.id.btn_save)
        loadButton = findViewById(R.id.btn_load)
        textViewData = findViewById(R.id.tv_data)
        updateTitleBtn = findViewById(R.id.btn_update_title)
        deleteDescriptionBtn = findViewById(R.id.btn_delete_description)
        deleteNoteBtn = findViewById(R.id.btn_delete_note)



        btnSave.setOnClickListener {
            save()
        }
        loadButton.setOnClickListener {
            loadData()
        }
        updateTitleBtn.setOnClickListener {
            updateTitle()
        }
        deleteDescriptionBtn.setOnClickListener {
            deleteDescription()
        }
        deleteNoteBtn.setOnClickListener {
            deleteNote()
        }
    }

    private fun save() {
        val title = etTitle.text.toString()
        val description = etDescription.text.toString()

        val note = Note(title, description)

        textViewData.text = "Title: $title, Description: $description"

        db.collection("Notebook").document("First Note").set(note)
            .addOnSuccessListener {
                Toast.makeText(this@MainFirestoreActivity, "Note added!", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this@MainFirestoreActivity, "Note not added!", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    private fun loadData() {
        docRef.get().addOnSuccessListener { document ->
            if (document.exists()) {
                val title = document.getString(KEY_TITLE)
                val description = document.getString(KEY_DESCRIPTION)

                val note = mutableMapOf<String, Any>()
                note.put(KEY_TITLE, title!!)
                note.put(KEY_DESCRIPTION, description!!)
            } else {
                Toast.makeText(
                    this@MainFirestoreActivity,
                    "The document does not exist",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this@MainFirestoreActivity, "Failed to load data", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onStart() {
        super.onStart()

        docRef.addSnapshotListener { document, exception ->
            exception?.let {
                return@addSnapshotListener
            }
            document?.let {
                if (it.exists()) {
                    val note = it.toObject(Note::class.java)

//                    val title = it.getString(KEY_TITLE)
//                    val description = it.getString(KEY_DESCRIPTION)
//                    textViewData.text = "Title: $title, Description: $description"

                    textViewData.text = "Title: ${note?.title}, Description: ${note?.description}"
                } else {
                    textViewData.text = ""
                    Toast.makeText(
                        this@MainFirestoreActivity,
                        "The document does not exist",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun deleteDescription() {
        val note = mutableMapOf<String, Any>()
        note.put(KEY_DESCRIPTION, FieldValue.delete())
        docRef.update(note)
    }

    private fun deleteNote() {
        docRef.delete()
    }

    private fun updateTitle() {
        val title = etTitle.text.toString()
        val note = mutableMapOf<String, Any>()
        note[KEY_TITLE] = title
        docRef.set(note, SetOptions.merge())
    }

    override fun onStop() {
        super.onStop()
    }
}