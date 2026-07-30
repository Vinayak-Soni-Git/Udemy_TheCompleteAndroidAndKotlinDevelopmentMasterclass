package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.MVVMDatabaseAndNoteApp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.MVVMDatabaseAndNoteApp.model.Note
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainNoteAppActivity : AppCompatActivity(), NoteAdapter.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NoteAdapter
    private lateinit var addNoteButton: FloatingActionButton
    private lateinit var getResult: ActivityResultLauncher<Intent>

    @Suppress("UNCHECKED_CAST")
    private val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
            return NoteViewModel(application) as T
        }
    }
    private val noteViewModel: NoteViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_note_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        addNoteButton = findViewById(R.id.add_note_btn)
        recyclerView = findViewById(R.id.notes_recycler_view)
        adapter = NoteAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        noteViewModel.allNotes.observe(this) {
            adapter.setNotes(it)
        }
        getResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == 111) {
                    val intent = it.data
                    val title = intent?.getStringExtra("title")
                    val description = intent?.getStringExtra("description")
                    val priority = intent?.getIntExtra("priority", 1)
                    val note = Note(title!!, description!!, priority!!)
                    noteViewModel.addNote(note)
                } else if (it.resultCode == 112) {
                    val intent = it.data
                    val title = intent?.getStringExtra("title")
                    val description = intent?.getStringExtra("description")
                    val priority = intent?.getIntExtra("priority", 1)
                    val id = intent?.getIntExtra("id", -1)
                    val note = Note(title!!, description!!, priority!!)
                    note.id = id!!
                    noteViewModel.updateNote(note)
                }
            }

        addNoteButton.setOnClickListener {
            val intent = Intent(this, AddEditNoteActivity::class.java)
            startActivity(intent)
        }
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel.deleteNote(adapter.getNoteAt(viewHolder.adapterPosition))
            }
        }).attachToRecyclerView(recyclerView)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all_note_menu -> {
                noteViewModel.deleteAllNotes()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClickItem(note: Note) {
        val title = note.title
        val description = note.description
        val priority = note.priority
        val id = note.id

        val intent = Intent(this@MainNoteAppActivity, AddEditNoteActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("description", description)
        intent.putExtra("priority", priority)
        intent.putExtra("id", id)
        getResult.launch(intent)
    }
}
}