package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.MVVMDatabaseAndNoteApp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.MVVMDatabaseAndNoteApp.model.Note
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes: LiveData<MutableList<Note>>
    val repository: NoteRepository

    init {
        val dao = NoteDatabase.getInstance(application).noteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.update(note)
    }

    fun addNote(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    suspend fun deleteAllNotes() = viewModelScope.launch {
        repository.deleteAll()
    }
}