package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.MVVMDatabaseAndNoteApp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.MVVMDatabaseAndNoteApp.model.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note:Note)

    @Update
    suspend fun update(note:Note)

    @Query("delete from note_table")
    suspend fun deleteAllNotes()

    @Query("select * from note_table order by priority asc")
    fun getAllNotes(): LiveData<MutableList<Note>>
}