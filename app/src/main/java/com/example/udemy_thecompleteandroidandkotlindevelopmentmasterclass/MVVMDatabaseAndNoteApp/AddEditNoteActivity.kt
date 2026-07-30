package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.MVVMDatabaseAndNoteApp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.R

class AddEditNoteActivity : AppCompatActivity() {
    private lateinit var titleEt: EditText
    private lateinit var descriptionEt: EditText
    private lateinit var priorityNp: NumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_edit_note)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        titleEt = findViewById(R.id.title)
        descriptionEt = findViewById(R.id.description)
        priorityNp = findViewById(R.id.priority)
        priorityNp.minValue = 1
        priorityNp.maxValue = 10

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_close_24)

        if (intent.hasExtra("id")) {
            title = "Edit note"
            titleEt.setText(intent.getStringExtra("title"))
            descriptionEt.setText(intent.getStringExtra("description"))
            priorityNp.value = intent.getIntExtra("priority", 1)

        } else {
            title = "Add Note"
        }

    }

    fun saveNote() {
        val title = titleEt.text.toString()
        val description = descriptionEt.text.toString()
        val priority = priorityNp.value
        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            return
        }
        val id = intent.getIntExtra("id", -1)
        if (id != -1) {
            setResult(111, Intent().apply {
                putExtra("title", title)
                putExtra("description", description)
                putExtra("priority", priority)
                putExtra("id", id)
            })
        } else {
            val intent = intent
            intent.putExtra("title", title)
            intent.putExtra("description", description)
            intent.putExtra("priority", priority)
            setResult(111, intent)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_note_menu -> {
                saveNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}