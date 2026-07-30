package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.MVVMDatabaseAndNoteApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.MVVMDatabaseAndNoteApp.model.Note
import com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.R

class NoteAdapter(val listener: OnClickListener) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var notesList: MutableList<Note> = mutableListOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: NoteViewHolder,
        position: Int
    ) {
        val note = notesList[position]
        holder.tvTitle.text = note.title
        holder.tvDescription.text = note.description
        holder.tvPriority.text = note.priority.toString()
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun setNotes(notes: MutableList<Note>) {
        this.notesList = notes
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): Note {
        return notesList[position]
    }

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.text_view_title)
        val tvDescription: TextView = view.findViewById(R.id.text_view_description)
        val tvPriority: TextView = view.findViewById(R.id.text_view_priority)

        init {
            view.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onClickItem(notesList[adapterPosition])
                }
            }
        }
    }

    interface OnClickListener {
        fun onClickItem(note: Note)
    }
}